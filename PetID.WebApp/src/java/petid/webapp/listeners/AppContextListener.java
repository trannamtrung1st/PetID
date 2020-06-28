/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.webapp.listeners;

import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import petid.business.Settings;
import petid.business.models.ListBreeds;
import petid.business.services.PetBreedService;
import petid.data.EntityContext;
import petid.data.daos.PetBreedDAO;
import petid.data.models.PetBreed;
import petid.helper.FileHelper;
import petid.helper.XMLHelper;
import petid.webapp.Constants;

/**
 * Web application lifecycle listener.
 *
 * @author TNT
 */
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sContext = sce.getServletContext();
        Settings.baseApiUrl = sContext.getInitParameter("baseApiUrl");
        try (EntityContext eContext = EntityContext.newInstance()) {
            EntityManager em = eContext.getEntityManager();

            //start --- cache PetBreeds
            PetBreedService petBreedService = new PetBreedService(em, new PetBreedDAO(em));
            List<PetBreed> entities = petBreedService.getAllPetBreeds();
            ListBreeds list = new ListBreeds();
            list.setList(entities);
            String xml = XMLHelper.marshall(list, ListBreeds.class, PetBreed.class);
//            FileHelper.writeToFile(xml, "T:\\FPT\\STUDY\\SUMMER2020\\PRX\\Project\\PetID\\PetID.WebApp\\temp.xml");
            sContext.setAttribute(Constants.BREEDS_XML_CACHE_NAME, xml);
            sContext.setAttribute(Constants.BREEDS_COUNT_CACHE_NAME, entities.size());
            //end --- cache PetBreeds
        } catch (Exception ex) {
            Logger.getLogger(AppContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
