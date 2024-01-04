package service;

import exception.TelepassException;
import model.Casello;
import model.Utente;
import model.Veicolo;
import model.Viaggio;
import model.bo.StatisticsBO;
import oracle.ucp.util.Pair;

import java.util.List;
import java.util.Map;

public interface ViaggioService {

    void insertViaggio(Long entry, Long exit, String v) throws TelepassException;

    List<Viaggio> getAllViaggi();

    boolean updateViaggio(Viaggio viaggio);

    boolean deleteViaggioById(long viaggioId);

    Viaggio getViaggioById(long viaggioId);

    List<Pair<Veicolo, Integer>> getImportoTotalePagatoPerVeicolo(Utente u) throws TelepassException;

    Map<Casello, StatisticsBO> getStatisticheCaselli() throws TelepassException;
}
