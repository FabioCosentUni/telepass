/**
 * Interfaccia che estende {@link BaseHibernateDao} per la gestione dei dati relativi ai veicoli
 * utilizzando Hibernate come framework ORM.
 * Estende l'interfaccia {@link BaseHibernateDao} fornendo operazioni specifiche per l'entità {@link Veicolo}.
 */
package dao;

import exception.DaoException;
import exception.TelepassException;
import model.Utente;
import model.Veicolo;

import java.util.List;

public interface VeicoloDAO extends BaseHibernateDAO<Veicolo, String> {

    /**
     * Ottiene una lista di veicoli associati ad un utente specifico.
     * @param u
     * @return
     * @throws TelepassException
     * @throws DaoException
     */
    List<Veicolo> getVeicoliUtente(Utente u) throws TelepassException, DaoException;
}
