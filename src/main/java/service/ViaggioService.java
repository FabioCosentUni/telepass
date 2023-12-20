package service;

import exception.TelepassException;
import model.Utente;
import model.Veicolo;
import model.Viaggio;
import oracle.ucp.util.Pair;

import java.util.List;

public interface ViaggioService {

             boolean insertViaggio(Viaggio viaggio);

             List<Viaggio> getAllViaggi();

             boolean updateViaggio(Viaggio viaggio);

             boolean deleteViaggioById(long viaggioId);

             Viaggio getViaggioById(long viaggioId);

             public List<Pair<Veicolo, Integer>> getImportoTotalePagatoPerVeicolo(Utente u) throws TelepassException;
}
