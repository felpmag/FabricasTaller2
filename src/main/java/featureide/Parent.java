//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.09.19 a las 11:35:35 AM COT 
//


package featureide;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para parent complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="parent"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}namedElement"&gt;
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
 *         &lt;choice&gt;
 *           &lt;element ref="{}and"/&gt;
 *           &lt;element ref="{}alt"/&gt;
 *           &lt;element ref="{}or"/&gt;
 *           &lt;element ref="{}feature"/&gt;
 *           &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parent", propOrder = {
    "andOrAltOrOr"
})
@XmlSeeAlso({
    And.class,
    Or.class,
    Alt.class
})
public class Parent
    extends NamedElement
{

    @XmlElements({
        @XmlElement(name = "and", type = And.class),
        @XmlElement(name = "alt", type = Alt.class),
        @XmlElement(name = "or", type = Or.class),
        @XmlElement(name = "feature", type = Feature.class),
        @XmlElement(name = "description", type = String.class)
    })
    protected List<Object> andOrAltOrOr;

    /**
     * Default no-arg constructor
     * 
     */
    public Parent() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public Parent(final String name, final List<Object> andOrAltOrOr) {
        super(name);
        this.andOrAltOrOr = andOrAltOrOr;
    }

    /**
     * Gets the value of the andOrAltOrOr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the andOrAltOrOr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAndOrAltOrOr().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link And }
     * {@link Alt }
     * {@link Or }
     * {@link Feature }
     * {@link String }
     * 
     * 
     */
    public List<Object> getAndOrAltOrOr() {
        if (andOrAltOrOr == null) {
            andOrAltOrOr = new ArrayList<Object>();
        }
        return this.andOrAltOrOr;
    }

}
