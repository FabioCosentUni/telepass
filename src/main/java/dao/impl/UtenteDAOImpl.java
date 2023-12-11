package dao.impl;

import dao.UtenteDAO;
import model.Utente;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.sql.SQLException;
import java.util.List;

public class UtenteDAOImpl implements UtenteDAO {

    @Override
    public Utente getUtenteByEmail(String email) throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Utente> query = session.createQuery("FROM Utente WHERE email = :email", Utente.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Errore durante il recupero dell'utente.", e);
        }
    }

    @Override
    public boolean insertUtente(Utente utente) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(utente);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Errore durante l'inserimento dell'utente.", e);
        }
    }


    @Override
    public Utente getUtenteByCodiceFiscale(String codiceFiscale) {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.get(Utente.class, codiceFiscale);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<Utente> getAllUtenti() throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Utente> query = session.createQuery("FROM Utente", Utente.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Errore durante il recupero di tutti gli utenti.", e);
        }
    }


    @Override
    public boolean updateUtente(Utente utente) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(utente);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Errore durante l'aggiornamento dell'utente.", e);
        }
    }


    @Override
    public boolean deleteUtenteByCodiceFiscale(String codiceFiscale) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Utente utente = session.get(Utente.class, codiceFiscale);
            if (utente != null) {
                session.delete(utente);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Errore durante l'eliminazione dell'utente.", e);
        }
    }

}
