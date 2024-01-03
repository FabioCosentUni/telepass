package dao.impl;

import dao.MethodPaymentHibernateDAO;
import model.MethodPayment;

public class MethodPaymentHibernateDAOImpl extends BaseHibernateDaoImpl<MethodPayment, String> implements MethodPaymentHibernateDAO {

    public MethodPaymentHibernateDAOImpl() {
        super(MethodPayment.class);
    }

}
