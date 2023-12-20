package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table (name="TB_METODO_PAG")
public class MethodPayment implements Serializable {

    @Id
    @Column(name="NUM_CARTA_PK", nullable = false)
    private String numCartaPK;

    @Column(name="NOME_PRP", nullable = false)
    private String nomeProp;

    @Column(name="COGNOME_PRP", nullable = false)
    private String cognomePrp;

    @Column(name="SCADENZA", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date scadenza;

    @Column(name="CVC", nullable = false)
    private String cvc;

    @Column(name="TIPOLOGIA", nullable = false)
    private String tipologia;


    public MethodPayment(String numCartaPK, String nomePrp, String cognomePrp, Date scadenza, String cvc, String tipologia) {
        this.numCartaPK = numCartaPK;
        this.nomeProp = nomePrp;
        this.cognomePrp = cognomePrp;
        this.scadenza = scadenza;
        this.cvc = cvc;
        this.tipologia = tipologia;
    }

    public MethodPayment() {}

    public String getNumCartaPK() {
        return numCartaPK;
    }

    public void setNumCartaPK(String num_carta_pk) {
        this.numCartaPK = num_carta_pk;
    }

    public String getNomeProp() {
        return nomeProp;
    }

    public void setNomeProp(String nome_prp) {
        this.nomeProp = nome_prp;
    }

    public String getCognomePrp() {
        return cognomePrp;
    }

    public void setCognomePrp(String cognome_prp) {
        this.cognomePrp = cognome_prp;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodPayment that = (MethodPayment) o;

        if (!Objects.equals(numCartaPK, that.numCartaPK)) return false;
        if (!Objects.equals(nomeProp, that.nomeProp)) return false;
        if (!Objects.equals(cognomePrp, that.cognomePrp)) return false;
        if (!Objects.equals(scadenza, that.scadenza)) return false;
        if (!Objects.equals(cvc, that.cvc)) return false;
        return Objects.equals(tipologia, that.tipologia);
    }

    @Override
    public int hashCode() {
        int result = numCartaPK != null ? numCartaPK.hashCode() : 0;
        result = 31 * result + (nomeProp != null ? nomeProp.hashCode() : 0);
        result = 31 * result + (cognomePrp != null ? cognomePrp.hashCode() : 0);
        result = 31 * result + (scadenza != null ? scadenza.hashCode() : 0);
        result = 31 * result + (cvc != null ? cvc.hashCode() : 0);
        result = 31 * result + (tipologia != null ? tipologia.hashCode() : 0);
        return result;
    }
}
