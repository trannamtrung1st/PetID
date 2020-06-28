/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.parser.petfinder;

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
import petid.business.services.PetTypeService;
import petid.data.EntityContext;
import petid.data.daos.BreedAttrDAO;
import petid.data.daos.BreedInfoDAO;
import petid.data.daos.BreedTraitDAO;
import petid.data.daos.PetBreedDAO;
import petid.data.daos.PetTypeDAO;
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
        Templates breedTemplate = XMLHelper.getTemplates("breed-item.xsl");
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema breedSchema = schemaFactory.newSchema(new File("breed-item.xsd"));
        Validator breedValidator = breedSchema.newValidator();

        EntityContext context = EntityContext.newInstance();
        EntityManager em = context.getEntityManager();
        PetTypeService petTypeService = new PetTypeService(em, new PetTypeDAO(em));
        PetBreedService petBreedService = new PetBreedService(em, new PetBreedDAO(em));
        Parser parser = new Parser(em, petTypeService, petBreedService,
                breedValidator, breedTemplate, xmlParserConfig, parserConfig);
        parser.start();
    }
}
