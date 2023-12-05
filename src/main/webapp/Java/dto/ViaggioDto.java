import java.util.Date;

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

    // Metodo toString per la rappresentazione testuale dell'oggetto
    @Override
    public String toString() {
        return "ViaggioDTO{" +
                "veicoloDTO=" + veicoloDTO +
                ", caselloEntryDTO=" + caselloEntryDTO +
                ", timeEntry=" + timeEntry +
                ", caselloExitDTO=" + caselloExitDTO +
                ", timeExit=" + timeExit +
                ", pedaggio=" + pedaggio +
                ", pagatoFlag=" + pagatoFlag +
                '}';
    }
}
