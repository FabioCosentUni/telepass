package service.impl;

import dao.MethodPaymentDAO;
import dao.TransponderDAO;
import dao.UtenteDAO;
import exception.user.UserError;
import exception.user.UserException;
import model.Method_payment;
import model.Transponder;
import model.Utente;
import service.MethodPaymentService;

import java.sql.SQLException;
import java.util.List;


public class MethodPaymentServiceImpl implements MethodPaymentService {

    private MethodPaymentDAO methodPaymentDAO;
    private TransponderDAO transponderDAO;

    private UtenteDAO utenteDAO;

    @Override
    public Utente saveMethodPayment(Method_payment methodPayment, Utente u) throws SQLException, UserException {
        methodPaymentDAO.saveMetodoPagamento(methodPayment);
        Transponder t = transponderDAO.getDisponibilita();
        if(t!=null){
            t.setMetodoPag(methodPayment);
            u.setTransponder(t);
            return utenteDAO.updateUtente(u);
        } else {
            throw new UserException(UserError.TRANSPONDER_NOT_AVAILABLE);
        }
    }

    @Override
    public Method_payment getMethodPaymentByNumeroCarta(long numeroCarta) {
        return methodPaymentDAO.getMetodoPagamentoByNumeroCarta(numeroCarta);
    }

    @Override
    public List<Method_payment> getAllMethodPayments() throws SQLException {
        return methodPaymentDAO.getAllMetodiPagamento();
    }

    @Override
    public void updateMethodPayment(Method_payment methodPayment) throws SQLException {
        methodPaymentDAO.updateMetodoPagamento(methodPayment);
    }

    @Override
    public void deleteMethodPayment(long numeroCarta) throws SQLException {
        methodPaymentDAO.deleteMetodoPagamento(numeroCarta);
    }
}
