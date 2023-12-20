package dao.impl;

import dao.TransponderDAO;
import exception.DaoException;
import model.Transponder;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.util.List;

public class TransponderDAOImplImpl extends BaseDaoImpl<Transponder, String> implements TransponderDAO {


    public TransponderDAOImplImpl() {
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
}
