/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.business.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import petid.data.models.BreedAttr;

/**
 *
 * @author TNT
 */
@XmlRootElement
public class BreedAttrDTO extends BaseDTO<BreedAttr> {

    private Integer id;
    private String name;
    private String value;
    private PetBreedDTO breedCode;

    public BreedAttrDTO() {
    }

    public BreedAttrDTO(BreedAttr entity) {
        super(entity);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PetBreedDTO getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(PetBreedDTO breedCode) {
        this.breedCode = breedCode;
    }

    @Override
    public void copyFrom(BreedAttr entity) {
        setId(entity.getId());
        setName(entity.getName());
        setValue(entity.getValue());
    }

}
