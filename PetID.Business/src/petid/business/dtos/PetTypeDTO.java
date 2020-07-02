/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.business.dtos;

import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import petid.data.models.PetType;

/**
 *
 * @author TNT
 */
@XmlRootElement
public class PetTypeDTO extends BaseDTO<PetType> {

    private String name;
    private String description;
    private Collection<PetBreedDTO> petBreedCollection;

    public PetTypeDTO() {
    }

    public PetTypeDTO(PetType entity) {
        super(entity);
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

    public Collection<PetBreedDTO> getPetBreedCollection() {
        return petBreedCollection;
    }

    public void setPetBreedCollection(Collection<PetBreedDTO> petBreedCollection) {
        this.petBreedCollection = petBreedCollection;
    }

    @Override
    public void copyFrom(PetType entity) {
        setDescription(entity.getDescription());
        setName(entity.getName());
    }

}
