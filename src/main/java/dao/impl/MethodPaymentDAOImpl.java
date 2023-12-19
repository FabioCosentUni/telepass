package dao.impl;

import dao.BaseDao;
import dao.MethodPaymentDAO;
import model.MethodPayment;

public class MethodPaymentDAOImpl extends BaseDao<MethodPayment, String> implements MethodPaymentDAO {

    public MethodPaymentDAOImpl() {
        super(MethodPayment.class);
    }

}
