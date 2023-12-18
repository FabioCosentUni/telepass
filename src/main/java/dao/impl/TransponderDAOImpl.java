package dao.impl;

import dao.TransponderDAO;
import model.Transponder;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.sql.SQLException;
import java.util.List;

public class TransponderDAOImpl implements TransponderDAO {


    @Override
    public boolean insertTransponder(Transponder transponder) throws SQLException {
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
    public Transponder getTransponderByCodice(long transponderId) throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.get(Transponder.class, transponderId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<Transponder> getAllTransponders() throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Transponder> query = session.createQuery("FROM Transponder", Transponder.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Errore durante il recupero di tutti i transponder.", e);
        }
    }


    @Override
    public boolean updateTransponder(Transponder transponder) throws SQLException {
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

            Transponder transponder = session.get(Transponder.class, transponderId);
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

    @Override
    public Transponder getDisponibilita() throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            // Query per ottenere il transponder con Attivo = 0
            Query<Transponder> query = session.createQuery("FROM Transponder t WHERE t.attivo = 0", Transponder.class);
            query.setMaxResults(1);  // Limita la query a restituire solo un risultato

            List<Transponder> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new SQLException("Errore durante il recupero della disponibilità del transponder.", e);
        }
    }


}
