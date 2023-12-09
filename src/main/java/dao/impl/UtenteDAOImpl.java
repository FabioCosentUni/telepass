package dao.impl;

import dao.UtenteDAO;
import dto.UtenteDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.sql.SQLException;
import java.util.List;

public class UtenteDAOImpl implements UtenteDAO {

    @Override
    public boolean insertUtente(UtenteDTO utente) throws SQLException {
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
    public UtenteDTO getUtenteByCodiceFiscale(String codiceFiscale) {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.get(UtenteDTO.class, codiceFiscale);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<UtenteDTO> getAllUtenti() throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<UtenteDTO> query = session.createQuery("FROM UtenteDTO", UtenteDTO.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Errore durante il recupero di tutti gli utenti.", e);
        }
    }


    @Override
    public boolean updateUtente(UtenteDTO utente) throws SQLException {
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

            UtenteDTO utente = session.get(UtenteDTO.class, codiceFiscale);
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
