package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="tb_veicolo")
public class Veicolo implements Serializable {
    @Id
    @Column(name="TARGA_PK", nullable = false)
    private String targaPk;
    @Column(name="MODELLO", nullable = false)
    private String modello;
    @Column(name="BRAND", nullable = false)
    private String brand;
    @Column(name="TIPOLOGIA_VE", nullable = false)
    private String tipologiaVe;
    @Column(name="COLORE", nullable = false)
    private String colore;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TRANSPONDER_FK", nullable = false)
    private Transponder transponder;

    // Costruttore vuoto
    public Veicolo() {
    }

    // Costruttore con parametri
    public Veicolo(String targaPk, String modello, String brand, String tipologiaVe, String colore, Transponder transponder) {
        this.targaPk = targaPk;
        this.modello = modello;
        this.brand = brand;
        this.tipologiaVe = tipologiaVe;
        this.colore = colore;
        this.transponder = transponder;
    }

    // Metodi getter e setter
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

    public Transponder getTransponderDTO() {
        return transponder;
    }

    public void setTransponderDTO(Transponder transponder) {
        this.transponder = transponder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Veicolo that = (Veicolo) o;

        if (!Objects.equals(targaPk, that.targaPk)) return false;
        if (!Objects.equals(modello, that.modello)) return false;
        if (!Objects.equals(brand, that.brand)) return false;
        if (!Objects.equals(tipologiaVe, that.tipologiaVe)) return false;
        if (!Objects.equals(colore, that.colore)) return false;
        return Objects.equals(transponder, that.transponder);
    }

    @Override
    public int hashCode() {
        int result = targaPk != null ? targaPk.hashCode() : 0;
        result = 31 * result + (modello != null ? modello.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (tipologiaVe != null ? tipologiaVe.hashCode() : 0);
        result = 31 * result + (colore != null ? colore.hashCode() : 0);
        result = 31 * result + (transponder != null ? transponder.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VeicoloDTO{");
        sb.append("targaPk='").append(targaPk).append('\'');
        sb.append(", modello='").append(modello).append('\'');
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", tipologiaVe='").append(tipologiaVe).append('\'');
        sb.append(", colore='").append(colore).append('\'');
        sb.append(", transponderDTO=").append(transponder);
        sb.append('}');
        return sb.toString();
    }
}
