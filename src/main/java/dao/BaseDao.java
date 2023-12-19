package dao;

import exception.DaoException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateConfiguration;

import java.io.Serializable;

public class BaseDao<T, ID extends Serializable> {

    private final Class<T> entityClass;

    public BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

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

    public T findById(ID id) throws DaoException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.get(entityClass, id);
        } catch (Exception e) {
            throw new DaoException("Errore durante il recupero dell'entità ", e);
        }
    }

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
}
