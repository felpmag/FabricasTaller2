//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.09.19 a las 11:35:35 AM COT 
//


package featureide;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the featureide package. 
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

    private final static QName _Var_QNAME = new QName("", "var");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: featureide
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FeatureModel }
     * 
     */
    public FeatureModel createFeatureModel() {
        return new FeatureModel();
    }

    /**
     * Create an instance of {@link Struct }
     * 
     */
    public Struct createStruct() {
        return new Struct();
    }

    /**
     * Create an instance of {@link And }
     * 
     */
    public And createAnd() {
        return new And();
    }

    /**
     * Create an instance of {@link Parent }
     * 
     */
    public Parent createParent() {
        return new Parent();
    }

    /**
     * Create an instance of {@link NamedElement }
     * 
     */
    public NamedElement createNamedElement() {
        return new NamedElement();
    }

    /**
     * Create an instance of {@link Alt }
     * 
     */
    public Alt createAlt() {
        return new Alt();
    }

    /**
     * Create an instance of {@link Or }
     * 
     */
    public Or createOr() {
        return new Or();
    }

    /**
     * Create an instance of {@link Feature }
     * 
     */
    public Feature createFeature() {
        return new Feature();
    }

    /**
     * Create an instance of {@link Constraints }
     * 
     */
    public Constraints createConstraints() {
        return new Constraints();
    }

    /**
     * Create an instance of {@link Rule }
     * 
     */
    public Rule createRule() {
        return new Rule();
    }

    /**
     * Create an instance of {@link Imp }
     * 
     */
    public Imp createImp() {
        return new Imp();
    }

    /**
     * Create an instance of {@link Not }
     * 
     */
    public Not createNot() {
        return new Not();
    }

    /**
     * Create an instance of {@link FeatureOrder }
     * 
     */
    public FeatureOrder createFeatureOrder() {
        return new FeatureOrder();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "var")
    public JAXBElement<String> createVar(String value) {
        return new JAXBElement<String>(_Var_QNAME, String.class, null, value);
    }

}
