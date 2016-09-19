//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.09.19 a las 11:35:35 AM COT 
//


package featureide;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{}var"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "var"
})
@XmlRootElement(name = "not")
public class Not {

    @XmlElement(required = true)
    protected String var;

    /**
     * Default no-arg constructor
     * 
     */
    public Not() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public Not(final String var) {
        this.var = var;
    }

    /**
     * Obtiene el valor de la propiedad var.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVar() {
        return var;
    }

    /**
     * Define el valor de la propiedad var.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVar(String value) {
        this.var = value;
    }

}
