/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.webapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import petid.business.models.xmlschema.ModelOutput;
import petid.business.services.PetBreedService;
import petid.data.EntityContext;
import petid.data.daos.PetBreedDAO;
import petid.data.models.PetBreed;
import petid.webapp.Constants;

/**
 *
 * @author TNT
 */
public class BreedController extends HttpServlet {

    protected static final String BREED = "/breed.jsp";
    protected static final String INDEX = "/";
    public static final String BREED_CODE = "breedCode";

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
        dispatch(request, response);
    }

    protected void dispatch(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = BREED;
        String redir = INDEX;
        ServletContext sContext = getServletContext();
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.isEmpty() || pathInfo.length() == 1) {
            action = null;
        } else {
            String breedCode = pathInfo.substring(1);
            request.setAttribute(BREED_CODE, breedCode);
        }
        if (action != null) {
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher(action).forward(request, response);
        } else {
            response.sendRedirect(sContext.getContextPath() + redir);
        }
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
