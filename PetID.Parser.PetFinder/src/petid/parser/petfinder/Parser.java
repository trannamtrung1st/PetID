/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.parser.petfinder;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import petid.helper.FileHelper;
import petid.helper.HttpHelper;
import petid.helper.XMLHelper;
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

    public Parser(
            XmlParserConfig xmlParserConfig,
            ParserConfig parserConfig) {
        this.xmlParserConfig = xmlParserConfig;
        this.parserConfig = parserConfig;
        this.xpath = XMLHelper.getXPath();
        this.breedLinks = new HashSet<>();
    }

    public void start() {
        try {
            if (parserConfig.isGetAllBreeds()) {
                getAllBreeds();
                System.out.println("All breeds: " + breedLinks.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllBreeds() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        for (ParserConfig.AllBreedsPages.Page p : parserConfig.getAllBreedsPages().getPage()) {
            String content = preprocess(p.getUrl());
            FileHelper.writeToFile(content, "temp.html");
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
