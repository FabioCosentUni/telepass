package dao.impl;

import dao.VeicoloDAO;
import dto.VeicoloDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.sql.SQLException;
import java.util.List;

public class VeicoloDAOImpl implements VeicoloDAO {

    @Override
    public boolean insertVeicolo(VeicoloDTO veicolo) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(veicolo);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Errore durante l'inserimento del veicolo.", e);
        }
    }


    @Override
    public VeicoloDTO getVeicoloByTarga(String targa) {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.get(VeicoloDTO.class, targa);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<VeicoloDTO> getAllVeicoli() throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<VeicoloDTO> query = session.createQuery("FROM VeicoloDTO", VeicoloDTO.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Errore durante il recupero di tutti i veicoli.", e);
        }
    }


    @Override
    public boolean updateVeicolo(VeicoloDTO veicolo) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(veicolo);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Errore durante l'aggiornamento del veicolo.", e);
        }
    }


    @Override
    public boolean deleteVeicoloByTarga(String targa) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            VeicoloDTO veicolo = session.get(VeicoloDTO.class, targa);
            if (veicolo != null) {
                session.delete(veicolo);
            }

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Errore durante l'eliminazione del veicolo.", e);
        }
    }

}