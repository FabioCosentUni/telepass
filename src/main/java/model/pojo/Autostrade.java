//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2024.01.02 alle 04:35:52 PM CET 
//


package model.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="autostrada" type="{}autostrada" maxOccurs="unbounded" minOccurs="0"/>
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
    "autostrada"
})
@XmlRootElement(name = "autostrade")
public class Autostrade {

    protected List<Autostrada> autostrada;

    /**
     * Gets the value of the autostrada property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the autostrada property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAutostrada().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Autostrada }
     * 
     * 
     */
    public List<Autostrada> getAutostrada() {
        if (autostrada == null) {
            autostrada = new ArrayList<Autostrada>();
        }
        return this.autostrada;
    }

    /**
     * Sets the value of the autostrada property.
     * 
     * @param autostrada
     *     allowed object is
     *     {@link Autostrada }
     *     
     */
    public void setAutostrada(List<Autostrada> autostrada) {
        this.autostrada = autostrada;
    }

}
