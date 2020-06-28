
package petid.business.models.xmlschema;

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
 *         &lt;element name="prediction" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="topOutputs">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="topOutput" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="score" type="{http://www.w3.org/2001/XMLSchema}float"/>
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
    "prediction",
    "topOutputs"
})
@XmlRootElement(name = "modelOutput")
public class ModelOutput {

    @XmlElement(required = true)
    protected String prediction;
    @XmlElement(required = true)
    protected ModelOutput.TopOutputs topOutputs;

    /**
     * Gets the value of the prediction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrediction() {
        return prediction;
    }

    /**
     * Sets the value of the prediction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrediction(String value) {
        this.prediction = value;
    }

    /**
     * Gets the value of the topOutputs property.
     * 
     * @return
     *     possible object is
     *     {@link ModelOutput.TopOutputs }
     *     
     */
    public ModelOutput.TopOutputs getTopOutputs() {
        return topOutputs;
    }

    /**
     * Sets the value of the topOutputs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModelOutput.TopOutputs }
     *     
     */
    public void setTopOutputs(ModelOutput.TopOutputs value) {
        this.topOutputs = value;
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
     *         &lt;element name="topOutput" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="score" type="{http://www.w3.org/2001/XMLSchema}float"/>
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
        "topOutput"
    })
    public static class TopOutputs {

        @XmlElement(required = true)
        protected List<ModelOutput.TopOutputs.TopOutput> topOutput;

        /**
         * Gets the value of the topOutput property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the topOutput property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTopOutput().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ModelOutput.TopOutputs.TopOutput }
         * 
         * 
         */
        public List<ModelOutput.TopOutputs.TopOutput> getTopOutput() {
            if (topOutput == null) {
                topOutput = new ArrayList<ModelOutput.TopOutputs.TopOutput>();
            }
            return this.topOutput;
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
         *         &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="score" type="{http://www.w3.org/2001/XMLSchema}float"/>
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
            "label",
            "score"
        })
        public static class TopOutput {

            @XmlElement(required = true)
            protected String label;
            protected float score;

            /**
             * Gets the value of the label property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLabel() {
                return label;
            }

            /**
             * Sets the value of the label property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLabel(String value) {
                this.label = value;
            }

            /**
             * Gets the value of the score property.
             * 
             */
            public float getScore() {
                return score;
            }

            /**
             * Sets the value of the score property.
             * 
             */
            public void setScore(float value) {
                this.score = value;
            }

        }

    }

}
