package dao.impl;

import dao.TransponderHibernateDAO;
import exception.DaoException;
import model.Transponder;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'interfaccia {@link TransponderHibernateDAO} che gestisce l'accesso
 * ai dati dei transponder utilizzando Hibernate come ORM.
 */
public class TransponderHibernateDAOImpl extends BaseHibernateDAOImpl<Transponder, String> implements TransponderHibernateDAO {

    /**
     * Costruttore che inizializza la classe DAO per la gestione dei transponder.
     */
    public TransponderHibernateDAOImpl() {
        super(Transponder.class);
    }

    /**
     * Restituisce una lista di transponder attivi associati a un utente.
     *
     * @return Una lista di oggetti {@link Transponder} rappresentanti i transponder attivi.
     * @throws DaoException se si verifica un errore durante il recupero dei transponder attivi.
     */
    @Override
    public List<Transponder> getActiveTransponders() throws DaoException {
        List<Transponder> activeTransponders;

        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Transponder> query = session.createQuery("FROM Transponder t WHERE t.utente is not null", Transponder.class);
            activeTransponders = query.list();
        } catch (HibernateException e) {
            throw new DaoException("Errore durante il recupero dei transponder attivi", e);
        }

        return activeTransponders;
    }

    @Override
    public List<Transponder> getFreeTransponders() throws DaoException {
        List<Transponder> freeTransponders;

        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Transponder> query = session.createQuery("FROM Transponder t WHERE t.utente is null", Transponder.class);
            freeTransponders = query.list();
        } catch (HibernateException e) {
            throw new DaoException("Errore durante il recupero dei transponder attivi", e);
        }

        return freeTransponders;
    }
}
