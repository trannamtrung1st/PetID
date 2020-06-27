/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.parser.petfinder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import javax.persistence.EntityManager;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import petid.business.PetBreedService;
import petid.business.PetTypeService;
import petid.data.models.BreedAttr;
import petid.data.models.BreedInfo;
import petid.data.models.BreedTrait;
import petid.data.models.PetBreed;
import petid.data.models.PetType;
import petid.helper.HttpHelper;
import petid.helper.RegexHelper;
import petid.helper.XMLHelper;
import petid.parser.petfinder.models.xmlschema.BreedItem;
import petid.xmlparser.XmlParserConfig;
import petid.xmlparser.preprocessor.HtmlPreprocessor;

/**
 *
 * @author TNT
 */
public class Parser {

    protected XmlParserConfig xmlParserConfig;
    protected ParserConfig parserConfig;
    protected XPath xpath;
    protected Templates breedTemplate;
    protected Validator breedValidator;
    protected PetTypeService petTypeService;
    protected PetBreedService petBreedService;
    protected EntityManager entityManager;
    protected Map<String, PetType> breedLinks;

    public Parser(
            EntityManager entityManager,
            PetTypeService petTypeService,
            PetBreedService petBreedService,
            Validator breedValidator,
            Templates breedTemplate,
            XmlParserConfig xmlParserConfig,
            ParserConfig parserConfig) {
        this.petBreedService = petBreedService;
        this.xmlParserConfig = xmlParserConfig;
        this.petTypeService = petTypeService;
        this.parserConfig = parserConfig;
        this.xpath = XMLHelper.getXPath();
        this.breedLinks = new HashMap<>();
        this.breedTemplate = breedTemplate;
        this.entityManager = entityManager;
        this.breedValidator = breedValidator;
    }

    public void start() {
        try {
            if (parserConfig.isGetAllBreeds()) {
                getAllBreeds();
                System.out.println("All breeds: " + breedLinks.size());
                parseBreedLinks();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void getAllBreeds() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, Exception {
        for (ParserConfig.AllBreedsPages.Page p : parserConfig.getAllBreedsPages().getPage()) {
            PetType petType = getOrCreatePetType(p);
            String content = preprocess(p.getUrl());
//            FileHelper.writeToFile(content, "temp.html");
            //parse DOM and use XPath to get links
            Document doc = XMLHelper.parseDOMFromString(content);
            NodeList linkNodes = (NodeList) xpath.evaluate(parserConfig.getBreedLinksXPath(), doc, XPathConstants.NODESET);
            System.out.println(petType.getName() + ": " + linkNodes.getLength());
            for (int i = 0; i < linkNodes.getLength(); i++) {
                String link = linkNodes.item(i).getNodeValue();
                link = resolveFullUrl(link);
                breedLinks.put(link, petType);
            }
        }
    }

    protected PetType getOrCreatePetType(ParserConfig.AllBreedsPages.Page breedPage) throws Exception {
        String typeName = breedPage.getPetType();
        PetType entity = petTypeService.findPetTypeByName(typeName);
        if (entity == null) {
            entity = new PetType();
            entity.setName(typeName);
            petTypeService.validateForCreate(entity);
            entityManager.getTransaction().begin();
            petTypeService.createPetType(entity);
            entityManager.getTransaction().commit();
        }
        return entity;
    }

    protected void parseBreedLinks() {
        BreedItem currentBreed = null;
        for (Map.Entry<String, PetType> kvp : breedLinks.entrySet()) {
            try {
                String link = kvp.getKey();
                PetType petType = kvp.getValue();
                System.out.println("Start parsing page: " + link);
                String pageContent = preprocess(link);
//                FileHelper.writeToFile(pageContent, "temp.html");
                String modelXml = transform(link, pageContent);
                validateBreedXml(modelXml);
//                FileHelper.writeToFile(modelXml, "temp.xml");
                currentBreed = XMLHelper.unmarshallDocXml(modelXml, petid.parser.petfinder.models.xmlschema.ObjectFactory.class);
                System.out.println(currentBreed.getBreedName());
                processBreedItem(currentBreed, petType);
                System.out.println("Finish parsing page: " + kvp);
                System.out.println("------------------------");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void validateBreedXml(String modelXml) throws SAXException, IOException {
        breedValidator.validate(new StreamSource(new StringReader(modelXml)));
    }

    protected String transform(String pageUrl, String pageContent) throws TransformerConfigurationException, FileNotFoundException, TransformerException, Exception {
        // Use the template to create a transformer
        Transformer xformer = breedTemplate.newTransformer();
        xformer.setParameter("url", pageUrl);
        Matcher matcher = RegexHelper.matcherDotAll(pageUrl, parserConfig.getBreedCodeFromUrlRegex());
        String code = null;
        if (matcher.find()) {
            code = matcher.group(1);
        } else {
            throw new Exception("Code not found");
        }
        xformer.setParameter("code", code);

        // Prepare the input and output files
        Source source = new StreamSource(new StringReader(pageContent));
        StringWriter writer = new StringWriter();
        Result result = new StreamResult(writer);
        // Apply the xsl file to the source file and write the result
        // to the output file
        xformer.transform(source, result);
        return writer.toString();
    }

    protected void processBreedItem(BreedItem item, PetType petType) throws ParseException, Exception {
        boolean existed = petBreedService.petBreedCodeExists(item.getCode());
        if (!existed) {
            //TODO: move String to config
            PetBreed entity = new PetBreed();
            entity.setAvailableUrl(item.getAvailableUrl());
            entity.setCode(item.getCode());
            entity.setDescription(item.getDescription());
            entity.setImageUrl(item.getImageUrl());
            entity.setIsAvailableParsed(false);
            entity.setIsBreedImagesParsed(false);
            entity.setName(item.getBreedName());
            entity.setTypeName(petType);
            entity.setUrl(item.getUrl());
            for (BreedItem.Traits.Item trait : item.getTraits().getItem()) {
                BreedTrait model = new BreedTrait();
                model.setName(trait.getName());
                model.setValue(trait.getValue());
                petBreedService.addPetBreedTrait(entity, model);
            }
            for (BreedItem.Attributes.Item trait : item.getAttributes().getItem()) {
                BreedAttr model = new BreedAttr();
                model.setName(trait.getName());
                model.setValue(trait.getValue());
                petBreedService.addPetBreedAttr(entity, model);
            }
            for (BreedItem.Sections.Item section : item.getSections().getItem()) {
                BreedInfo model = new BreedInfo();
                model.setName(section.getName());
                model.setSectionContent(section.getValue());
                petBreedService.addPetBreedInfo(entity, model);
            }
            entityManager.getTransaction().begin();
            petBreedService.createPetBreed(entity);
            entityManager.getTransaction().commit();
        }
    }

    protected String preprocess(String url) throws IOException {
        String content = HttpHelper.getPageContent(url);
        HtmlPreprocessor processor = new HtmlPreprocessor(xmlParserConfig);
        content = processor.refineHtml(content);
        return content;
    }

    protected String resolveFullUrl(String relPath) {
        return relPath.startsWith("http") ? relPath
                : ((parserConfig.getBaseUrl() + (relPath.startsWith("/") ? relPath : "/" + relPath)));
    }

}
