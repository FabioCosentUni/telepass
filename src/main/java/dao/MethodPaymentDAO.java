package dao;

import model.MethodPayment;

import java.sql.SQLException;
import java.util.List;

public interface MethodPaymentDAO {
    // Metodo per salvare un nuovo Metodo_pagamento
    void saveMetodoPagamento(MethodPayment metodoPagamento) throws SQLException;

    // Metodo per ottenere un Metodo_pagamento tramite il numero della carta
    MethodPayment getMetodoPagamentoByNumeroCarta(long numeroCarta);

    // Metodo per ottenere tutti i Metodo_pagamento
    List<MethodPayment> getAllMetodiPagamento() throws SQLException;

    // Metodo per aggiornare un Metodo_pagamento esistente
    void updateMetodoPagamento(MethodPayment metodoPagamento) throws SQLException;

    // Metodo per eliminare un Metodo_pagamento
    void deleteMetodoPagamento(long numeroCarta) throws SQLException;
}
