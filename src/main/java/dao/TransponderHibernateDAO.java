/**
 * Interfaccia che estende {@link BaseHibernateDao} per la gestione dei dati relativi ai transponder
 * utilizzando Hibernate come framework ORM.
 * Estende l'interfaccia {@link BaseHibernateDao} fornendo operazioni specifiche per l'entità {@link Transponder}.
 */
package dao;

import exception.DaoException;
import model.Transponder;

import java.util.List;

public interface TransponderHibernateDAO extends BaseHibernateDao<Transponder, String> {

    /**
     * Ottiene una lista di transponder attivi presenti nel sistema.
     *
     * @return Una lista di transponder attivi.
     * @throws DaoException se si verifica un errore durante l'accesso ai dati nel database.
     */
    List<Transponder> getActiveTransponders() throws DaoException;
}
