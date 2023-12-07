package dto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="tb_veicolo")
public class VeicoloDTO {
    @Id
    @Column(name="targaPk", nullable = false)
    private String targaPk;
    @Column(name="modello", nullable = false)
    private String modello;
    @Column(name="brand", nullable = false)
    private String brand;
    @Column(name="tipologiaVe", nullable = false)
    private String tipologiaVe;
    @Column(name="colore", nullable = false)
    private String colore;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="codiceTranspFk", nullable = false)
    private TransponderDTO transponderDTO;  // Aggiunto campo per rappresentare la relazione con il transponder

    // Costruttore vuoto
    public VeicoloDTO() {
    }

    // Costruttore con parametri
    public VeicoloDTO(String targaPk, String modello, String brand, String tipologiaVe, String colore, TransponderDTO transponderDTO) {
        this.targaPk = targaPk;
        this.modello = modello;
        this.brand = brand;
        this.tipologiaVe = tipologiaVe;
        this.colore = colore;
        this.transponderDTO = transponderDTO;
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

    public TransponderDTO getTransponderDTO() {
        return transponderDTO;
    }

    public void setTransponderDTO(TransponderDTO transponderDTO) {
        this.transponderDTO = transponderDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VeicoloDTO that = (VeicoloDTO) o;

        if (!Objects.equals(targaPk, that.targaPk)) return false;
        if (!Objects.equals(modello, that.modello)) return false;
        if (!Objects.equals(brand, that.brand)) return false;
        if (!Objects.equals(tipologiaVe, that.tipologiaVe)) return false;
        if (!Objects.equals(colore, that.colore)) return false;
        return Objects.equals(transponderDTO, that.transponderDTO);
    }

    @Override
    public int hashCode() {
        int result = targaPk != null ? targaPk.hashCode() : 0;
        result = 31 * result + (modello != null ? modello.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (tipologiaVe != null ? tipologiaVe.hashCode() : 0);
        result = 31 * result + (colore != null ? colore.hashCode() : 0);
        result = 31 * result + (transponderDTO != null ? transponderDTO.hashCode() : 0);
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
        sb.append(", transponderDTO=").append(transponderDTO);
        sb.append('}');
        return sb.toString();
    }
}
