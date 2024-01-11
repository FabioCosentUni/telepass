/**
 * Interfaccia che estende {@link BaseHibernateDao} per la gestione dei dati relativi agli utenti
 * utilizzando Hibernate come framework ORM.
 * Estende l'interfaccia {@link BaseHibernateDao} fornendo operazioni specifiche per l'entità {@link Utente}.
 */
package dao;

import exception.DaoException;
import model.Utente;

public interface UtenteDAO extends BaseHibernateDAO<Utente, String> {

    /**
     * Ottiene un utente basato sull'indirizzo email specificato.
     *
     * @param email Indirizzo email dell'utente da cercare.
     * @return L'utente corrispondente all'indirizzo email specificato, se presente; altrimenti, null.
     * @throws DaoException se si verifica un errore durante l'accesso ai dati nel database.
     */
    Utente getUtenteByEmail(String email) throws DaoException;
}
