package dto;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="tb_transponder")
public class TransponderDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transponder_generator")
    @SequenceGenerator(name="transponder_generator", sequenceName = "seq_transponder", allocationSize = 1)
    @Column(name="CODICE_TRANSP_PK", nullable = false)
    private long codiceTranspPk;

    @ManyToOne
    @JoinColumn(name="CF_UTENTE_FK", nullable = false)
    private UtenteDTO utente;

    @Column(name="METODO_PAG")
    private String metodoPag;

    @Max(value=1, message="Il flag 'attivo' può assumere solo i valori 0 o 1")
    @Column(name="ATTIVO", nullable = false)
    private int attivo;

    @Max(value=1, message="Il flag 'plus' può assumere solo i valori 0 o 1")
    @Column(name="PLUS", nullable = false)
    private int plus;

    @OneToMany(mappedBy = "transponderDTO")
    private List<VeicoloDTO> veicoloList;

    public long getCodiceTranspPk() {
        return codiceTranspPk;
    }

    public void setCodiceTranspPk(long codiceTranspPk) {
        this.codiceTranspPk = codiceTranspPk;
    }

    public UtenteDTO getUtente() {
        return utente;
    }

    public void setUtente(UtenteDTO utente) {
        this.utente = utente;
    }

    public String getMetodoPag() {
        return metodoPag;
    }

    public void setMetodoPag(String metodoPag) {
        this.metodoPag = metodoPag;
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

    public List<VeicoloDTO> getVeicoloList() {
        return veicoloList;
    }

    public void setVeicoloList(List<VeicoloDTO> veicoloList) {
        this.veicoloList = veicoloList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransponderDTO that = (TransponderDTO) o;

        if (codiceTranspPk != that.codiceTranspPk) return false;
        if (attivo != that.attivo) return false;
        if (plus != that.plus) return false;
        if (!Objects.equals(utente, that.utente)) return false;
        if (!Objects.equals(metodoPag, that.metodoPag)) return false;
        return Objects.equals(veicoloList, that.veicoloList);
    }

    @Override
    public int hashCode() {
        int result = (int) (codiceTranspPk ^ (codiceTranspPk >>> 32));
        result = 31 * result + (utente != null ? utente.hashCode() : 0);
        result = 31 * result + (metodoPag != null ? metodoPag.hashCode() : 0);
        result = 31 * result + attivo;
        result = 31 * result + plus;
        result = 31 * result + (veicoloList != null ? veicoloList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TransponderDTO{");
        sb.append("codiceTranspPk=").append(codiceTranspPk);
        sb.append(", utente=").append(utente);
        sb.append(", metodoPag='").append(metodoPag).append('\'');
        sb.append(", attivo=").append(attivo);
        sb.append(", plus=").append(plus);
        sb.append(", veicoloList=").append(veicoloList);
        sb.append('}');
        return sb.toString();
    }
}
