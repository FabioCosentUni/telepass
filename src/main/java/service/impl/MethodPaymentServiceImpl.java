package service.impl;

import dao.MethodPaymentDAO;
import exception.DaoException;
import exception.TelepassError;
import exception.TelepassException;
import model.MethodPayment;
import service.MethodPaymentService;

/**
 * Implementazione del servizio MethodPaymentService per la gestione dei metodi di pagamento nel sistema Telepass.
 */
public class MethodPaymentServiceImpl implements MethodPaymentService {

    private final MethodPaymentDAO methodPaymentDAO;

    /**
     * Costruttore che inizializza l'implementazione con un'istanza di MethodPaymentDAO.
     *
     * @param methodPaymentDAO DAO per la gestione dei metodi di pagamento.
     */
    public MethodPaymentServiceImpl(MethodPaymentDAO methodPaymentDAO) {
        this.methodPaymentDAO = methodPaymentDAO;
    }

    /**
     * Verifica la validità di un metodo di pagamento.
     *
     * @param methodPayment Il metodo di pagamento da convalidare.
     * @throws TelepassException se il metodo di pagamento non è valido o si verifica un errore durante la convalida.
     */
    @Override
    public void validateMethodPayment(MethodPayment methodPayment) throws TelepassException {
        try {
            MethodPayment existingMethodPayment = methodPaymentDAO.findById(methodPayment.getNumCartaPK());
            if (existingMethodPayment != null) {
                if (!existingMethodPayment.equals(methodPayment)) {
                    throw new TelepassException(TelepassError.METHOD_PAYMENT_DIFFERENT);
                }
            }
        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }
}
