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
import javax.persistence.EntityManager;
import javax.persistence.Query;
import petid.data.daos.BreedAttrDAO;
import petid.data.daos.BreedInfoDAO;
import petid.data.daos.BreedTraitDAO;
import petid.data.daos.PetBreedDAO;
import petid.data.models.BreedAttr;
import petid.data.models.BreedInfo;
import petid.data.models.BreedTrait;
import petid.data.models.PetBreed;
import petid.data.models.PetType;

/**
 *
 * @author TNT
 */
public class PetBreedService {

    protected PetBreedDAO petBreedDAO;
    protected BreedTraitDAO breedTraitDAO;
    protected BreedAttrDAO breedAttrDAO;
    protected BreedInfoDAO breedInfoDAO;
    protected EntityManager entityManager;

    public PetBreedService(EntityManager entityManager, PetBreedDAO petBreedDAO,
            BreedTraitDAO breedTraitDAO,
            BreedInfoDAO breedInfoDAO,
            BreedAttrDAO breedAttrDAO) {
        this.entityManager = entityManager;
        this.petBreedDAO = petBreedDAO;
        this.breedAttrDAO = breedAttrDAO;
        this.breedTraitDAO = breedTraitDAO;
        this.breedInfoDAO = breedInfoDAO;
    }

    public boolean petBreedCodeExists(String code) {
        String sql = "SELECT COUNT(code) FROM PetBreed WHERE code=?code";
        Query query = petBreedDAO.nativeQuery(sql).setParameter("code", code);
        Integer count = (Integer) query.getSingleResult();
        return count > 0;
    }

    public List<String> getAllPetBreedCodes() {
        String sql = "SELECT code FROM PetBreed";
        Query query = petBreedDAO.nativeQuery(sql);
        List<String> list = query.getResultList();
        return list;
    }

    public int deleteAllBreedsTransaction() {
        return petBreedDAO.nativeQuery("DELETE FROM BreedTrait;"
                + "DELETE FROM BreedAttr;"
                + "DELETE FROM BreedInfo;"
                + "DELETE FROM PetBreed;").executeUpdate();
    }

    public PetBreed createPetBreed(PetBreed entity) {
        return petBreedDAO.create(entity);
    }

    public void addPetBreedTrait(PetBreed entity, BreedTrait model) {
        model.setBreedCode(entity);
        Collection<BreedTrait> collections = entity.getBreedTraitCollection();
        if (collections == null) {
            collections = new ArrayList<>();
            entity.setBreedTraitCollection(collections);
        }
        collections.add(model);
    }

    public void setPetType(PetBreed entity, PetType model) {
        entity.setTypeName(model);
        Collection<PetBreed> collections = model.getPetBreedCollection();
        if (collections == null) {
            collections = new ArrayList<>();
            model.setPetBreedCollection(collections);
        }
        collections.add(entity);
    }

    public void addPetBreedInfo(PetBreed entity, BreedInfo model) {
        model.setBreedCode(entity);
        Collection<BreedInfo> collections = entity.getBreedInfoCollection();
        if (collections == null) {
            collections = new ArrayList<>();
            entity.setBreedInfoCollection(collections);
        }
        collections.add(model);
    }

    public void addPetBreedAttr(PetBreed entity, BreedAttr model) {
        model.setBreedCode(entity);
        Collection<BreedAttr> collections = entity.getBreedAttrCollection();
        if (collections == null) {
            collections = new ArrayList<>();
            entity.setBreedAttrCollection(collections);
        }
        collections.add(model);
    }
}
