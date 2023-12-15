package dao.impl;

import dao.CaselloDAO;
import model.Casello;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class CaselloDAOImpl implements CaselloDAO {


    @Override
    public boolean insertCasello(Casello casello) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(casello);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Casello getCaselloById(long caselloId) throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.get(Casello.class, caselloId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Casello> getAllCaselli()throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Casello> query = session.createQuery("FROM Casello", Casello.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // o gestisci l'errore in modo appropriato per la tua applicazione
        }
    }

    @Override
    public List<String> getAllAutostrade() throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<String> query = session.createQuery("SELECT DISTINCT autostrada FROM Casello", String.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    @Override
    public boolean updateCasello(Casello casello) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(casello);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public boolean deleteCaselloById(long caselloId) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Casello casello = session.get(Casello.class, caselloId);
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
        return false;
    }
}
