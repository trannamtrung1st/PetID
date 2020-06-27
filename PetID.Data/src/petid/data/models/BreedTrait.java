/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.data.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TNT
 */
@Entity
@Table(catalog = "PetID", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BreedTrait.findAll", query = "SELECT b FROM BreedTrait b")
    , @NamedQuery(name = "BreedTrait.findById", query = "SELECT b FROM BreedTrait b WHERE b.id = :id")
    , @NamedQuery(name = "BreedTrait.findByName", query = "SELECT b FROM BreedTrait b WHERE b.name = :name")
    , @NamedQuery(name = "BreedTrait.findByValue", query = "SELECT b FROM BreedTrait b WHERE b.value = :value")})
public class BreedTrait implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String name;
    @Column(length = 255)
    private String value;
    @JoinColumn(name = "breedCode", referencedColumnName = "code", nullable = false)
    @ManyToOne(optional = false)
    private PetBreed breedCode;

    public BreedTrait() {
    }

    public BreedTrait(Integer id) {
        this.id = id;
    }

    public BreedTrait(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public PetBreed getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(PetBreed breedCode) {
        this.breedCode = breedCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BreedTrait)) {
            return false;
        }
        BreedTrait other = (BreedTrait) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "petid.data.models.BreedTrait[ id=" + id + " ]";
    }
    
}
