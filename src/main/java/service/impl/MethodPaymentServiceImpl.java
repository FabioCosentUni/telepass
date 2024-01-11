package service.impl;

import dao.MethodPaymentDAO;
import exception.DaoException;
import exception.TelepassError;
import exception.TelepassException;
import model.MethodPayment;
import service.MethodPaymentService;


public class MethodPaymentServiceImpl implements MethodPaymentService {

    private final MethodPaymentDAO methodPaymentDAO;

    public MethodPaymentServiceImpl(MethodPaymentDAO methodPaymentDAO) {
        this.methodPaymentDAO = methodPaymentDAO;
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
