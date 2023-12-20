package model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="tb_utente")
public class Utente implements Serializable {
    @Id
    @Column(name="CODICE_FISCALE_PK", nullable = false, length = 16)
    private String codiceFiscalePk;
    @Column(name="NOME", nullable = false)
    private String nome;

    @Column(name="EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name="COGNOME", nullable = false)
    private String cognome;

    @Column(name="SESSO", nullable = false)
    private Character sesso;

    @Column(name="PASSWORD", nullable = false)
    private String password;

    //default value = 0

    @Max(value=1, message="Il flag 'amministratore' può assumere solo i valori 0 o 1")
    @Column(name="AMMINISTRATORE", nullable = false)
    private int amministratore = 0;

    @OneToOne(mappedBy = "utente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Transponder transponder;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="NUM_CARTA_FK")
    private MethodPayment methodPayment;

    // Costruttore vuoto
    public Utente() {
    }

    public Utente(String codiceFiscalePk, String nome, String email, String cognome,Character sesso, String password) {
        this.codiceFiscalePk = codiceFiscalePk;
        this.nome = nome;
        this.email = email;
        this.cognome = cognome;
        this.sesso = sesso;
        this.password = password;
    }

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

    public char getSesso() {return sesso; }

    public void setSesso(char sesso) {this.sesso = sesso; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAmministratore() {
        return amministratore;
    }

    public void setAmministratore(int amministratore) {
        this.amministratore = amministratore;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Transponder getTransponder() {
        return transponder;
    }

    public void setTransponder(Transponder transponder) {
        this.transponder = transponder;
    }

    public MethodPayment getMethodPayment() {
        return methodPayment;
    }

    public void setMethodPayment(MethodPayment methodPayment) {
        this.methodPayment = methodPayment;
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
        if (!Objects.equals(email, utente.email)) return false;
        if (!Objects.equals(cognome, utente.cognome)) return false;
        if (!Objects.equals(sesso, utente.sesso)) return false;
        if (!Objects.equals(password, utente.password)) return false;
        return Objects.equals(transponder, utente.transponder);
    }

    @Override
    public int hashCode() {
        int result = codiceFiscalePk != null ? codiceFiscalePk.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (cognome != null ? cognome.hashCode() : 0);
        result = 31 * result + (sesso != null ? sesso.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + amministratore;
        result = 31 * result + (transponder != null ? transponder.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Utente{");
        sb.append("codiceFiscalePk='").append(codiceFiscalePk).append('\'');
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", cognome='").append(cognome).append('\'');
        sb.append(", sesso='").append(sesso).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", amministratore=").append(amministratore);
        sb.append(", transponder=").append(transponder);
        sb.append('}');
        return sb.toString();
    }
}
