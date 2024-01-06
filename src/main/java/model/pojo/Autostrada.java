//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2024.01.02 alle 04:35:52 PM CET 
//


package model.pojo;

import javax.xml.bind.annotation.*;


/**
 * <p>Classe Java per autostrada complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="autostrada">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tariffario" type="{}tariffario"/>
 *       &lt;/sequence>
 *       &lt;attribute name="nome" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "autostrada", propOrder = {
    "tariffario"
})
public class Autostrada {

    @XmlElement(required = true)
    protected Tariffario tariffario;
    @XmlAttribute(name = "nome", required = true)
    protected String nome;

    /**
     * Recupera il valore della proprietà tariffario.
     * 
     * @return
     *     possible object is
     *     {@link Tariffario }
     *     
     */
    public Tariffario getTariffario() {
        return tariffario;
    }

    /**
     * Imposta il valore della proprietà tariffario.
     * 
     * @param value
     *     allowed object is
     *     {@link Tariffario }
     *     
     */
    public void setTariffario(Tariffario value) {
        this.tariffario = value;
    }

    /**
     * Recupera il valore della proprietà nome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il valore della proprietà nome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

}
