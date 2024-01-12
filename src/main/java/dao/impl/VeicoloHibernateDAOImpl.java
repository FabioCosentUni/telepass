package dao.impl;

import dao.VeicoloDAO;
import exception.DaoException;
import model.Utente;
import model.Veicolo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.util.List;
/**
 * Implementazione specifica del DAO per l'entità Veicolo utilizzando Hibernate per
 * l'interazione con il database.
 */
public class VeicoloHibernateDAOImpl extends BaseHibernateDAOImpl<Veicolo, String> implements VeicoloDAO {

    /**
     * Costruttore che inizializza la classe DAO impostando il tipo di entità gestita della superclasse.
     */
    public VeicoloHibernateDAOImpl() {
        super(Veicolo.class);
    }

    /**
     * Ottiene la lista dei veicoli associati a un utente.
     *
     * @param u Utente per il quale si desidera ottenere la lista dei veicoli.
     * @return Lista dei veicoli associati all'utente.
     * @throws DaoException se si verifica un errore durante il recupero dei veicoli.
     */
    @Override
    public List<Veicolo> getVeicoliUtente(Utente u) throws DaoException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Veicolo> query = session.createQuery(
                    "select v from Veicolo v " +
                            "join fetch v.transponder transp " +
                            "join fetch transp.utente " +
                            "where v.transponder = :transp",
                    Veicolo.class
            );
            query.setParameter("transp", u.getTransponder());
            return query.getResultList();
        } catch (HibernateException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
