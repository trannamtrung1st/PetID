/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.parser.dogily;

import java.io.File;
import java.io.FileNotFoundException;
import javax.persistence.EntityManager;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;
import petid.business.services.PetBreedService;
import petid.business.services.PetPostService;
import petid.data.EntityContext;
import petid.data.daos.PetBreedDAO;
import petid.data.daos.PetPostDAO;
import petid.helper.XMLHelper;
import petid.xmlparser.XmlParserConfig;

/**
 *
 * @author TNT
 */
public class Entry {

    public static void main(String[] args) throws JAXBException, TransformerConfigurationException, FileNotFoundException, SAXException {
        ParserConfig parserConfig = XMLHelper.unmarshallDocFile("parser-config.xml", ObjectFactory.class);
        XmlParserConfig xmlParserConfig = XMLHelper.unmarshallDocFile("xml-parser-config.xml", petid.xmlparser.ObjectFactory.class);
        Templates postTemplate = XMLHelper.getTemplates("post-items.xsl");
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema postSchema = schemaFactory.newSchema(new File("post-items.xsd"));
        Validator postValidator = postSchema.newValidator();

        EntityContext context = EntityContext.newInstance();
        EntityManager em = context.getEntityManager();
        PetBreedService petBreedService = new PetBreedService(em, new PetBreedDAO(em));
        PetPostService petPostService = new PetPostService(em, new PetPostDAO(em));
        Parser parser = new Parser(em, petPostService, petBreedService,
                postValidator, postTemplate, xmlParserConfig, parserConfig);
        parser.start();
    }
}
