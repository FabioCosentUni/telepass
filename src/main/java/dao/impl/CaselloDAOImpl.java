package dao.impl;

import dao.CaselloDAO;
import dto.CaselloDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class CaselloDAOImpl implements CaselloDAO {


    @Override
    public void insertCasello(CaselloDTO casello) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(casello);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public CaselloDTO getCaselloById(long caselloId) throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.get(CaselloDTO.class, caselloId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CaselloDTO> getAllCaselli()throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<CaselloDTO> query = session.createQuery("FROM CaselloDTO", CaselloDTO.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // o gestisci l'errore in modo appropriato per la tua applicazione
        }
    }


    @Override
    public void updateCasello(CaselloDTO casello) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(casello);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }



    @Override
    public void deleteCaselloById(long caselloId) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CaselloDTO casello = session.get(CaselloDTO.class, caselloId);
            if (casello != null) {
                session.delete(casello);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
