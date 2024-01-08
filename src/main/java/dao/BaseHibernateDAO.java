package dao;

import exception.DaoException;

import java.io.Serializable;
import java.util.List;

/**
 * Interfaccia di base per le operazioni CRUD (Create, Read, Update, Delete)
 * su entità generiche.
 *
 * @param <T>  Tipo dell'entità
 * @param <ID> Tipo dell'identificatore dell'entità
 */
public interface BaseHibernateDAO<T, ID extends Serializable> {

    /**
     * Salva un'entità nel database.
     *
     * @param entity Entità da salvare
     * @throws DaoException Eccezione relativa al DAO
     */
    void save(T entity) throws DaoException;

    /**
     * Trova un'entità per ID nel database.
     *
     * @param id ID dell'entità da trovare
     * @return L'entità trovata o null se non esiste
     * @throws DaoException Eccezione relativa al DAO
     */
    T findById(ID id) throws DaoException;

    /**
     * Aggiorna un'entità esistente nel database.
     *
     * @param entity Entità da aggiornare
     * @throws DaoException Eccezione relativa al DAO
     */
    void update(T entity) throws DaoException;

    /**
     * Unisce (merge) un'entità con lo stato persistente esistente nel database di un'entità nello stato transient .
     *
     * @param entity Entità da unire
     * @throws DaoException Eccezione relativa al DAO
     */
    void merge(T entity) throws DaoException;

    /**
     * Cancella un'entità dal database.
     *
     * @param entity Entità da cancellare
     * @throws DaoException Eccezione relativa al DAO
     */
    void delete(T entity) throws DaoException;

    /**
     * Restituisce una lista di tutte le entità presenti nel database.
     *
     * @return Lista di tutte le entità
     * @throws DaoException Eccezione relativa al DAO
     */
    List<T> findAll() throws DaoException;
}
