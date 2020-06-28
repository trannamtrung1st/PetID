/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.business.models;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import petid.data.models.PetBreed;

/**
 *
 * @author TNT
 */
@XmlRootElement(name = "ListBreeds")
public class ListBreeds {

    protected List<PetBreed> list;

    @XmlElementWrapper(name = "list")
    @XmlElement(name = "PetBreed")
    public List<PetBreed> getList() {
        return list;
    }

    public void setList(List<PetBreed> list) {
        this.list = list;
    }

}
