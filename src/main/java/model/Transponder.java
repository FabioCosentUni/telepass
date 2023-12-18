package model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="tb_transponder")
public class Transponder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transponder_generator")
    @SequenceGenerator(name="transponder_generator", sequenceName = "seq_transponder", allocationSize = 1)
    @Column(name="CODICE_TRANSP_PK", nullable = false)
    private long codiceTranspPk;

    @Column(name="CODICE_TRANSPONDER", nullable = false)
    private String codiceTransponder;

    @OneToOne
    @JoinColumn(name="CF_UTENTE_FK")
    private Utente utente;

    @Max(value=1, message="Il flag 'attivo' può assumere solo i valori 0 o 1")
    @Column(name="ATTIVO", nullable = false)
    private int attivo = 1;

    @Max(value=1, message="Il flag 'plus' può assumere solo i valori 0 o 1")
    @Column(name="PLUS", nullable = false)
    private int plus = 0;

    @OneToMany(mappedBy = "transponder", fetch = FetchType.EAGER)
    private List<Veicolo> veicoloList = new ArrayList<>();


    public Transponder(String codiceTransponder, Utente utente, int attivo) {
        this.codiceTransponder = codiceTransponder;
        this.utente = utente;
        this.attivo = attivo;
    }

    public Transponder(String codiceTransponder, Utente utente, int attivo, int plus, List<Veicolo> veicoloList) {
        this.codiceTransponder = codiceTransponder;
        this.utente = utente;
        this.attivo = attivo;
        this.plus = plus;
        this.veicoloList = veicoloList;
    }

    public Transponder() {}


    public long getCodiceTranspPk() {
        return codiceTranspPk;
    }

    public void setCodiceTranspPk(long codiceTranspPk) {
        this.codiceTranspPk = codiceTranspPk;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public int getAttivo() {
        return attivo;
    }

    public void setAttivo(int attivo) {
        this.attivo = attivo;
    }

    public int getPlus() {
        return plus;
    }

    public void setPlus(int plus) {
        this.plus = plus;
    }

    public List<Veicolo> getVeicoloList() {
        return veicoloList;
    }

    public void setVeicoloList(List<Veicolo> veicoloList) {
        this.veicoloList = veicoloList;
    }

    public String getCodiceTransponder() {
        return codiceTransponder;
    }

    public void setCodiceTransponder(String codiceTransponder) {
        this.codiceTransponder = codiceTransponder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transponder that = (Transponder) o;

        if (codiceTranspPk != that.codiceTranspPk) return false;
        if (attivo != that.attivo) return false;
        if (plus != that.plus) return false;
        if (!Objects.equals(codiceTransponder, that.codiceTransponder))
            return false;
        if (!Objects.equals(utente, that.utente)) return false;
        return Objects.equals(veicoloList, that.veicoloList);
    }

    @Override
    public int hashCode() {
        int result = (int) (codiceTranspPk ^ (codiceTranspPk >>> 32));
        result = 31 * result + (codiceTransponder != null ? codiceTransponder.hashCode() : 0);
        result = 31 * result + (utente != null ? utente.hashCode() : 0);
        result = 31 * result + attivo;
        result = 31 * result + plus;
        result = 31 * result + (veicoloList != null ? veicoloList.hashCode() : 0);
        return result;
    }
}
