/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.parser.dogily;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import petid.business.PetPostService;
import petid.business.PetTypeService;
import petid.data.models.BreedAttr;
import petid.data.models.BreedInfo;
import petid.data.models.BreedTrait;
import petid.data.models.PetBreed;
import petid.data.models.PetPost;
import petid.data.models.PetType;
import petid.helper.FileHelper;
import petid.helper.HttpHelper;
import petid.helper.RegexHelper;
import petid.helper.XMLHelper;
import petid.parser.dogily.models.xmlschema.PostItems;
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
    protected Templates postTemplate;
    protected Validator postValidator;
    protected PetPostService petPostService;
    protected PetBreedService petBreedService;
    protected EntityManager entityManager;
    protected Set<String> parsedLinks;

    public Parser(
            EntityManager entityManager,
            PetPostService petPostService,
            PetBreedService petBreedService,
            Validator postValidator,
            Templates postTemplate,
            XmlParserConfig xmlParserConfig,
            ParserConfig parserConfig) {
        this.petPostService = petPostService;
        this.petBreedService = petBreedService;
        this.xmlParserConfig = xmlParserConfig;
        this.parserConfig = parserConfig;
        this.xpath = XMLHelper.getXPath();
        this.parsedLinks = new HashSet<>();
        this.postTemplate = postTemplate;
        this.entityManager = entityManager;
        this.postValidator = postValidator;
    }

    public void start() {
        try {
            getAllLinks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void getAllLinks() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, Exception {
        List<PetBreed> list = petBreedService.getAllPetBreedsHasDogilyMapping();
        for (PetBreed petBreed : list) {
            ParserConfig.Pages.Page page = parserConfig.getPages().getPage()
                    .stream().filter(p -> p.getType().equals(petBreed.getTypeName().getName())).findFirst().get();
            String link = page.getUrlPlaceholder().replace("{code}", petBreed.getDogilyCodeMapping());
            if (!parsedLinks.add(link)) {
                continue;
            }
            int pageNo = 1;
            String pageLink = link;
            while (parseLink(pageLink, petBreed) > 0) {
                pageNo++;
                String appendStr = parserConfig.getPagingAppendStr().replace("{page}", pageNo + "");
                pageLink = link.endsWith("/") ? (link + appendStr) : (link + "/" + appendStr);
            }

        }
    }

    protected int parseLink(String link, PetBreed petBreed) {
        int count = 0;
        try {
            PostItems current = null;
            System.out.println("Start parsing page: " + link);
            String pageContent = preprocess(link);
            FileHelper.writeToFile(pageContent, "temp.html");
            String modelXml = transform(pageContent);
            FileHelper.writeToFile(modelXml, "temp.xml");
            validateXml(modelXml);
            current = XMLHelper.unmarshallDocXml(modelXml, petid.parser.dogily.models.xmlschema.ObjectFactory.class);
            count = processPostItems(current, petBreed);
            System.out.println("End parsing page: " + link);
            System.out.println("------------------------");
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    protected int processPostItems(PostItems items, PetBreed breed) throws ParseException, Exception {
        int count = 0;
        for (PostItems.Items.Item item : items.getItems().getItem()) {
            try {
                String name = items.getItemNames().getItem().get(count++);
                System.out.println(name);
                String detailUrl = item.getDetailUrl();
                Matcher matcher = RegexHelper.matcherDotAll(detailUrl, parserConfig.getPostCodeFromUrlRegex());
                String code = null;
                if (matcher.find()) {
                    code = matcher.group(1);
                } else {
                    throw new Exception("Code not found");
                }
                boolean existed = petPostService.petPostCodeExists(code);
                if (!existed) {
                    PetPost entity = new PetPost();
                    entity.setAvailable(true);
                    entity.setCode(code);
                    entity.setDetailUrl(detailUrl);
                    entity.setImageUrl(item.getImageUrl());
                    entity.setName(name);
                    entity.setPetBreedCode(breed);
                    entityManager.getTransaction().begin();
                    petPostService.createPetPost(entity);
                    entityManager.getTransaction().commit();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    protected void validateXml(String modelXml) throws SAXException, IOException {
        postValidator.validate(new StreamSource(new StringReader(modelXml)));
    }

    protected String transform(String pageContent) throws TransformerConfigurationException, FileNotFoundException, TransformerException, Exception {
        // Use the template to create a transformer
        Transformer xformer = postTemplate.newTransformer();
        // Prepare the input and output files
        Source source = new StreamSource(new StringReader(pageContent));
        StringWriter writer = new StringWriter();
        Result result = new StreamResult(writer);
        // Apply the xsl file to the source file and write the result
        // to the output file
        xformer.transform(source, result);
        return writer.toString();
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
