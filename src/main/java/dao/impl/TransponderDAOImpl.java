package dao.impl;

import dao.TransponderDAO;
import dto.TransponderDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.sql.SQLException;
import java.util.List;

public class TransponderDAOImpl implements TransponderDAO {


    @Override
    public boolean insertTransponder(TransponderDTO transponder) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(transponder);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Errore durante l'inserimento del transponder.", e);
        }
    }

    @Override
    public TransponderDTO getTransponderByCodice(long transponderId) throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.get(TransponderDTO.class, transponderId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<TransponderDTO> getAllTransponders() throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<TransponderDTO> query = session.createQuery("FROM TransponderDTO", TransponderDTO.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Errore durante il recupero di tutti i transponder.", e);
        }
    }


    @Override
    public boolean updateTransponder(TransponderDTO transponder) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(transponder);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Errore durante l'aggiornamento del transponder.", e);
        }
    }


    @Override
    public boolean deleteTransponderById(long transponderId) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            TransponderDTO transponder = session.get(TransponderDTO.class, transponderId);
            if (transponder != null) {
                session.delete(transponder);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Errore durante l'eliminazione del transponder.", e);
        }
    }


}
