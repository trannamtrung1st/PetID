/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.business.dtos;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TNT
 */
@XmlRootElement(name = "ListBreeds")
public class ListBreedsDTO {

    protected List<PetBreedDTO> list;

    @XmlElementWrapper(name = "list")
    @XmlElement(name = "PetBreed")
    public List<PetBreedDTO> getList() {
        return list;
    }

    public void setList(List<PetBreedDTO> list) {
        this.list = list;
    }

}
