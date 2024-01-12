package dao.impl;

import dao.CaselloDAO;
import model.Casello;

public class CaselloHibernateDAOImpl extends BaseHibernateDAOImpl<Casello, Long> implements CaselloDAO {

    /**
     * Costruttore che inizializza la classe DAO impostando il tipo di entità gestita dalla superclasse.
     */
    public CaselloHibernateDAOImpl() {
        super(Casello.class);
    }
}
