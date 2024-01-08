/**
 * Interfaccia che estende {@link BaseHibernateDao} per la gestione dei dati relativi ai viaggi
 * utilizzando Hibernate come framework ORM.
 * Estende l'interfaccia {@link BaseHibernateDao} fornendo operazioni specifiche per l'entità {@link Viaggio}.
 */
package dao;

import exception.DaoException;
import model.Casello;
import model.Veicolo;
import model.Viaggio;

import java.util.List;
import java.util.Map;

public interface ViaggioHibernateDAO extends BaseHibernateDAO<Viaggio, Long> {

    /**
     * Ottiene una lista di identificatori dei viaggi associati a un veicolo specifico.
     *
     * @param targa La targa del veicolo di cui si vogliono ottenere i viaggi.
     * @return Una lista di identificatori dei viaggi associati al veicolo.
     * @throws DaoException se si verifica un errore durante l'accesso ai dati nel database.
     */
    List<Float> getPedaggiPagatiByVeicolo(String targa) throws DaoException;

    /**
     * Verifica se ci sono viaggi associati a un veicolo specifico.
     *
     * @param targa La targa del veicolo di cui si vuole verificare la presenza di viaggi.
     * @return true se ci sono viaggi associati al veicolo, false altrimenti.
     * @throws DaoException se si verifica un errore durante l'accesso ai dati nel database.
     */
    boolean checkVeicoloViaggi(String targa) throws DaoException;

    /**
     * Ottiene una mappa che associa ogni casello alla sua percentuale di entrate generate.
     *
     * @return Una mappa che associa i caselli alle percentuali di entrate generate.
     * @throws DaoException se si verifica un errore durante l'accesso ai dati nel database.
     */
    Map<Casello, Double> getPercentualiEntrateCaselli() throws DaoException;

    /**
     * Ottiene una mappa che associa ogni casello alla sua percentuale di uscite registrate.
     *
     * @return Una mappa che associa i caselli alle percentuali di uscite registrate.
     * @throws DaoException se si verifica un errore durante l'accesso ai dati nel database.
     */
    Map<Casello, Double> getPercentualiUsciteCaselli() throws DaoException;

    /**
     * Ottiene una lista di viaggi associati ad un veicolo specifico.
     * @param v
     * @return {@link List<Viaggio>}
     * @throws DaoException
     */
    List<Viaggio> getViaggiPerVeicolo(Veicolo v) throws DaoException;
}
