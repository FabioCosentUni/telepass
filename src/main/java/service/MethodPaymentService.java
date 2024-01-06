package service;

import exception.TelepassException;
import model.MethodPayment;

public interface MethodPaymentService {

    void validateMethodPayment(MethodPayment methodPayment) throws TelepassException;
}
