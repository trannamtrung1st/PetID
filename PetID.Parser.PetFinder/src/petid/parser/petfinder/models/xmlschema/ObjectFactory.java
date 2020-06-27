
package petid.parser.petfinder.models.xmlschema;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the petid.parser.petfinder.models.xmlschema package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: petid.parser.petfinder.models.xmlschema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BreedItem }
     * 
     */
    public BreedItem createBreedItem() {
        return new BreedItem();
    }

    /**
     * Create an instance of {@link BreedItem.Sections }
     * 
     */
    public BreedItem.Sections createBreedItemSections() {
        return new BreedItem.Sections();
    }

    /**
     * Create an instance of {@link BreedItem.Attributes }
     * 
     */
    public BreedItem.Attributes createBreedItemAttributes() {
        return new BreedItem.Attributes();
    }

    /**
     * Create an instance of {@link BreedItem.Traits }
     * 
     */
    public BreedItem.Traits createBreedItemTraits() {
        return new BreedItem.Traits();
    }

    /**
     * Create an instance of {@link BreedItem.Sections.Item }
     * 
     */
    public BreedItem.Sections.Item createBreedItemSectionsItem() {
        return new BreedItem.Sections.Item();
    }

    /**
     * Create an instance of {@link BreedItem.Attributes.Item }
     * 
     */
    public BreedItem.Attributes.Item createBreedItemAttributesItem() {
        return new BreedItem.Attributes.Item();
    }

    /**
     * Create an instance of {@link BreedItem.Traits.Item }
     * 
     */
    public BreedItem.Traits.Item createBreedItemTraitsItem() {
        return new BreedItem.Traits.Item();
    }

}
