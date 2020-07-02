/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.business.dtos;

import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import petid.data.models.PetBreed;

/**
 *
 * @author TNT
 */
@XmlRootElement
public class PetBreedDTO extends BaseDTO<PetBreed> {

    private String code;
    private String name;
    private String description;
    private String url;
    private String imageUrl;
    private String availableUrl;
    private Boolean isAvailableParsed;
    private Boolean isBreedImagesParsed;
    private String dogilyCodeMapping;
    private Collection<BreedTraitDTO> breedTraitCollection;
    private PetTypeDTO typeName;
    private Collection<PetPostDTO> petPostCollection;
    private Collection<BreedAttrDTO> breedAttrCollection;
    private Collection<BreedInfoDTO> breedInfoCollection;

    public PetBreedDTO() {
    }

    public PetBreedDTO(PetBreed entity) {
        super(entity);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAvailableUrl() {
        return availableUrl;
    }

    public void setAvailableUrl(String availableUrl) {
        this.availableUrl = availableUrl;
    }

    public Boolean getIsAvailableParsed() {
        return isAvailableParsed;
    }

    public void setIsAvailableParsed(Boolean isAvailableParsed) {
        this.isAvailableParsed = isAvailableParsed;
    }

    public Boolean getIsBreedImagesParsed() {
        return isBreedImagesParsed;
    }

    public void setIsBreedImagesParsed(Boolean isBreedImagesParsed) {
        this.isBreedImagesParsed = isBreedImagesParsed;
    }

    public String getDogilyCodeMapping() {
        return dogilyCodeMapping;
    }

    public void setDogilyCodeMapping(String dogilyCodeMapping) {
        this.dogilyCodeMapping = dogilyCodeMapping;
    }

    @XmlElementWrapper(name = "traits")
    @XmlElement(name = "item")
    public Collection<BreedTraitDTO> getBreedTraitCollection() {
        return breedTraitCollection;
    }

    public void setBreedTraitCollection(Collection<BreedTraitDTO> breedTraitCollection) {
        this.breedTraitCollection = breedTraitCollection;
    }

    public PetTypeDTO getTypeName() {
        return typeName;
    }

    public void setTypeName(PetTypeDTO typeName) {
        this.typeName = typeName;
    }

    @XmlElementWrapper(name = "posts")
    @XmlElement(name = "item")
    public Collection<PetPostDTO> getPetPostCollection() {
        return petPostCollection;
    }

    public void setPetPostCollection(Collection<PetPostDTO> petPostCollection) {
        this.petPostCollection = petPostCollection;
    }

    @XmlElementWrapper(name = "attrs")
    @XmlElement(name = "item")
    public Collection<BreedAttrDTO> getBreedAttrCollection() {
        return breedAttrCollection;
    }

    public void setBreedAttrCollection(Collection<BreedAttrDTO> breedAttrCollection) {
        this.breedAttrCollection = breedAttrCollection;
    }

    @XmlElementWrapper(name = "infos")
    @XmlElement(name = "item")
    public Collection<BreedInfoDTO> getBreedInfoCollection() {
        return breedInfoCollection;
    }

    public void setBreedInfoCollection(Collection<BreedInfoDTO> breedInfoCollection) {
        this.breedInfoCollection = breedInfoCollection;
    }

    @Override
    public void copyFrom(PetBreed entity) {
        setAvailableUrl(entity.getAvailableUrl());
        setCode(entity.getCode());
        setDescription(entity.getDescription());
        setDogilyCodeMapping(entity.getDogilyCodeMapping());
        setImageUrl(entity.getImageUrl());
        setIsAvailableParsed(entity.getIsAvailableParsed());
        setIsBreedImagesParsed(entity.getIsBreedImagesParsed());
        setName(entity.getName());
        setUrl(entity.getUrl());
    }

}
