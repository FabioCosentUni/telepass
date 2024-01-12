package dao.impl;

import dao.BaseHibernateDAO;
import exception.DaoException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateConfiguration;

import java.io.Serializable;
import java.util.List;

/**
 * Implementazione di base dell'interfaccia BaseHibernateDao per operazioni CRUD
 * usando Hibernate per l'interazione con il database.
 *
 * @param <T>  Tipo dell'entità
 * @param <ID> Tipo dell'identificatore dell'entità
 */
public class BaseHibernateDAOImpl<T, ID extends Serializable> implements BaseHibernateDAO<T, ID> {

    private final Class<T> entityClass;

    /**
     * Costruttore per l'implementazione del DAO.
     *
     * @param entityClass Classe dell'entità gestita da questo DAO
     */
    public BaseHibernateDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Salva un'entità nel database.
     *
     * @param entity L'entità da salvare.
     * @throws DaoException se si verifica un errore durante l'inserimento dell'entità.
     */
    @Override
    public void save(T entity) throws DaoException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Errore durante l'inserimento dell'entità ", e);
        }
    }

    /**
     * Trova un'entità nel database dato il suo identificatore.
     *
     * @param id L'identificatore dell'entità da cercare.
     * @return L'entità trovata o null se non esiste.
     * @throws DaoException se si verifica un errore durante il recupero dell'entità.
     */
    @Override
    public T findById(ID id) throws DaoException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.get(entityClass, id);
        } catch (Exception e) {
            throw new DaoException("Errore durante il recupero dell'entità ", e);
        }
    }

    /**
     * Aggiorna un'entità nel database.
     *
     * @param entity L'entità da aggiornare.
     * @throws DaoException se si verifica un errore durante l'aggiornamento dell'entità.
     */
    @Override
    public void update(T entity) throws DaoException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Errore durante l'aggiornamento dell'entità ", e);
        }
    }

    /**
     * Esegue un merge dell'entità nel database.
     *
     * @param entity L'entità da eseguire il merge.
     * @throws DaoException se si verifica un errore durante l'aggiornamento dell'entità.
     */
    @Override
    public void merge(T entity) throws DaoException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Errore durante l'aggiornamento dell'entità ", e);
        }
    }

    /**
     * Cancella un'entità dal database.
     *
     * @param entity L'entità da cancellare.
     */
    @Override
    public void delete(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Trova tutte le entità della classe nel database.
     *
     * @return Una lista di tutte le entità della classe.
     * @throws DaoException se si verifica un errore durante il recupero delle entità.
     */
    @Override
    public List<T> findAll() throws DaoException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.createQuery("FROM " + entityClass.getName(), entityClass).list();
        } catch (Exception e) {
            throw new DaoException("Errore durante il recupero delle entità ", e);
        }
    }
}
