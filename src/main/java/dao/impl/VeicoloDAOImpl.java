package dao.impl;

import dao.VeicoloDAO;
import model.Veicolo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;
import utils.VeicoloWithoutTrans;

import java.sql.SQLException;
import java.util.List;

public class VeicoloDAOImpl implements VeicoloDAO {

    @Override
    public boolean insertVeicolo(Veicolo veicolo) throws SQLException {
        Transaction transaction = null;
        VeicoloWithoutTrans v = new VeicoloWithoutTrans();
        v.copyVeicolo(veicolo);
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(v);
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
    public Veicolo getVeicoloByTarga(String targa) {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.get(Veicolo.class, targa);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<Veicolo> getAllVeicoli() throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Veicolo> query = session.createQuery("FROM Veicolo", Veicolo.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Errore durante il recupero di tutti i veicoli.", e);
        }
    }


    @Override
    public boolean updateVeicolo(Veicolo veicolo) throws SQLException {
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

            Veicolo veicolo = session.get(Veicolo.class, targa);
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