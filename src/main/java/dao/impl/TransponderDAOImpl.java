package dao.impl;

import dao.TransponderDAO;
import exception.DaoException;
import model.Transponder;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.util.List;

public class TransponderDAOImpl extends BaseDaoImpl<Transponder, String> implements TransponderDAO {


    public TransponderDAOImpl() {
        super(Transponder.class);
    }


    @Override
    public List<Transponder> getActiveTransponders() throws DaoException {
        List<Transponder> activeTransponders;

        try(Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Transponder> query = session.createQuery("FROM Transponder t WHERE t.utente is not null", Transponder.class);
            activeTransponders = query.list();
        } catch (HibernateException e) {
            throw new DaoException("Errore durante il recupero dei transponder attivi", e);
        }

        return activeTransponders;
    }

    @Override
    public Transponder findFreeTransponder() throws DaoException {
        Transponder t = null;
        try(Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Transponder> query = session.createQuery("FROM Transponder t WHERE t.utente is null", Transponder.class);
            List<Transponder> resultList = query.list();

            if (!resultList.isEmpty()) {
                t = resultList.get(0);
            }
        } catch (HibernateException e) {
            throw new DaoException("Errore durante il recupero dei transponder attivi", e);
        }
        return t;
    }

    @Override
    public void revokeTransponder(String transponderCode) throws DaoException {
        /*
        Transaction transaction = null;

        try(Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Transponder transponder = session.get(Transponder.class, transponderCode);

            transponder.setUtente(null);
            transponder.setVeicoloList(null);

            session.update(transponder);

            transaction.commit();

        } catch (HibernateException e) {

            if (transaction != null) {
                transaction.rollback();
            }

            throw new DaoException("Errore durante la revoca del transponder", e);
        }

         */
    }
}
