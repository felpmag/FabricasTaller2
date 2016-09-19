//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.09.19 a las 11:35:35 AM COT 
//


package featureide;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{}struct"/&gt;
 *         &lt;element ref="{}constraints"/&gt;
 *         &lt;element name="comments" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element ref="{}featureOrder"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="chosenLayoutAlgorithm" type="{http://www.w3.org/2001/XMLSchema}integer" default="4" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "struct",
    "constraints",
    "comments",
    "featureOrder"
})
@XmlRootElement(name = "featureModel")
public class FeatureModel {

    @XmlElement(required = true)
    protected Struct struct;
    @XmlElement(required = true)
    protected Constraints constraints;
    @XmlElement(required = true)
    protected String comments;
    @XmlElement(required = true)
    protected FeatureOrder featureOrder;
    @XmlAttribute(name = "chosenLayoutAlgorithm")
    protected BigInteger chosenLayoutAlgorithm;

    /**
     * Default no-arg constructor
     * 
     */
    public FeatureModel() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public FeatureModel(final Struct struct, final Constraints constraints, final String comments, final FeatureOrder featureOrder, final BigInteger chosenLayoutAlgorithm) {
        this.struct = struct;
        this.constraints = constraints;
        this.comments = comments;
        this.featureOrder = featureOrder;
        this.chosenLayoutAlgorithm = chosenLayoutAlgorithm;
    }

    /**
     * Obtiene el valor de la propiedad struct.
     * 
     * @return
     *     possible object is
     *     {@link Struct }
     *     
     */
    public Struct getStruct() {
        return struct;
    }

    /**
     * Define el valor de la propiedad struct.
     * 
     * @param value
     *     allowed object is
     *     {@link Struct }
     *     
     */
    public void setStruct(Struct value) {
        this.struct = value;
    }

    /**
     * Obtiene el valor de la propiedad constraints.
     * 
     * @return
     *     possible object is
     *     {@link Constraints }
     *     
     */
    public Constraints getConstraints() {
        return constraints;
    }

    /**
     * Define el valor de la propiedad constraints.
     * 
     * @param value
     *     allowed object is
     *     {@link Constraints }
     *     
     */
    public void setConstraints(Constraints value) {
        this.constraints = value;
    }

    /**
     * Obtiene el valor de la propiedad comments.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComments() {
        return comments;
    }

    /**
     * Define el valor de la propiedad comments.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComments(String value) {
        this.comments = value;
    }

    /**
     * Obtiene el valor de la propiedad featureOrder.
     * 
     * @return
     *     possible object is
     *     {@link FeatureOrder }
     *     
     */
    public FeatureOrder getFeatureOrder() {
        return featureOrder;
    }

    /**
     * Define el valor de la propiedad featureOrder.
     * 
     * @param value
     *     allowed object is
     *     {@link FeatureOrder }
     *     
     */
    public void setFeatureOrder(FeatureOrder value) {
        this.featureOrder = value;
    }

    /**
     * Obtiene el valor de la propiedad chosenLayoutAlgorithm.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getChosenLayoutAlgorithm() {
        if (chosenLayoutAlgorithm == null) {
            return new BigInteger("4");
        } else {
            return chosenLayoutAlgorithm;
        }
    }

    /**
     * Define el valor de la propiedad chosenLayoutAlgorithm.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setChosenLayoutAlgorithm(BigInteger value) {
        this.chosenLayoutAlgorithm = value;
    }

}
