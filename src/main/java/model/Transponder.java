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
    @Column(name="CODICE_TRANSPONDER", nullable = false)
    private String codiceTransponder;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CF_UTENTE_FK")
    private Utente utente;

    @Max(value=1, message="Il flag 'plus' può assumere solo i valori 0 o 1")
    @Column(name="PLUS", nullable = false)
    private int plus = 0;

    @OneToMany(mappedBy = "transponder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Veicolo> veicoloList = new ArrayList<>();

    public Transponder(String codiceTransponder, Utente utente, int plus) {
        this.codiceTransponder = codiceTransponder;
        this.utente = utente;
        this.plus = plus;
    }

    public Transponder(String codiceTransponder, Utente utente, int plus, List<Veicolo> veicoloList) {
        this.codiceTransponder = codiceTransponder;
        this.utente = utente;
        this.plus = plus;
        this.veicoloList = veicoloList;
    }
    public Transponder() {}

    public String getCodiceTransponder() {
        return codiceTransponder;
    }

    public void setCodiceTransponder(String codiceTransponder) {
        this.codiceTransponder = codiceTransponder;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transponder that = (Transponder) o;

        if (plus != that.plus) return false;
        if (!Objects.equals(codiceTransponder, that.codiceTransponder))
            return false;
        if (!Objects.equals(utente, that.utente)) return false;
        return Objects.equals(veicoloList, that.veicoloList);
    }

    @Override
    public int hashCode() {
        int result = codiceTransponder != null ? codiceTransponder.hashCode() : 0;
        result = 31 * result + (utente != null ? utente.hashCode() : 0);
        result = 31 * result + plus;
        result = 31 * result + (veicoloList != null ? veicoloList.hashCode() : 0);
        return result;
    }
}
