/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.parser.googleimage;

import java.io.File;
import javax.persistence.EntityManager;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Templates;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
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

    public static void main(String[] args) throws Exception {
        ParserConfig parserConfig = XMLHelper.unmarshallDocFile("parser-config.xml", ObjectFactory.class);
        XmlParserConfig xmlParserConfig = XMLHelper.unmarshallDocFile("xml-parser-config.xml", petid.xmlparser.ObjectFactory.class);
        EntityContext context = EntityContext.newInstance();
        EntityManager em = context.getEntityManager();
        PetBreedService petBreedService = new PetBreedService(em, new PetBreedDAO(em));
        Parser parser = new Parser(em, petBreedService, xmlParserConfig, parserConfig);
        parser.start();
    }
}
