/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.webapp.apis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.hc.core5.http.HttpStatus;
import petid.business.dtos.BreedAttrDTO;
import petid.business.dtos.BreedInfoDTO;
import petid.business.dtos.BreedTraitDTO;
import petid.business.dtos.ListBreedsDTO;
import petid.business.dtos.PetBreedDTO;
import petid.business.dtos.PetPostDTO;
import petid.business.dtos.PetTypeDTO;
import petid.business.services.PetBreedService;
import petid.data.EntityContext;
import petid.data.daos.PetBreedDAO;
import petid.data.models.BreedAttr;
import petid.data.models.BreedInfo;
import petid.data.models.BreedTrait;
import petid.data.models.PetBreed;
import petid.data.models.PetPost;
import petid.helper.XMLHelper;

/**
 *
 * @author TNT
 */
public class BreedsApiController extends HttpServlet {

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
        response.setContentType("application/xml");
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.isEmpty() || pathInfo.length() == 1) {
            response.setStatus(HttpStatus.SC_NOT_FOUND);
        } else {
            try {
                handleGetBreedDetail(request, response);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    protected void handleGetBreedDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String code = request.getPathInfo().substring(1);
        try (EntityContext eContext = EntityContext.newInstance(); PrintWriter out = response.getWriter()) {
            EntityManager em = eContext.getEntityManager();
            PetBreedService petBreedService = new PetBreedService(em, new PetBreedDAO(em));
            PetBreed entity = petBreedService.findPetBreedByCode(code);
            if (entity == null) {
                response.setStatus(HttpStatus.SC_NOT_FOUND);
                return;
            }
            PetBreedDTO dto = new PetBreedDTO(entity);
            dto.setTypeName(new PetTypeDTO(entity.getTypeName()));
            Collection<BreedTrait> listTraits = entity.getBreedTraitCollection();
            Collection<BreedInfo> listInfos = entity.getBreedInfoCollection();
            Collection<BreedAttr> listAttrs = entity.getBreedAttrCollection();
            Collection<PetPost> listPosts = entity.getPetPostCollection();
            dto.setBreedTraitCollection(Arrays.asList(listTraits.toArray(new BreedTrait[listTraits.size()]))
                    .stream().map(o1 -> new BreedTraitDTO(o1)).collect(Collectors.toList()));
            dto.setBreedInfoCollection(Arrays.asList(listInfos.toArray(new BreedInfo[listInfos.size()]))
                    .stream().map(o1 -> new BreedInfoDTO(o1)).collect(Collectors.toList()));
            dto.setBreedAttrCollection(Arrays.asList(listAttrs.toArray(new BreedAttr[listAttrs.size()]))
                    .stream().map(o1 -> new BreedAttrDTO(o1)).collect(Collectors.toList()));
            dto.setPetPostCollection(Arrays.asList(listPosts.toArray(new PetPost[listPosts.size()]))
                    .stream().map(o1 -> new PetPostDTO(o1)).collect(Collectors.toList()));
            String xml = XMLHelper.marshall(dto, PetBreedDTO.class);
            out.print(xml);
        }
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
