package dao;

import model.Metodo_pagamento;

import java.sql.SQLException;
import java.util.List;

public interface MethodPaymentDAO {
    // Metodo per salvare un nuovo Metodo_pagamento
    void saveMetodoPagamento(Metodo_pagamento metodoPagamento) throws SQLException;

    // Metodo per ottenere un Metodo_pagamento tramite il numero della carta
    Metodo_pagamento getMetodoPagamentoByNumeroCarta(long numeroCarta);

    // Metodo per ottenere tutti i Metodo_pagamento
    List<Metodo_pagamento> getAllMetodiPagamento() throws SQLException;

    // Metodo per aggiornare un Metodo_pagamento esistente
    void updateMetodoPagamento(Metodo_pagamento metodoPagamento) throws SQLException;

    // Metodo per eliminare un Metodo_pagamento
    void deleteMetodoPagamento(long numeroCarta) throws SQLException;
}
