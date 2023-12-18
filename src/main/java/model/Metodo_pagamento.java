package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table (name="TB_METODO_PAG")
public class Metodo_pagamento implements Serializable {

    @Id
    @Column(name="NUM_CARTA_PK", nullable = false)
    private long num_carta_pk;

    @Column(name="NOME_PRP", nullable = false)
    private String nome_prp;

    @Column(name="COGNOME_PRP", nullable = false)
    private String cognome_prp;

    @Column(name="SCADENZA", nullable = false)
    private Date scadenza;

    @Column(name="CVC", nullable = false)
    private int cvc;

    @Column(name="TIPOLOGIA", nullable = false)
    private String tipologia;

    public Metodo_pagamento(long num_carta_pk, String nome_prp, String cognome_prp, Date scadenza, int cvc, String tipologia) {
        this.num_carta_pk = num_carta_pk;
        this.nome_prp = nome_prp;
        this.cognome_prp = cognome_prp;
        this.scadenza = scadenza;
        this.cvc = cvc;
        this.tipologia = tipologia;
    }

    public Metodo_pagamento() {

    }

    public long getNum_carta_pk() {
        return num_carta_pk;
    }

    public void setNum_carta_pk(long num_carta_pk) {
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

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }
}
