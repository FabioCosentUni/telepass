package dao.impl;

import dao.CaselloHibernateDAO;
import model.Casello;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.util.Collections;
import java.util.List;

public class CaselloHibernateDAOImpl extends BaseHibernateDAOImpl<Casello, Long> implements CaselloHibernateDAO {

    /**
     * Costruttore che inizializza la classe DAO impostando il tipo di entità gestita.
     */
    public CaselloHibernateDAOImpl() {
        super(Casello.class);
    }
}
