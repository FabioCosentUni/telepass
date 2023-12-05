public class CaselloDTO {
    private Long idCaselloPk;
    private Integer km;
    private Integer ingressi;
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

    // Metodo toString per la rappresentazione testuale dell'oggetto
    @Override
    public String toString() {
        return "CaselloDTO{" +
                "idCaselloPk=" + idCaselloPk +
                ", km=" + km +
                ", ingressi=" + ingressi +
                ", autostrada='" + autostrada + '\'' +
                '}';
    }
}
