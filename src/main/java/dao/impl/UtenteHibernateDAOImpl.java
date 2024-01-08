package dao.impl;

import dao.UtenteHibernateDAO;
import exception.DaoException;
import model.Utente;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

public class UtenteHibernateDAOImpl extends BaseHibernateDAOImpl<Utente, String> implements UtenteHibernateDAO {

    /**
     * Costruttore che inizializza la classe DAO impostando il tipo di entità gestita.
     */
    public UtenteHibernateDAOImpl() {
        super(Utente.class);
    }

    /**
     * Ottiene un utente basato sull'indirizzo email specificato.
     *
     * @param email Indirizzo email dell'utente da cercare.
     * @return L'utente corrispondente all'indirizzo email specificato, se presente; altrimenti, null.
     * @throws DaoException se si verifica un errore durante il recupero dell'utente dal database.
     */
    @Override
    public Utente getUtenteByEmail(String email) throws DaoException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Utente> query = session.createQuery("FROM Utente u WHERE u.email = :email", Utente.class);
            query.setParameter("email", email);
            if (query.list().isEmpty()) {
                return null;
            }
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DaoException("Errore durante il recupero dell'utente", e);
        }
    }
}
