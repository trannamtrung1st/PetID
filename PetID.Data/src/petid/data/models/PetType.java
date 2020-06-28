/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.data.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TNT
 */
@Entity
@Table(catalog = "PetID", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PetType.findAll", query = "SELECT p FROM PetType p")
    , @NamedQuery(name = "PetType.findByName", query = "SELECT p FROM PetType p WHERE p.name = :name")
    , @NamedQuery(name = "PetType.findByDescription", query = "SELECT p FROM PetType p WHERE p.description = :description")})
public class PetType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String name;
    @Column(length = 500)
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeName")
    private Collection<PetBreed> petBreedCollection;

    public PetType() {
    }

    public PetType(String name) {
        this.name = name;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<PetBreed> getPetBreedCollection() {
        return petBreedCollection;
    }

    public void setPetBreedCollection(Collection<PetBreed> petBreedCollection) {
        this.petBreedCollection = petBreedCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PetType)) {
            return false;
        }
        PetType other = (PetType) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "petid.data.models.PetType[ name=" + name + " ]";
    }

}
