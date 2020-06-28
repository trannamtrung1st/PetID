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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@XmlRootElement(name = "PetBreed")
@NamedQueries({
    @NamedQuery(name = "PetBreed.findAll", query = "SELECT p FROM PetBreed p")
    , @NamedQuery(name = "PetBreed.findByCode", query = "SELECT p FROM PetBreed p WHERE p.code = :code")
    , @NamedQuery(name = "PetBreed.findByName", query = "SELECT p FROM PetBreed p WHERE p.name = :name")
    , @NamedQuery(name = "PetBreed.findByDescription", query = "SELECT p FROM PetBreed p WHERE p.description = :description")
    , @NamedQuery(name = "PetBreed.findByUrl", query = "SELECT p FROM PetBreed p WHERE p.url = :url")
    , @NamedQuery(name = "PetBreed.findByImageUrl", query = "SELECT p FROM PetBreed p WHERE p.imageUrl = :imageUrl")
    , @NamedQuery(name = "PetBreed.findByAvailableUrl", query = "SELECT p FROM PetBreed p WHERE p.availableUrl = :availableUrl")
    , @NamedQuery(name = "PetBreed.findByIsAvailableParsed", query = "SELECT p FROM PetBreed p WHERE p.isAvailableParsed = :isAvailableParsed")
    , @NamedQuery(name = "PetBreed.findByIsBreedImagesParsed", query = "SELECT p FROM PetBreed p WHERE p.isBreedImagesParsed = :isBreedImagesParsed")
    , @NamedQuery(name = "PetBreed.findByDogilyCodeMapping", query = "SELECT p FROM PetBreed p WHERE p.dogilyCodeMapping = :dogilyCodeMapping")})
public class PetBreed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String code;
    @Column(length = 255)
    private String name;
    @Column(length = 2147483647)
    private String description;
    @Column(length = 500)
    private String url;
    @Column(length = 2000)
    private String imageUrl;
    @Column(length = 2000)
    private String availableUrl;
    private Boolean isAvailableParsed;
    private Boolean isBreedImagesParsed;
    @Column(length = 50)
    private String dogilyCodeMapping;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "breedCode")
    private Collection<BreedTrait> breedTraitCollection;
    @JoinColumn(name = "typeName", referencedColumnName = "name", nullable = false)
    @ManyToOne(optional = false)
    private PetType typeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "petBreedCode")
    private Collection<PetPost> petPostCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "breedCode")
    private Collection<BreedAttr> breedAttrCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "breedCode")
    private Collection<BreedInfo> breedInfoCollection;

    public PetBreed() {
    }

    public PetBreed(String code) {
        this.code = code;
    }

    @XmlElement
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @XmlElement
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlElement
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @XmlElement
    public String getAvailableUrl() {
        return availableUrl;
    }

    public void setAvailableUrl(String availableUrl) {
        this.availableUrl = availableUrl;
    }

    @XmlTransient
    public Boolean getIsAvailableParsed() {
        return isAvailableParsed;
    }

    public void setIsAvailableParsed(Boolean isAvailableParsed) {
        this.isAvailableParsed = isAvailableParsed;
    }

    @XmlTransient
    public Boolean getIsBreedImagesParsed() {
        return isBreedImagesParsed;
    }

    public void setIsBreedImagesParsed(Boolean isBreedImagesParsed) {
        this.isBreedImagesParsed = isBreedImagesParsed;
    }

    @XmlTransient
    public String getDogilyCodeMapping() {
        return dogilyCodeMapping;
    }

    public void setDogilyCodeMapping(String dogilyCodeMapping) {
        this.dogilyCodeMapping = dogilyCodeMapping;
    }

    @XmlTransient
    public Collection<BreedTrait> getBreedTraitCollection() {
        return breedTraitCollection;
    }

    public void setBreedTraitCollection(Collection<BreedTrait> breedTraitCollection) {
        this.breedTraitCollection = breedTraitCollection;
    }

    @XmlElement
    public PetType getTypeName() {
        return typeName;
    }

    public void setTypeName(PetType typeName) {
        this.typeName = typeName;
    }

    @XmlTransient
    public Collection<PetPost> getPetPostCollection() {
        return petPostCollection;
    }

    public void setPetPostCollection(Collection<PetPost> petPostCollection) {
        this.petPostCollection = petPostCollection;
    }

    @XmlTransient
    public Collection<BreedAttr> getBreedAttrCollection() {
        return breedAttrCollection;
    }

    public void setBreedAttrCollection(Collection<BreedAttr> breedAttrCollection) {
        this.breedAttrCollection = breedAttrCollection;
    }

    @XmlTransient
    public Collection<BreedInfo> getBreedInfoCollection() {
        return breedInfoCollection;
    }

    public void setBreedInfoCollection(Collection<BreedInfo> breedInfoCollection) {
        this.breedInfoCollection = breedInfoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PetBreed)) {
            return false;
        }
        PetBreed other = (PetBreed) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "petid.data.models.PetBreed[ code=" + code + " ]";
    }

}
