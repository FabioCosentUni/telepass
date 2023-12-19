package model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="tb_veicolo")
public class Veicolo implements Serializable {
    @Id
    @Column(name="TARGA_PK", nullable = false)
    private String targaPk;
    @Column(name="TIPOLOGIA_VE", nullable = false)
    private String tipologiaVe;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TRANSPONDER_FK", nullable = false)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Transponder transponder;

    // Costruttore vuoto
    public Veicolo() {
    }

    // Costruttore con parametri
    public Veicolo(String targaPk, String tipologiaVe, Transponder transponder) {
        this.targaPk = targaPk;
        this.tipologiaVe = tipologiaVe;
        this.transponder = transponder;
    }

    // Metodi getter e setter
    public String getTargaPk() {
        return targaPk;
    }

    public void setTargaPk(String targaPk) {
        this.targaPk = targaPk;
    }

    public String getTipologiaVe() {
        return tipologiaVe;
    }

    public void setTipologiaVe(String tipologiaVe) {
        this.tipologiaVe = tipologiaVe;
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
        if (!Objects.equals(tipologiaVe, that.tipologiaVe)) return false;
        return Objects.equals(transponder, that.transponder);
    }

    @Override
    public int hashCode() {
        int result = targaPk != null ? targaPk.hashCode() : 0;
        result = 31 * result + (tipologiaVe != null ? tipologiaVe.hashCode() : 0);
        result = 31 * result + (transponder != null ? transponder.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VeicoloDTO{");
        sb.append("targaPk='").append(targaPk).append('\'');
        sb.append(", tipologiaVe='").append(tipologiaVe).append('\'');
        sb.append(", transponderDTO=").append(transponder);
        sb.append('}');
        return sb.toString();
    }
}
