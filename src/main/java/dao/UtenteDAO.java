package dao;

import model.Utente;

import java.sql.SQLException;
import java.util.List;

public interface UtenteDAO {

    Utente getUtenteByCF(String cf) throws SQLException;
    void insert(Utente utente) throws SQLException;

    Utente getUtenteByCodiceFiscale(String codiceFiscale) throws SQLException;

    List<Utente> getAllUtenti() throws SQLException;

    boolean updateUtente(Utente utente) throws SQLException;

    boolean deleteUtenteByCodiceFiscale(String codiceFiscale) throws SQLException;
}
