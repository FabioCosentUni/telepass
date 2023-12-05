public class VeicoloDTO {
    private String targaPk;
    private String modello;
    private String brand;
    private String tipologiaVe;
    private String colore;
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

    // Metodo toString per la rappresentazione testuale dell'oggetto
    @Override
    public String toString() {
        return "VeicoloDTO{" +
                "targaPk='" + targaPk + '\'' +
                ", modello='" + modello + '\'' +
                ", brand='" + brand + '\'' +
                ", tipologiaVe='" + tipologiaVe + '\'' +
                ", colore='" + colore + '\'' +
                ", transponderDTO=" + transponderDTO +
                '}';
    }
}
