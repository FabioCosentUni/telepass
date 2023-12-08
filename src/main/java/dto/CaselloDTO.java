package dto;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="tb_casello")
public class CaselloDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "casello_generator")
    @SequenceGenerator(name="casello_generator", sequenceName = "seq_casello", allocationSize = 1)
    @Column(name="ID_CASELLO_PK", nullable = false)
    private Long idCaselloPk;
    @Column(name="km")
    private Integer km;
    @Column(name="ingressi")
    private Integer ingressi;
    @Column(name="autostrada")
    private String autostrada;

    // Costruttore vuoto
    public CaselloDTO() {
    }

    // Costruttore con parametri
    public CaselloDTO(Long idCaselloPk, Integer km, Integer ingressi, String autostrada) {
        this.idCaselloPk = idCaselloPk;
        this.km = km;
        this.ingressi = ingressi;
        this.autostrada = autostrada;
    }

    // Metodi getter e setter
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

    public Integer getIngressi() {
        return ingressi;
    }

    public void setIngressi(Integer ingressi) {
        this.ingressi = ingressi;
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

        CaselloDTO that = (CaselloDTO) o;

        if (!Objects.equals(idCaselloPk, that.idCaselloPk)) return false;
        if (!Objects.equals(km, that.km)) return false;
        if (!Objects.equals(ingressi, that.ingressi)) return false;
        return Objects.equals(autostrada, that.autostrada);
    }

    @Override
    public int hashCode() {
        int result = idCaselloPk != null ? idCaselloPk.hashCode() : 0;
        result = 31 * result + (km != null ? km.hashCode() : 0);
        result = 31 * result + (ingressi != null ? ingressi.hashCode() : 0);
        result = 31 * result + (autostrada != null ? autostrada.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CaselloDTO{");
        sb.append("idCaselloPk=").append(idCaselloPk);
        sb.append(", km=").append(km);
        sb.append(", ingressi=").append(ingressi);
        sb.append(", autostrada='").append(autostrada).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
