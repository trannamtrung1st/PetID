
package petid.parser.petfinder;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="getAllBreeds" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="allBreedsPages">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="page" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="breed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="breedLinksXPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "getAllBreeds",
    "allBreedsPages",
    "breedLinksXPath"
})
@XmlRootElement(name = "parserConfig")
public class ParserConfig {

    @XmlElement(required = true)
    protected String baseUrl;
    protected boolean getAllBreeds;
    @XmlElement(required = true)
    protected ParserConfig.AllBreedsPages allBreedsPages;
    @XmlElement(required = true)
    protected String breedLinksXPath;

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
     * Gets the value of the getAllBreeds property.
     * 
     */
    public boolean isGetAllBreeds() {
        return getAllBreeds;
    }

    /**
     * Sets the value of the getAllBreeds property.
     * 
     */
    public void setGetAllBreeds(boolean value) {
        this.getAllBreeds = value;
    }

    /**
     * Gets the value of the allBreedsPages property.
     * 
     * @return
     *     possible object is
     *     {@link ParserConfig.AllBreedsPages }
     *     
     */
    public ParserConfig.AllBreedsPages getAllBreedsPages() {
        return allBreedsPages;
    }

    /**
     * Sets the value of the allBreedsPages property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParserConfig.AllBreedsPages }
     *     
     */
    public void setAllBreedsPages(ParserConfig.AllBreedsPages value) {
        this.allBreedsPages = value;
    }

    /**
     * Gets the value of the breedLinksXPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBreedLinksXPath() {
        return breedLinksXPath;
    }

    /**
     * Sets the value of the breedLinksXPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBreedLinksXPath(String value) {
        this.breedLinksXPath = value;
    }


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
     *         &lt;element name="page" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="breed" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "page"
    })
    public static class AllBreedsPages {

        @XmlElement(required = true)
        protected List<ParserConfig.AllBreedsPages.Page> page;

        /**
         * Gets the value of the page property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the page property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPage().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ParserConfig.AllBreedsPages.Page }
         * 
         * 
         */
        public List<ParserConfig.AllBreedsPages.Page> getPage() {
            if (page == null) {
                page = new ArrayList<ParserConfig.AllBreedsPages.Page>();
            }
            return this.page;
        }


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
         *         &lt;element name="breed" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "breed",
            "url"
        })
        public static class Page {

            @XmlElement(required = true)
            protected String breed;
            @XmlElement(required = true)
            protected String url;

            /**
             * Gets the value of the breed property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBreed() {
                return breed;
            }

            /**
             * Sets the value of the breed property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBreed(String value) {
                this.breed = value;
            }

            /**
             * Gets the value of the url property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUrl() {
                return url;
            }

            /**
             * Sets the value of the url property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUrl(String value) {
                this.url = value;
            }

        }

    }

}
