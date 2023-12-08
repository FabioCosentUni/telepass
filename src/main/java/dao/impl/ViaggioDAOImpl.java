package dao.impl;

import dao.ViaggioDAO;
import dto.ViaggioDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.sql.SQLException;
import java.util.List;

public class ViaggioDAOImpl implements ViaggioDAO {
    @Override
    public void insertViaggio(ViaggioDTO viaggio) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(viaggio);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Errore durante l'inserimento del viaggio.", e);
        }
    }


    @Override
    public ViaggioDTO getViaggioById(long viaggioId) {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.get(ViaggioDTO.class, viaggioId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<ViaggioDTO> getAllViaggi() throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<ViaggioDTO> query = session.createQuery("FROM ViaggioDTO", ViaggioDTO.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Errore durante il recupero di tutti i viaggi.", e);
        }
    }


    @Override
    public void updateViaggio(ViaggioDTO viaggio) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(viaggio);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Errore durante l'aggiornamento del viaggio.", e);
        }
    }


    @Override
    public void deleteViaggioById(long viaggioId) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            ViaggioDTO viaggio = session.get(ViaggioDTO.class, viaggioId);
            if (viaggio != null) {
                session.delete(viaggio);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Errore durante l'eliminazione del viaggio.", e);
        }
    }


}
