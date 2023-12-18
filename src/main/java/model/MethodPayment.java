package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table (name="TB_METODO_PAG")
public class MethodPayment implements Serializable {

    @Id
    @Column(name="NUM_CARTA_PK", nullable = false)
    private String num_carta_pk;

    @Column(name="NOME_PRP", nullable = false)
    private String nome_prp;

    @Column(name="COGNOME_PRP", nullable = false)
    private String cognome_prp;

    @Column(name="SCADENZA", nullable = false)
    private Date scadenza;

    @Column(name="CVC", nullable = false)
    private String cvc;

    @Column(name="TIPOLOGIA", nullable = false)
    private String tipologia;

    public MethodPayment(String num_carta_pk, String nome_prp, String cognome_prp, Date scadenza, String cvc, String tipologia) {
        this.num_carta_pk = num_carta_pk;
        this.nome_prp = nome_prp;
        this.cognome_prp = cognome_prp;
        this.scadenza = scadenza;
        this.cvc = cvc;
        this.tipologia = tipologia;
    }

    public MethodPayment() {

    }

    public String getNum_carta_pk() {
        return num_carta_pk;
    }

    public void setNum_carta_pk(String num_carta_pk) {
        this.num_carta_pk = num_carta_pk;
    }

    public String getNome_prp() {
        return nome_prp;
    }

    public void setNome_prp(String nome_prp) {
        this.nome_prp = nome_prp;
    }

    public String getCognome_prp() {
        return cognome_prp;
    }

    public void setCognome_prp(String cognome_prp) {
        this.cognome_prp = cognome_prp;
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
}
