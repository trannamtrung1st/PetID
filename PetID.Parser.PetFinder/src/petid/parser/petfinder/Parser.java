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
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import petid.helper.FileHelper;
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
    protected Set<String> breedLinks;
    protected Templates breedTemplate;
    protected Validator breedValidator;

    public Parser(
            Validator breedValidator,
            Templates breedTemplate,
            XmlParserConfig xmlParserConfig,
            ParserConfig parserConfig) {
        this.xmlParserConfig = xmlParserConfig;
        this.parserConfig = parserConfig;
        this.xpath = XMLHelper.getXPath();
        this.breedLinks = new HashSet<>();
        this.breedTemplate = breedTemplate;
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

    protected void getAllBreeds() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        for (ParserConfig.AllBreedsPages.Page p : parserConfig.getAllBreedsPages().getPage()) {
            String content = preprocess(p.getUrl());
//            FileHelper.writeToFile(content, "temp.html");
            //parse DOM and use XPath to get links
            Document doc = XMLHelper.parseDOMFromString(content);
            NodeList linkNodes = (NodeList) xpath.evaluate(parserConfig.getBreedLinksXPath(), doc, XPathConstants.NODESET);
            System.out.println(p.getPetType() + ": " + linkNodes.getLength());
            for (int i = 0; i < linkNodes.getLength(); i++) {
                String link = linkNodes.item(i).getNodeValue();
                link = resolveFullUrl(link);
                breedLinks.add(link);
            }
        }
    }

    protected void parseBreedLinks() {
        BreedItem currentBreed = null;
        for (String bLinks : breedLinks) {
            try {
                System.out.println("Start parsing page: " + bLinks);
                String pageContent = preprocess(bLinks);
//                FileHelper.writeToFile(pageContent, "temp.html");
                String modelXml = transform(bLinks, pageContent);
                validateBreedXml(modelXml);
//                FileHelper.writeToFile(modelXml, "temp.xml");
                currentBreed = XMLHelper.unmarshallDocXml(modelXml, petid.parser.petfinder.models.xmlschema.ObjectFactory.class);
                System.out.println(currentBreed.getBreedName());
//                processJobItem(currentBreed);
                System.out.println("Finish parsing page: " + bLinks);
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
