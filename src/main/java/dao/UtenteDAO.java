package dao;

import model.Utente;

import java.sql.SQLException;
import java.util.List;

public interface UtenteDAO {

    Utente getUtenteByEmail(String email) throws SQLException;
    void insert(Utente utente) throws SQLException;

    Utente getUtenteByCodiceFiscale(String codiceFiscale) throws SQLException;

    List<Utente> getAllUtenti() throws SQLException;

    Utente updateUtente(Utente utente) throws SQLException;

    boolean deleteUtenteByCodiceFiscale(String codiceFiscale) throws SQLException;
}
