package dto;

import java.util.Date;
import java.util.Objects;

public class ViaggioDTO {
    private VeicoloDTO veicoloDTO;
    private CaselloDTO caselloEntryDTO;
    private Date timeEntry;
    private CaselloDTO caselloExitDTO;
    private Date timeExit;
    private float pedaggio;
    private int pagatoFlag;

    // Costruttore vuoto
    public ViaggioDTO() {
    }

    // Costruttore con parametri
    public ViaggioDTO(VeicoloDTO veicoloDTO, CaselloDTO caselloEntryDTO, Date timeEntry,
                      CaselloDTO caselloExitDTO, Date timeExit, float pedaggio, int pagatoFlag) {
        this.veicoloDTO = veicoloDTO;
        this.caselloEntryDTO = caselloEntryDTO;
        this.timeEntry = timeEntry;
        this.caselloExitDTO = caselloExitDTO;
        this.timeExit = timeExit;
        this.pedaggio = pedaggio;
        this.pagatoFlag = pagatoFlag;
    }

    // Metodi getter e setter
    public VeicoloDTO getVeicoloDTO() {
        return veicoloDTO;
    }

    public void setVeicoloDTO(VeicoloDTO veicoloDTO) {
        this.veicoloDTO = veicoloDTO;
    }

    public CaselloDTO getCaselloEntryDTO() {
        return caselloEntryDTO;
    }

    public void setCaselloEntryDTO(CaselloDTO caselloEntryDTO) {
        this.caselloEntryDTO = caselloEntryDTO;
    }

    public Date getTimeEntry() {
        return timeEntry;
    }

    public void setTimeEntry(Date timeEntry) {
        this.timeEntry = timeEntry;
    }

    public CaselloDTO getCaselloExitDTO() {
        return caselloExitDTO;
    }

    public void setCaselloExitDTO(CaselloDTO caselloExitDTO) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViaggioDTO that = (ViaggioDTO) o;

        if (Float.compare(that.pedaggio, pedaggio) != 0) return false;
        if (pagatoFlag != that.pagatoFlag) return false;
        if (!Objects.equals(veicoloDTO, that.veicoloDTO)) return false;
        if (!Objects.equals(caselloEntryDTO, that.caselloEntryDTO))
            return false;
        if (!Objects.equals(timeEntry, that.timeEntry)) return false;
        if (!Objects.equals(caselloExitDTO, that.caselloExitDTO))
            return false;
        return Objects.equals(timeExit, that.timeExit);
    }

    @Override
    public int hashCode() {
        int result = veicoloDTO != null ? veicoloDTO.hashCode() : 0;
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
        sb.append("veicoloDTO=").append(veicoloDTO);
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
