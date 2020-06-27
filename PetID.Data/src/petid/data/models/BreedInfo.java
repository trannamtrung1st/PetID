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
    @NamedQuery(name = "BreedInfo.findAll", query = "SELECT b FROM BreedInfo b")
    , @NamedQuery(name = "BreedInfo.findById", query = "SELECT b FROM BreedInfo b WHERE b.id = :id")
    , @NamedQuery(name = "BreedInfo.findByName", query = "SELECT b FROM BreedInfo b WHERE b.name = :name")
    , @NamedQuery(name = "BreedInfo.findBySectionContent", query = "SELECT b FROM BreedInfo b WHERE b.sectionContent = :sectionContent")})
public class BreedInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String name;
    @Column(length = 1073741823)
    private String sectionContent;
    @JoinColumn(name = "breedCode", referencedColumnName = "code", nullable = false)
    @ManyToOne(optional = false)
    private PetBreed breedCode;

    public BreedInfo() {
    }

    public BreedInfo(Integer id) {
        this.id = id;
    }

    public BreedInfo(Integer id, String name) {
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

    public String getSectionContent() {
        return sectionContent;
    }

    public void setSectionContent(String sectionContent) {
        this.sectionContent = sectionContent;
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
        if (!(object instanceof BreedInfo)) {
            return false;
        }
        BreedInfo other = (BreedInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "petid.data.models.BreedInfo[ id=" + id + " ]";
    }
    
}
