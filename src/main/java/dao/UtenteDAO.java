package dao;

import exception.DaoException;
import model.Utente;

import java.sql.SQLException;

public interface UtenteDAO extends BaseDao<Utente, String>{

    Utente getUtenteByEmail(String email) throws DaoException;
}
