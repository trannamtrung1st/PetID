/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.webapp.listeners;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import petid.business.Settings;
import petid.business.services.PetBreedService;
import petid.data.EntityContext;
import petid.data.daos.PetBreedDAO;
import petid.data.models.PetBreed;
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
        ServletContext context = sce.getServletContext();
        Settings.baseApiUrl = context.getInitParameter("baseApiUrl");
        try (EntityContext eContext = EntityContext.newInstance()) {
            EntityManager em = eContext.getEntityManager();
            PetBreedService petBreedService = new PetBreedService(em, new PetBreedDAO(em));
            List<PetBreed> entities = petBreedService.getAllPetBreeds();
            String xml = XMLHelper.marshall(entities, PetBreed.class);
            context.setAttribute(Constants.BREEDS_CACHE_NAME, xml);
        } catch (Exception ex) {
            Logger.getLogger(AppContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
