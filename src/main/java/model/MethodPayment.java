package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    private Date scadenza;

    @Column(name="CVC", nullable = false)
    private String cvc;

    @Column(name="TIPOLOGIA", nullable = false)
    private String tipologia;

    @OneToOne(mappedBy = "methodPayment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Utente utente;


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

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
