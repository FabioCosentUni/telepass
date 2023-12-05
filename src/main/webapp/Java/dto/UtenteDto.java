public class UtenteDTO {
    private String codiceFiscalePk;
    private String nome;
    private String cognome;
    private String email;
    private String indirizzoFatt;
    private String cittaFatt;
    private String regioneFatt;
    private int amministratore;

    // Costruttore vuoto
    public UtenteDTO() {
    }

    // Costruttore con parametri
    public UtenteDTO(String codiceFiscalePk, String nome, String cognome, String email,
                     String indirizzoFatt, String cittaFatt, String regioneFatt, int amministratore) {
        this.codiceFiscalePk = codiceFiscalePk;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.indirizzoFatt = indirizzoFatt;
        this.cittaFatt = cittaFatt;
        this.regioneFatt = regioneFatt;
        this.amministratore = amministratore;
    }

    // Metodi getter e setter
    public String getCodiceFiscalePk() {
        return codiceFiscalePk;
    }

    public void setCodiceFiscalePk(String codiceFiscalePk) {
        this.codiceFiscalePk = codiceFiscalePk;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndirizzoFatt() {
        return indirizzoFatt;
    }

    public void setIndirizzoFatt(String indirizzoFatt) {
        this.indirizzoFatt = indirizzoFatt;
    }

    public String getCittaFatt() {
        return cittaFatt;
    }

    public void setCittaFatt(String cittaFatt) {
        this.cittaFatt = cittaFatt;
    }

    public String getRegioneFatt() {
        return regioneFatt;
    }

    public void setRegioneFatt(String regioneFatt) {
        this.regioneFatt = regioneFatt;
    }

    public int getAmministratore() {
        return amministratore;
    }

    public void setAmministratore(int amministratore) {
        this.amministratore = amministratore;
    }

    // Metodo toString per la rappresentazione testuale dell'oggetto
    @Override
    public String toString() {
        return "UtenteDTO{" +
                "codiceFiscalePk='" + codiceFiscalePk + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", indirizzoFatt='" + indirizzoFatt + '\'' +
                ", cittaFatt='" + cittaFatt + '\'' +
                ", regioneFatt='" + regioneFatt + '\'' +
                ", amministratore=" + amministratore +
                '}';
    }
}
