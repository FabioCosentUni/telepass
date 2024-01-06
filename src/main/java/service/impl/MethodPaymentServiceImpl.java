package service.impl;

import dao.MethodPaymentHibernateDAO;
import dao.impl.MethodPaymentHibernateDAOImpl;
import exception.DaoException;
import exception.TelepassError;
import exception.TelepassException;
import model.MethodPayment;
import service.MethodPaymentService;


public class MethodPaymentServiceImpl implements MethodPaymentService {

    private final MethodPaymentHibernateDAO methodPaymentDAO;

    public MethodPaymentServiceImpl() {
        methodPaymentDAO = new MethodPaymentHibernateDAOImpl();
    }


    @Override
    public void validateMethodPayment(MethodPayment methodPayment) throws TelepassException {
        try {
            MethodPayment mp = methodPaymentDAO.findById(methodPayment.getNumCartaPK());
            if(mp != null) {
                if(!mp.equals(methodPayment)) {
                    throw new TelepassException(TelepassError.METHOD_PAYMENT_DIFFERENT);
                }
            }
        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }
}
