/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.business;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBException;
import petid.business.models.xmlschema.ModelOutput;
import petid.business.services.PetBreedService;
import petid.data.EntityContext;
import petid.data.daos.PetBreedDAO;
import petid.data.models.PetBreed;

/**
 *
 * @author TNT
 */
public class Entry {

    public static void main(String[] args) throws FileNotFoundException, IOException, JAXBException {
        EntityContext context = EntityContext.newInstance();
        EntityManager em = context.getEntityManager();
        PetBreedService petBreedService = new PetBreedService(em, new PetBreedDAO(em));
        Settings.baseApiUrl = "http://localhost:61501";
        ModelOutput output = petBreedService.predictPetIdByImage(new FileInputStream(
                "T:\\FPT\\STUDY\\SUMMER2020\\PRX\\Project\\PetID\\PetID.ImageClassification\\PetID.ImageClassificationML.ConsoleApp\\Data\\test\\1.jpg"),
                "1.jpg", 5);
        System.out.println(output.getPrediction());
        for (ModelOutput.TopOutputs.TopOutput o : output.getTopOutputs().getTopOutput()) {
            PetBreed breed = petBreedService.findPetBreedByCode(o.getLabel());
            System.out.println(breed.getName() + " - " + o.getScore());
        }
    }

}
