/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.business.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import petid.data.models.PetPost;

/**
 *
 * @author TNT
 */
@XmlRootElement
public class PetPostDTO extends BaseDTO<PetPost> {

    private Integer id;
    private String code;
    private String name;
    private String imageUrl;
    private String detailUrl;
    private Boolean available;
    private PetBreedDTO petBreedCode;

    public PetPostDTO() {
    }

    public PetPostDTO(PetPost entity) {
        super(entity);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public PetBreedDTO getPetBreedCode() {
        return petBreedCode;
    }

    public void setPetBreedCode(PetBreedDTO petBreedCode) {
        this.petBreedCode = petBreedCode;
    }

    @Override
    public void copyFrom(PetPost entity) {
        setAvailable(entity.getAvailable());
        setCode(entity.getCode());
        setDetailUrl(entity.getDetailUrl());
        setId(entity.getId());
        setImageUrl(entity.getImageUrl());
        setName(entity.getName());
    }

}
