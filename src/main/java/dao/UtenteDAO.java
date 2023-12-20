package dao;

import model.Utente;

import java.sql.SQLException;

public interface UtenteDAO {

    Utente getUtenteByEmail(String email) throws SQLException;
}
