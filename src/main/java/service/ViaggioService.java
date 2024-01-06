package service;

import exception.TelepassException;
import model.Casello;
import model.Utente;
import model.Veicolo;
import model.bo.StatisticsBO;
import oracle.ucp.util.Pair;

import java.util.List;
import java.util.Map;

public interface ViaggioService {

    void insertViaggio(Long entry, Long exit, String v) throws TelepassException;

    Map<Veicolo, Float> getImportoTotalePagatoPerVeicolo(Utente u) throws TelepassException;

    Map<Casello, StatisticsBO> getStatisticheCaselli() throws TelepassException;
}
