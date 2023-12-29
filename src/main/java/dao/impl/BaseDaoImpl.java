package dao.impl;

import dao.BaseDao;
import exception.DaoException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateConfiguration;

import java.io.Serializable;
import java.util.List;

/**
 * Implementazione di base dell'interfaccia BaseDao per operazioni CRUD
 * usando Hibernate per l'interazione con il database.
 *
 * @param <T>  Tipo dell'entità
 * @param <ID> Tipo dell'identificatore dell'entità
 */
public class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {

    private final Class<T> entityClass;

    /**
     * Costruttore per l'implementazione del DAO.
     *
     * @param entityClass Classe dell'entità gestita da questo DAO
     */
    public BaseDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

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

    @Override
    public T findById(ID id) throws DaoException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.get(entityClass, id);
        } catch (Exception e) {
            throw new DaoException("Errore durante il recupero dell'entità ", e);
        }
    }

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

    @Override
    public List<T> findAll() throws DaoException {
        try(Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.createQuery("FROM " + entityClass.getName(), entityClass).list();
        } catch (Exception e) {
            throw new DaoException("Errore durante il recupero delle entità ", e);
        }
    }
}
