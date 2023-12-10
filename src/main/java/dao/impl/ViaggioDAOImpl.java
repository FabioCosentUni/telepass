package dao.impl;

import dao.ViaggioDAO;
import model.Viaggio;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.sql.SQLException;
import java.util.List;

public class ViaggioDAOImpl implements ViaggioDAO {
    @Override
    public boolean insertViaggio(Viaggio viaggio) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(viaggio);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Errore durante l'inserimento del viaggio.", e);
        }
    }


    @Override
    public Viaggio getViaggioById(long viaggioId) {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.get(Viaggio.class, viaggioId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<Viaggio> getAllViaggi() throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Viaggio> query = session.createQuery("FROM Viaggio", Viaggio.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Errore durante il recupero di tutti i viaggi.", e);
        }
    }


    @Override
    public boolean updateViaggio(Viaggio viaggio) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(viaggio);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Errore durante l'aggiornamento del viaggio.", e);
        }
    }


    @Override
    public boolean deleteViaggioById(long viaggioId) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Viaggio viaggio = session.get(Viaggio.class, viaggioId);
            if (viaggio != null) {
                session.delete(viaggio);
            }

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Errore durante l'eliminazione del viaggio.", e);
        }
    }


}
