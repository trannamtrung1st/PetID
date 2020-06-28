/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.parser.googleimage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import petid.business.services.PetBreedService;
import petid.data.models.PetBreed;
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
    protected PetBreedService petBreedService;
    protected EntityManager entityManager;
    protected List<PetBreed> unparsedList;

    public Parser(
            EntityManager entityManager,
            PetBreedService petBreedService,
            XmlParserConfig xmlParserConfig,
            ParserConfig parserConfig) {
        this.petBreedService = petBreedService;
        this.xmlParserConfig = xmlParserConfig;
        this.parserConfig = parserConfig;
        this.xpath = XMLHelper.getXPath();
        this.entityManager = entityManager;
    }

    public void start() {
        try {
            unparsedList = petBreedService.queryAllUnparsedImagesBreeds((Object[] in) -> {
                PetBreed entity = new PetBreed();
                entity.setCode((String) in[0]);
                entity.setName((String) in[1]);
                return entity;
            }, "code", "name");
            searchLinks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void searchLinks() {
        PetBreed currentBreed = null;
        for (PetBreed entity : unparsedList) {
            try {
                currentBreed = entity;
                String search = HttpHelper.encodeUrl(entity.getName());
                String link = parserConfig.getSearchPlaceholder().replace("{search}", search);
                System.out.println("Start download images for: " + entity.getName());
                String pageContent = preprocess(link);
//                FileHelper.writeToFile(pageContent, "temp.html");
                Document doc = XMLHelper.parseDOMFromString(pageContent);
                downloadImages(entity, doc);
                finishParsedBreedImages(entity);
                System.out.println("Finish download images for: " + entity.getName());
                System.out.println("------------------------");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void finishParsedBreedImages(PetBreed entity) {
        entityManager.getTransaction().begin();
        entity = petBreedService.findPetBreedByCode(entity.getCode());
        petBreedService.updatePetBreedImageParsed(entity, true);
        entityManager.getTransaction().commit();
    }

    protected void downloadImages(PetBreed entity, Document doc) throws XPathExpressionException, IOException {
        String folder = parserConfig.getSaveFolder() + "\\" + entity.getCode();
        Files.createDirectories(Paths.get(folder));
        //parse DOM and use XPath to get links
        NodeList linkNodes = (NodeList) xpath.evaluate(parserConfig.getImgXPath(), doc, XPathConstants.NODESET);
        System.out.println(entity.getName() + ": " + linkNodes.getLength());
        for (int i = 0; i < linkNodes.getLength(); i++) {
            String link = linkNodes.item(i).getNodeValue();
            link = resolveFullUrl(link);
            System.out.println("downloading: " + link);
            HttpHelper.download(link, folder + "\\" + i + ".jpg");
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
