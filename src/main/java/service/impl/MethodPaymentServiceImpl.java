package service.impl;

import dao.MethodPaymentDAO;
import dao.TransponderDAO;
import dao.UtenteDAO;
import exception.user.UserError;
import exception.user.UserException;
import model.MethodPayment;
import model.Transponder;
import model.Utente;
import service.MethodPaymentService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class MethodPaymentServiceImpl implements MethodPaymentService {

    private MethodPaymentDAO methodPaymentDAO;
    private TransponderDAO transponderDAO;

    private UtenteDAO utenteDAO;

    @Override
    public Utente saveMethodPayment(MethodPayment methodPayment, Utente u) throws SQLException, UserException {
        // Formattatore per la conversione della stringa in LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try
        {
            LocalDate data = LocalDate.parse(methodPayment.getScadenza().toString(), formatter);
            LocalDate sysdate = LocalDate.now();
            if(data.isAfter(sysdate))
                throw new UserException(UserError.CARD_EXPIRED);

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
        catch (Exception e)
        {
            //throw new UserException(UserError.PAYMENT_OPTION_NOT_FOUND);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public MethodPayment getMethodPaymentByNumeroCarta(long numeroCarta) {
        return methodPaymentDAO.getMetodoPagamentoByNumeroCarta(numeroCarta);
    }

    @Override
    public List<MethodPayment> getAllMethodPayments() throws SQLException {
        return methodPaymentDAO.getAllMetodiPagamento();
    }

    @Override
    public void updateMethodPayment(MethodPayment methodPayment) throws SQLException {
        methodPaymentDAO.updateMetodoPagamento(methodPayment);
    }

    @Override
    public void deleteMethodPayment(long numeroCarta) throws SQLException {
        methodPaymentDAO.deleteMetodoPagamento(numeroCarta);
    }
}
