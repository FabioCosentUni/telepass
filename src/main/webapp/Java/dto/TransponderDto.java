public class TransponderDTO {
    private String codiceTranspPk;
    private String cfUtenteFk;
    private String metodoPag;
    private int attivo;
    private int plus;

    // Costruttore vuoto
    public TransponderDTO() {
    }

    // Costruttore con parametri
    public TransponderDTO(String codiceTranspPk, String cfUtenteFk, String metodoPag, int attivo, int plus) {
        this.codiceTranspPk = codiceTranspPk;
        this.cfUtenteFk = cfUtenteFk;
        this.metodoPag = metodoPag;
        this.attivo = attivo;
        this.plus = plus;
    }

    // Metodi getter e setter
    public String getCodiceTranspPk() {
        return codiceTranspPk;
    }

    public void setCodiceTranspPk(String codiceTranspPk) {
        this.codiceTranspPk = codiceTranspPk;
    }

    public String getCfUtenteFk() {
        return cfUtenteFk;
    }

    public void setCfUtenteFk(String cfUtenteFk) {
        this.cfUtenteFk = cfUtenteFk;
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

    // Metodo toString per la rappresentazione testuale dell'oggetto
    @Override
    public String toString() {
        return "TransponderDTO{" +
                "codiceTranspPk='" + codiceTranspPk + '\'' +
                ", cfUtenteFk='" + cfUtenteFk + '\'' +
                ", metodoPag='" + metodoPag + '\'' +
                ", attivo=" + attivo +
                ", plus=" + plus +
                '}';
    }
}
