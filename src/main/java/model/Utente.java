package model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="tb_utente")
public class Utente {
    @Id
    @Column(name="CODICE_FISCALE_PK", nullable = false)
    private String codiceFiscalePk;
    @Column(name="NOME", nullable = false)
    private String nome;
    @Column(name="COGNOME", nullable = false)
    private String cognome;
    @Email(message = "L'email deve essere valida")
    @Column(name="EMAIL", nullable = false)
    private String email;
    @Column(name="INDIRIZZO_FATT", nullable = false)
    private String indirizzoFatt;
    @Column(name="CITTA_FATT", nullable = false)
    private String cittaFatt;
    @Column(name="REGIONE_FATT", nullable = false)
    private String regioneFatt;
    @Max(value=1, message="Il flag 'amministratore' può assumere solo i valori 0 o 1")
    @Column(name="AMMINISTRATORE", nullable = false)
    private int amministratore;

    @OneToMany(mappedBy = "utente")
    private List<Transponder> transponderList;

    // Costruttore vuoto
    public Utente() {
    }

    // Costruttore con parametri
    public Utente(String codiceFiscalePk, String nome, String cognome, String email,
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Utente utente = (Utente) o;

        if (amministratore != utente.amministratore) return false;
        if (!Objects.equals(codiceFiscalePk, utente.codiceFiscalePk))
            return false;
        if (!Objects.equals(nome, utente.nome)) return false;
        if (!Objects.equals(cognome, utente.cognome)) return false;
        if (!Objects.equals(email, utente.email)) return false;
        if (!Objects.equals(indirizzoFatt, utente.indirizzoFatt))
            return false;
        if (!Objects.equals(cittaFatt, utente.cittaFatt)) return false;
        return Objects.equals(regioneFatt, utente.regioneFatt);
    }

    @Override
    public int hashCode() {
        int result = codiceFiscalePk != null ? codiceFiscalePk.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (cognome != null ? cognome.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (indirizzoFatt != null ? indirizzoFatt.hashCode() : 0);
        result = 31 * result + (cittaFatt != null ? cittaFatt.hashCode() : 0);
        result = 31 * result + (regioneFatt != null ? regioneFatt.hashCode() : 0);
        result = 31 * result + amministratore;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UtenteDTO{");
        sb.append("codiceFiscalePk='").append(codiceFiscalePk).append('\'');
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", cognome='").append(cognome).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", indirizzoFatt='").append(indirizzoFatt).append('\'');
        sb.append(", cittaFatt='").append(cittaFatt).append('\'');
        sb.append(", regioneFatt='").append(regioneFatt).append('\'');
        sb.append(", amministratore=").append(amministratore);
        sb.append('}');
        return sb.toString();
    }
}
