/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.webapp.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.JAXBException;
import petid.business.models.xmlschema.ModelOutput;
import petid.business.services.PetBreedService;
import petid.data.EntityContext;
import petid.data.daos.PetBreedDAO;
import petid.webapp.Constants;

/**
 *
 * @author TNT
 */
@MultipartConfig
public class IndexController extends BaseController {

    protected final String INDEX = "index.jsp";
    protected final String ACTION_SUBMIT_IMAGE = "submit_image";

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        dispatch(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (ACTION_SUBMIT_IMAGE.equals(action)) {
            handleSubmitImage(request, response);
        } else {
            throw new RuntimeException("Empty action");
        }
    }

    protected void handleSubmitImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        InputStream fileContent = filePart.getInputStream();
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        try (EntityContext context = EntityContext.newInstance()) {
            EntityManager em = context.getEntityManager();
            PetBreedService petBreedService = new PetBreedService(em, new PetBreedDAO(em));
            try {
                ModelOutput output = petBreedService.predictPetIdByImage(fileContent, fileName, 5);
                System.out.println(output.getPrediction());
            } catch (JAXBException ex) {
                throw new RuntimeException(ex);
            }
        }
        dispatch(request, response);
    }

    protected void dispatch(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletContext sContext = getServletContext();
        Integer count = (Integer) sContext.getAttribute(Constants.BREEDS_COUNT_CACHE_NAME);
        String listXml = (String) sContext.getAttribute(Constants.BREEDS_XML_CACHE_NAME);
        String xsl = (String) sContext.getAttribute(Constants.BREEDS_XSL_CACHE_NAME);
        String topXsl = (String) sContext.getAttribute(Constants.TOP_OUTPUT_XSL_CACHE_NAME);

        request.setAttribute("count", count);
        request.setAttribute("listXml", listXml);
        request.setAttribute("xsl", xsl);
        request.setAttribute("topXsl", topXsl);

        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher(INDEX).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
