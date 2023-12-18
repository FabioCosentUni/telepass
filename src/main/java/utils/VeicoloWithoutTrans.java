package utils;

import model.Veicolo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_veicolo")
public class VeicoloWithoutTrans {
    @Id
    @Column(name = "TARGA_PK", nullable = false)
    private String targaPk;

    @Column(name = "MODELLO", nullable = false)
    private String modello;

    @Column(name = "BRAND", nullable = false)
    private String brand;

    @Column(name = "TIPOLOGIA_VE", nullable = false)
    private String tipologiaVe;

    @Column(name = "COLORE", nullable = false)
    private String colore;

    public VeicoloWithoutTrans() {
    }

    public void copyVeicolo(Veicolo v){
        this.targaPk = v.getTargaPk();
        this.modello = v.getModello();
        this.brand = v.getBrand();
        this.tipologiaVe = v.getTipologiaVe();
        this.colore = v.getColore();
    }

    public String getTargaPk() {
        return targaPk;
    }

    public void setTargaPk(String targaPk) {
        this.targaPk = targaPk;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTipologiaVe() {
        return tipologiaVe;
    }

    public void setTipologiaVe(String tipologiaVe) {
        this.tipologiaVe = tipologiaVe;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }
}
