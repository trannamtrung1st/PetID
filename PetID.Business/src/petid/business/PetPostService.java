/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import petid.data.daos.BreedAttrDAO;
import petid.data.daos.BreedInfoDAO;
import petid.data.daos.BreedTraitDAO;
import petid.data.daos.PetPostDAO;
import petid.data.models.BreedAttr;
import petid.data.models.BreedInfo;
import petid.data.models.BreedTrait;
import petid.data.models.PetBreed;
import petid.data.models.PetPost;
import petid.data.models.PetType;

/**
 *
 * @author TNT
 */
public class PetPostService {

    protected PetPostDAO petPostDAO;
    protected EntityManager entityManager;

    public PetPostService(EntityManager entityManager, PetPostDAO petPostDAO) {
        this.entityManager = entityManager;
        this.petPostDAO = petPostDAO;
    }

    public PetPost findPetPostByCode(String code) {
        String sql = "SELECT * FROM PetType WHERE code=?code";
        Query query = petPostDAO.nativeQuery(sql, PetPost.class).setParameter("code", code);
        List<PetPost> list = query.getResultList();
        return list.size() > 0 ? list.get(0) : null;
    }

    public boolean petPostCodeExists(String code) {
        String sql = "SELECT COUNT(code) FROM PetPost WHERE code=?code";
        Query query = petPostDAO.nativeQuery(sql).setParameter("code", code);
        Integer count = (Integer) query.getSingleResult();
        return count > 0;
    }

    public List<String> getAllPetPostCodes() {
        String sql = "SELECT code FROM PetPost";
        Query query = petPostDAO.nativeQuery(sql);
        List<String> list = query.getResultList();
        return list;
    }

    public PetPost createPetPost(PetPost entity) {
        return petPostDAO.create(entity);
    }

    public void setPetBreed(PetPost entity, PetBreed model) {
        entity.setPetBreedCode(model);
        Collection<PetPost> collections = model.getPetPostCollection();
        if (collections == null) {
            collections = new ArrayList<>();
            model.setPetPostCollection(collections);
        }
        collections.add(entity);
    }

}
