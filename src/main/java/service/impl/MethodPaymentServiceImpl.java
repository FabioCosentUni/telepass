package service.impl;

import dao.MethodPaymentDAO;
import dao.TransponderDAO;
import dao.UtenteDAO;
import dao.impl.MethodPaymentDAOImpl;
import dao.impl.TransponderDAOImpl;
import dao.impl.UtenteDAOImpl;
import exception.DaoException;
import exception.TelepassError;
import exception.TelepassException;
import model.MethodPayment;
import model.Utente;
import service.MethodPaymentService;

import java.sql.SQLException;
import java.util.List;


public class MethodPaymentServiceImpl implements MethodPaymentService {

    private MethodPaymentDAO methodPaymentDAO;
    private TransponderDAO transponderDAO;

    private UtenteDAO utenteDAO;

    public MethodPaymentServiceImpl() {
        methodPaymentDAO = new MethodPaymentDAOImpl();
        transponderDAO = new TransponderDAOImpl();
        utenteDAO = new UtenteDAOImpl();
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

    @Override
    public Utente saveMethodPayment(MethodPayment methodPayment, Utente u) throws SQLException, TelepassException {
        /*
        // Formattatore per la conversione della stringa in LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try
        {
            LocalDate data = LocalDate.parse(methodPayment.getScadenza().toString(), formatter);
            LocalDate sysdate = LocalDate.now();
            if(data.isAfter(sysdate))
                throw new TelepassException(TelepassError.CARD_EXPIRED);

            methodPaymentDAO.saveMetodoPagamento(methodPayment);
            Transponder t = transponderDAO.getDisponibilita();
            if(t!=null){
               // t.setMetodoPag(methodPayment);
                u.setTransponder(t);
                return utenteDAO.updateUtente(u);
            } else {
                throw new TelepassException(TelepassError.TRANSPONDER_NOT_AVAILABLE);
            }
        }
        catch (Exception e)
        {
            //throw new UserException(UserError.PAYMENT_OPTION_NOT_FOUND);
            e.printStackTrace();
            return null;
        }

         */
        return null;
    }

    @Override
    public MethodPayment getMethodPaymentByNumeroCarta(long numeroCarta) {
        return null;
    }

    @Override
    public List<MethodPayment> getAllMethodPayments() throws SQLException {
        return null;
    }

    @Override
    public void updateMethodPayment(MethodPayment methodPayment) throws SQLException {

    }

    @Override
    public void deleteMethodPayment(long numeroCarta) throws SQLException {

    }
/*
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
    }*/
}
