package service;

import exception.user.UserException;
import model.MethodPayment;
import model.Utente;

import java.sql.SQLException;
import java.util.List;

public interface MethodPaymentService {
    Utente saveMethodPayment(MethodPayment methodPayment, Utente u) throws SQLException, UserException;

    MethodPayment getMethodPaymentByNumeroCarta(long numeroCarta);

    List<MethodPayment> getAllMethodPayments() throws SQLException;

    void updateMethodPayment(MethodPayment methodPayment) throws SQLException;

    void deleteMethodPayment(long numeroCarta) throws SQLException;
}
