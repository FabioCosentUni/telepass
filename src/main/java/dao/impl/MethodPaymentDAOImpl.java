package dao.impl;

import dao.MethodPaymentDAO;
import model.Method_payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.sql.SQLException;
import java.util.List;

public class MethodPaymentDAOImpl implements MethodPaymentDAO {

    @Override
    public void saveMetodoPagamento(Method_payment metodoPagamento) throws SQLException {
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
    public Method_payment getMetodoPagamentoByNumeroCarta(long numeroCarta) {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.get(Method_payment.class, numeroCarta);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Method_payment> getAllMetodiPagamento() throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Method_payment> query = session.createQuery("FROM Method_payment", Method_payment.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Errore durante il recupero di tutti i metodi di pagamento.", e);
        }
    }

    @Override
    public void updateMetodoPagamento(Method_payment metodoPagamento) throws SQLException {
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

            Method_payment metodoPagamento = session.get(Method_payment.class, numeroCarta);
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
