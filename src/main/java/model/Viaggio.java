package model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="tb_viaggio")
public class Viaggio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "viaggio_generator")
    @SequenceGenerator(name="viaggio_generator", sequenceName = "seq_viaggio", allocationSize = 1)
    @Column(name="ID_VIAGGIO_PK", nullable = false)
    private long idViaggioPk;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="TARGA_VE_FK", referencedColumnName = "TARGA_PK")
    private Veicolo veicolo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="CASELLO_ENTRY_FK", referencedColumnName = "ID_CASELLO_PK")
    private Casello caselloEntryDTO;
    @Column(name="TIME_ENTRY", nullable = false)
    private Date timeEntry;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="CASELLO_EXIT_FK", referencedColumnName = "ID_CASELLO_PK")
    private Casello caselloExitDTO;
    @Column(name="TIME_EXIT", nullable = false)
    private Date timeExit;
    @Column(name="pedaggio", nullable = false)
    private float pedaggio;
    @Max(value=1, message="Il flag 'pagato' può assumere solo i valori 0 o 1")
    @Column(name="PAGATO_FLAG", nullable = false)
    private int pagatoFlag;

    public long getIdViaggioPk() {
        return idViaggioPk;
    }

    public void setIdViaggioPk(long idViaggioPk) {
        this.idViaggioPk = idViaggioPk;
    }

    public Veicolo getVeicoloDTO() {
        return veicolo;
    }

    public void setVeicoloDTO(Veicolo veicolo) {
        this.veicolo = veicolo;
    }

    public Casello getCaselloEntryDTO() {
        return caselloEntryDTO;
    }

    public void setCaselloEntryDTO(Casello caselloEntryDTO) {
        this.caselloEntryDTO = caselloEntryDTO;
    }

    public Date getTimeEntry() {
        return timeEntry;
    }

    public void setTimeEntry(Date timeEntry) {
        this.timeEntry = timeEntry;
    }

    public Casello getCaselloExitDTO() {
        return caselloExitDTO;
    }

    public void setCaselloExitDTO(Casello caselloExitDTO) {
        this.caselloExitDTO = caselloExitDTO;
    }

    public Date getTimeExit() {
        return timeExit;
    }

    public void setTimeExit(Date timeExit) {
        this.timeExit = timeExit;
    }

    public float getPedaggio() {
        return pedaggio;
    }

    public void setPedaggio(float pedaggio) {
        this.pedaggio = pedaggio;
    }

    public int getPagatoFlag() {
        return pagatoFlag;
    }

    public void setPagatoFlag(int pagatoFlag) {
        this.pagatoFlag = pagatoFlag;
    }

    public String getFormatDateEntry(){
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formatoData.format(this.timeEntry);
    }

    public String getFormatDateExit(){
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formatoData.format(this.timeExit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Viaggio that = (Viaggio) o;

        if (idViaggioPk != that.idViaggioPk) return false;
        if (Float.compare(that.pedaggio, pedaggio) != 0) return false;
        if (pagatoFlag != that.pagatoFlag) return false;
        if (!Objects.equals(veicolo, that.veicolo)) return false;
        if (!Objects.equals(caselloEntryDTO, that.caselloEntryDTO))
            return false;
        if (!Objects.equals(timeEntry, that.timeEntry)) return false;
        if (!Objects.equals(caselloExitDTO, that.caselloExitDTO))
            return false;
        return Objects.equals(timeExit, that.timeExit);
    }

    @Override
    public int hashCode() {
        int result = (int) (idViaggioPk ^ (idViaggioPk >>> 32));
        result = 31 * result + (veicolo != null ? veicolo.hashCode() : 0);
        result = 31 * result + (caselloEntryDTO != null ? caselloEntryDTO.hashCode() : 0);
        result = 31 * result + (timeEntry != null ? timeEntry.hashCode() : 0);
        result = 31 * result + (caselloExitDTO != null ? caselloExitDTO.hashCode() : 0);
        result = 31 * result + (timeExit != null ? timeExit.hashCode() : 0);
        result = 31 * result + (pedaggio != +0.0f ? Float.floatToIntBits(pedaggio) : 0);
        result = 31 * result + pagatoFlag;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ViaggioDTO{");
        sb.append("idViaggioPk=").append(idViaggioPk);
        sb.append(", veicoloDTO=").append(veicolo);
        sb.append(", caselloEntryDTO=").append(caselloEntryDTO);
        sb.append(", timeEntry=").append(timeEntry);
        sb.append(", caselloExitDTO=").append(caselloExitDTO);
        sb.append(", timeExit=").append(timeExit);
        sb.append(", pedaggio=").append(pedaggio);
        sb.append(", pagatoFlag=").append(pagatoFlag);
        sb.append('}');
        return sb.toString();
    }
}
