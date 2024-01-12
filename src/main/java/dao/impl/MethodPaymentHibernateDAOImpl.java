package dao.impl;

import dao.MethodPaymentDAO;
import model.MethodPayment;

public class MethodPaymentHibernateDAOImpl extends BaseHibernateDAOImpl<MethodPayment, String> implements MethodPaymentDAO {

    /**
     * Costruttore che inizializza la classe DAO impostando il tipo di entità gestita dalla superclasse.
     */
    public MethodPaymentHibernateDAOImpl() {
        super(MethodPayment.class);
    }
}
