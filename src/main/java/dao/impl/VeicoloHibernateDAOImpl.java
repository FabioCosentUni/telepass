package dao.impl;

import dao.VeicoloHibernateDAO;
import exception.DaoException;
import model.Utente;
import model.Veicolo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.util.List;

public class VeicoloHibernateDAOImpl extends BaseHibernateDAOImpl<Veicolo, String> implements VeicoloHibernateDAO {


    public VeicoloHibernateDAOImpl() {
        super(Veicolo.class);
    }

    @Override
    public List<Veicolo> getVeicoliUtente(Utente u) throws DaoException {

        try(Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Veicolo> query = session.createQuery("from Veicolo where transponder = :transp", Veicolo.class);
            query.setParameter("transp", u.getTransponder());
            return query.getResultList();
        } catch (HibernateException e) {
            throw new DaoException(e.getMessage(), e);
        }

    }
}