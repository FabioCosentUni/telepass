package dao.impl;

import dao.MethodPaymentDAO;
import model.MethodPayment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.sql.SQLException;
import java.util.List;

public class MethodPaymentDAOImpl implements MethodPaymentDAO {

    @Override
    public void saveMetodoPagamento(MethodPayment metodoPagamento) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(metodoPagamento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Errore durante l'inserimento/aggiornamento del metodo di pagamento.", e);
        }
    }

    @Override
    public MethodPayment getMetodoPagamentoByNumeroCarta(long numeroCarta) {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.get(MethodPayment.class, numeroCarta);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<MethodPayment> getAllMetodiPagamento() throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<MethodPayment> query = session.createQuery("FROM MethodPayment", MethodPayment.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Errore durante il recupero di tutti i metodi di pagamento.", e);
        }
    }

    @Override
    public void updateMetodoPagamento(MethodPayment metodoPagamento) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(metodoPagamento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Errore durante l'aggiornamento del metodo di pagamento.", e);
        }
    }

    @Override
    public void deleteMetodoPagamento(long numeroCarta) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            MethodPayment metodoPagamento = session.get(MethodPayment.class, numeroCarta);
            if (metodoPagamento != null) {
                session.delete(metodoPagamento);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Errore durante l'eliminazione del metodo di pagamento.", e);
        }
    }
}
