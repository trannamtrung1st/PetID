
package petid.parser.googleimage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="baseUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="saveFolder" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="imgXPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="searchPlaceholder" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "baseUrl",
    "saveFolder",
    "imgXPath",
    "searchPlaceholder"
})
@XmlRootElement(name = "parserConfig")
public class ParserConfig {

    @XmlElement(required = true)
    protected String baseUrl;
    @XmlElement(required = true)
    protected String saveFolder;
    @XmlElement(required = true)
    protected String imgXPath;
    @XmlElement(required = true)
    protected String searchPlaceholder;

    /**
     * Gets the value of the baseUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * Sets the value of the baseUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseUrl(String value) {
        this.baseUrl = value;
    }

    /**
     * Gets the value of the saveFolder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaveFolder() {
        return saveFolder;
    }

    /**
     * Sets the value of the saveFolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaveFolder(String value) {
        this.saveFolder = value;
    }

    /**
     * Gets the value of the imgXPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImgXPath() {
        return imgXPath;
    }

    /**
     * Sets the value of the imgXPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImgXPath(String value) {
        this.imgXPath = value;
    }

    /**
     * Gets the value of the searchPlaceholder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchPlaceholder() {
        return searchPlaceholder;
    }

    /**
     * Sets the value of the searchPlaceholder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchPlaceholder(String value) {
        this.searchPlaceholder = value;
    }

}
