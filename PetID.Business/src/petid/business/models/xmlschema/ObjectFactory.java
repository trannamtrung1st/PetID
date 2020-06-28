
package petid.business.models.xmlschema;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the petid.business.models.xmlschema package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: petid.business.models.xmlschema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ModelOutput }
     * 
     */
    public ModelOutput createModelOutput() {
        return new ModelOutput();
    }

    /**
     * Create an instance of {@link ModelOutput.TopOutputs }
     * 
     */
    public ModelOutput.TopOutputs createModelOutputTopOutputs() {
        return new ModelOutput.TopOutputs();
    }

    /**
     * Create an instance of {@link ModelOutput.TopOutputs.TopOutput }
     * 
     */
    public ModelOutput.TopOutputs.TopOutput createModelOutputTopOutputsTopOutput() {
        return new ModelOutput.TopOutputs.TopOutput();
    }

}
