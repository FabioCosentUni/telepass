package service;

import exception.TelepassException;
import model.MethodPayment;

/**
 * Interfaccia che definisce i servizi disponibili per la gestione dei metodi di pagamento nel sistema Telepass.
 */
public interface MethodPaymentService {

    /**
     * Convalida un metodo di pagamento nel sistema Telepass.
     *
     * @param methodPayment Il metodo di pagamento da convalidare.
     * @throws TelepassException se si verifica un errore durante la convalida del metodo di pagamento.
     */
    void validateMethodPayment(MethodPayment methodPayment) throws TelepassException;
}
