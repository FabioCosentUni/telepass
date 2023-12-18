package service;

import exception.user.UserException;
import model.Method_payment;
import model.Utente;

import java.sql.SQLException;
import java.util.List;

public interface MethodPaymentService {
    Utente saveMethodPayment(Method_payment methodPayment, Utente u) throws SQLException, UserException;

    Method_payment getMethodPaymentByNumeroCarta(long numeroCarta);

    List<Method_payment> getAllMethodPayments() throws SQLException;

    void updateMethodPayment(Method_payment methodPayment) throws SQLException;

    void deleteMethodPayment(long numeroCarta) throws SQLException;
}
