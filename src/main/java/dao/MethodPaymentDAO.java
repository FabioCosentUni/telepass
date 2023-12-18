package dao;

import model.Method_payment;

import java.sql.SQLException;
import java.util.List;

public interface MethodPaymentDAO {
    // Metodo per salvare un nuovo Metodo_pagamento
    void saveMetodoPagamento(Method_payment metodoPagamento) throws SQLException;

    // Metodo per ottenere un Metodo_pagamento tramite il numero della carta
    Method_payment getMetodoPagamentoByNumeroCarta(long numeroCarta);

    // Metodo per ottenere tutti i Metodo_pagamento
    List<Method_payment> getAllMetodiPagamento() throws SQLException;

    // Metodo per aggiornare un Metodo_pagamento esistente
    void updateMetodoPagamento(Method_payment metodoPagamento) throws SQLException;

    // Metodo per eliminare un Metodo_pagamento
    void deleteMetodoPagamento(long numeroCarta) throws SQLException;
}
