package dao.impl;

import dao.MethodPaymentHibernateDAO;
import model.MethodPayment;

public class MethodPaymentHibernateDAOImpl extends BaseHibernateDAOImpl<MethodPayment, String> implements MethodPaymentHibernateDAO {

    /**
     * Costruttore che inizializza la classe DAO impostando il tipo di entità gestita.
     */
    public MethodPaymentHibernateDAOImpl() {
        super(MethodPayment.class);
    }
}
