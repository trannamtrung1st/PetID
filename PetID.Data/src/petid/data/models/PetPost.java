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
    @NamedQuery(name = "PetPost.findAll", query = "SELECT p FROM PetPost p")
    , @NamedQuery(name = "PetPost.findById", query = "SELECT p FROM PetPost p WHERE p.id = :id")
    , @NamedQuery(name = "PetPost.findByCode", query = "SELECT p FROM PetPost p WHERE p.code = :code")
    , @NamedQuery(name = "PetPost.findByName", query = "SELECT p FROM PetPost p WHERE p.name = :name")
    , @NamedQuery(name = "PetPost.findByImageUrl", query = "SELECT p FROM PetPost p WHERE p.imageUrl = :imageUrl")
    , @NamedQuery(name = "PetPost.findByDetailUrl", query = "SELECT p FROM PetPost p WHERE p.detailUrl = :detailUrl")
    , @NamedQuery(name = "PetPost.findByAvailable", query = "SELECT p FROM PetPost p WHERE p.available = :available")})
public class PetPost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Column(length = 255)
    private String code;
    @Column(length = 255)
    private String name;
    @Column(length = 2000)
    private String imageUrl;
    @Column(length = 1000)
    private String detailUrl;
    private Boolean available;
    @JoinColumn(name = "petBreedCode", referencedColumnName = "code", nullable = false)
    @ManyToOne(optional = false)
    private PetBreed petBreedCode;

    public PetPost() {
    }

    public PetPost(Integer id) {
        this.id = id;
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

    public PetBreed getPetBreedCode() {
        return petBreedCode;
    }

    public void setPetBreedCode(PetBreed petBreedCode) {
        this.petBreedCode = petBreedCode;
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
        if (!(object instanceof PetPost)) {
            return false;
        }
        PetPost other = (PetPost) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "petid.data.models.PetPost[ id=" + id + " ]";
    }
    
}
