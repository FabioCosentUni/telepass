package dao.impl;

import dao.CaselloHibernateDAO;
import model.Casello;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class CaselloHibernateDAOImpl extends BaseHibernateDaoImpl<Casello, Long> implements CaselloHibernateDAO {

    /**
     * Costruttore che inizializza la classe DAO impostando il tipo di entità gestita.
     */
    public CaselloHibernateDAOImpl() {
        super(Casello.class);
    }

    /**
     * Ottiene una lista di nomi di tutte le autostrade presenti nei caselli.
     *
     * @return Una lista di stringhe rappresentanti i nomi delle autostrade.
     */
    @Override
    public List<String> getAllAutostrade() {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<String> query = session.createQuery("SELECT DISTINCT autostrada FROM Casello", String.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
