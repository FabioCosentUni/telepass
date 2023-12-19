package dao;

import exception.DaoException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateConfiguration;

import java.io.Serializable;
import java.util.List;

public class BaseDao<T, ID extends Serializable> {

    private final Class<T> entityClass;

    public BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public boolean save(T entity) throws DaoException {
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
        return false;
    }

    public T findById(ID id) throws DaoException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.get(entityClass, id);
        } catch (Exception e) {
            throw new DaoException("Errore durante il recupero dell'entità ", e);
        }
    }

    public boolean update(T entity) throws DaoException {
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
        return false;
    }

    public boolean delete(T entity) throws DaoException {
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
            throw new DaoException("Errore durante l'eliminazione dell'entità ", e);
        }
        return false;
    }


    public List<T> findAll() throws DaoException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
        } catch (Exception e) {
            throw new DaoException("Errore durante il recupero di tutte le entità ", e);
        }
    }

}
