/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.business;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import petid.data.daos.PetTypeDAO;
import petid.data.models.PetType;

/**
 *
 * @author TNT
 */
public class PetTypeService {

    protected PetTypeDAO petTypeDAO;
    protected EntityManager entityManager;

    public PetTypeService(EntityManager entityManager, PetTypeDAO petTypeDAO) {
        this.entityManager = entityManager;
        this.petTypeDAO = petTypeDAO;
    }

    public boolean petTypeNameExists(String name) {
        String sql = "SELECT COUNT(name) FROM PetType WHERE name=?name";
        Query query = petTypeDAO.nativeQuery(sql).setParameter("name", name);
        Integer count = (Integer) query.getSingleResult();
        return count > 0;
    }

    public int deleteAllPetTypes() {
        return petTypeDAO.nativeQuery("DELETE FROM PetType").executeUpdate();
    }

    public PetType findPetTypeByName(String name){
        String sql = "SELECT * FROM PetType WHERE name=?name";
        Query query = petTypeDAO.nativeQuery(sql, PetType.class).setParameter("name", name);
        List<PetType> list = query.getResultList();
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public PetType createPetType(PetType entity) {
        return petTypeDAO.create(entity);
    }
}
