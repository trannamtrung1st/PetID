/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.business.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.bind.JAXBException;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import petid.business.Settings;
import petid.business.models.xmlschema.ModelOutput;
import petid.business.models.xmlschema.ObjectFactory;
import petid.data.daos.PetBreedDAO;
import petid.data.models.BreedAttr;
import petid.data.models.BreedInfo;
import petid.data.models.BreedTrait;
import petid.data.models.PetBreed;
import petid.data.models.PetType;
import petid.helper.HttpHelper;
import petid.helper.StreamHelper;
import petid.helper.XMLHelper;

/**
 *
 * @author TNT
 */
public class PetBreedService {

    protected PetBreedDAO petBreedDAO;
    protected EntityManager entityManager;

    public PetBreedService(EntityManager entityManager, PetBreedDAO petBreedDAO) {
        this.entityManager = entityManager;
        this.petBreedDAO = petBreedDAO;
    }

    public PetBreed findPetBreedByCode(String code) {
        String sql = "SELECT * FROM PetBreed WHERE code=?code";
        Query query = petBreedDAO.nativeQuery(sql, PetBreed.class).setParameter("code", code);
        List<PetBreed> list = query.getResultList();
        return list.size() > 0 ? list.get(0) : null;
    }

    public boolean petBreedCodeExists(String code) {
        String sql = "SELECT COUNT(code) FROM PetBreed WHERE code=?code";
        Query query = petBreedDAO.nativeQuery(sql).setParameter("code", code);
        Integer count = (Integer) query.getSingleResult();
        return count > 0;
    }

    public <In, T> List<T> queryAllUnparsedImagesBreeds(Function<In, T> mapping, String... fields) {
        String fieldJoin = String.join(",", fields);
        String sql = "SELECT " + fieldJoin + " FROM PetBreed where isBreedImagesParsed=0";
        Query query = petBreedDAO.nativeQuery(sql);
        List<In> list = query.getResultList();
        return list.stream().map(mapping).collect(Collectors.toList());
    }

    public List<PetBreed> getAllPetBreeds() {
        String sql = "SELECT * FROM PetBreed";
        Query query = petBreedDAO.nativeQuery(sql, PetBreed.class);
        List<PetBreed> list = query.getResultList();
        return list;
    }

    public List<String> getAllPetBreedCodes() {
        String sql = "SELECT code FROM PetBreed";
        Query query = petBreedDAO.nativeQuery(sql);
        List<String> list = query.getResultList();
        return list;
    }

    public List<PetBreed> getAllPetBreedsHasDogilyMapping() {
        String sql = "SELECT * FROM PetBreed WHERE dogilyCodeMapping IS NOT NULL";
        Query query = petBreedDAO.nativeQuery(sql, PetBreed.class);
        List<PetBreed> list = query.getResultList();
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

    public void updatePetBreedImageParsed(PetBreed entity, boolean value) {
        entity.setIsBreedImagesParsed(value);
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

    public ModelOutput predictPetIdByImage(InputStream is, String fileName, int topCount) throws IOException, JAXBException {
        String url = Settings.baseApiUrl + "/api/pet-id?top_count=" + topCount;
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder = HttpHelper.addFileUpload(builder, "file", is, fileName);
        HttpEntity entity = builder.build();
        Response resp = Request.post(url).addHeader("Accept", "application/xml").body(entity).execute();
        CloseableHttpResponse httpResp = (CloseableHttpResponse) resp.returnResponse();
        String reason = httpResp.getReasonPhrase();
        String contentStr = StreamHelper.readStringFromStream(httpResp.getEntity().getContent());
        if (httpResp.getCode() == HttpStatus.SC_OK) {
            ModelOutput output = XMLHelper.unmarshallDocXml(contentStr, ObjectFactory.class);
            return output;
        }
        return null;
    }
}
