package dao;

import exception.DaoException;
import model.Utente;

public interface UtenteHibernateDAO extends BaseHibernateDao<Utente, String> {

    Utente getUtenteByEmail(String email) throws DaoException;
}
