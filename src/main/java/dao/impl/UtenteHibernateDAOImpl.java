package dao.impl;

import dao.UtenteHibernateDAO;
import exception.DaoException;
import model.Utente;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

public class UtenteHibernateDAOImpl extends BaseHibernateDaoImpl<Utente, String> implements UtenteHibernateDAO {

    public UtenteHibernateDAOImpl() {
        super(Utente.class);
    }

    @Override
    public Utente getUtenteByEmail(String email) throws DaoException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Utente> query = session.createQuery("FROM Utente u WHERE u.email = :email", Utente.class);
            query.setParameter("email", email);
            if(query.list().size() == 0) {
                return null;
            }
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DaoException("Errore durante il recupero dell'utente ", e);
        }
    }

}
