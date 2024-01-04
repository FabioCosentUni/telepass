package model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="tb_casello")
public class Casello implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "casello_generator")
    @SequenceGenerator(name="casello_generator", sequenceName = "seq_casello", allocationSize = 1)
    @Column(name="ID_CASELLO_PK", nullable = false)
    private Long idCaselloPk;
    @Column(name="km")
    private Integer km;

    @Column(name="citta")
    private String citta;
    @Column(name="autostrada")
    private String autostrada;

    // Costruttore vuoto
    public Casello() {
    }

    // Costruttore con parametri

    public Casello(Long idCaselloPk, Integer km, String citta, String autostrada) {
        this.idCaselloPk = idCaselloPk;
        this.km = km;
        this.citta = citta;
        this.autostrada = autostrada;
    }

    public Long getIdCaselloPk() {
        return idCaselloPk;
    }

    public void setIdCaselloPk(Long idCaselloPk) {
        this.idCaselloPk = idCaselloPk;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getAutostrada() {
        return autostrada;
    }

    public void setAutostrada(String autostrada) {
        this.autostrada = autostrada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Casello casello = (Casello) o;

        if (!Objects.equals(idCaselloPk, casello.idCaselloPk)) return false;
        if (!Objects.equals(km, casello.km)) return false;
        if (!Objects.equals(citta, casello.citta)) return false;
        return Objects.equals(autostrada, casello.autostrada);
    }

    @Override
    public int hashCode() {
        int result = idCaselloPk != null ? idCaselloPk.hashCode() : 0;
        result = 31 * result + (km != null ? km.hashCode() : 0);
        result = 31 * result + (citta != null ? citta.hashCode() : 0);
        result = 31 * result + (autostrada != null ? autostrada.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Casello{");
        sb.append("idCaselloPk=").append(idCaselloPk);
        sb.append(", km=").append(km);
        sb.append(", citta='").append(citta).append('\'');
        sb.append(", autostrada='").append(autostrada).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
