/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.business.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import petid.data.models.BreedInfo;

/**
 *
 * @author TNT
 */
@XmlRootElement
public class BreedInfoDTO extends BaseDTO<BreedInfo> {
    
    private Integer id;
    private String name;
    private String sectionContent;
    private PetBreedDTO breedCode;
    
    public BreedInfoDTO() {
    }
    
    public BreedInfoDTO(BreedInfo entity) {
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
    
    public String getSectionContent() {
        return sectionContent;
    }
    
    public void setSectionContent(String sectionContent) {
        this.sectionContent = sectionContent;
    }
    
    public PetBreedDTO getBreedCode() {
        return breedCode;
    }
    
    public void setBreedCode(PetBreedDTO breedCode) {
        this.breedCode = breedCode;
    }
    
    @Override
    public void copyFrom(BreedInfo entity) {
        setId(entity.getId());
        setName(entity.getName());
        setSectionContent(entity.getSectionContent());
    }
    
}
