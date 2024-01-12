package service;

import exception.TelepassException;
import model.Casello;
import model.Utente;
import model.Veicolo;
import model.Viaggio;
import model.bo.StatisticsBO;

import java.util.List;
import java.util.Map;

/**
 * Interfaccia per la gestione dei servizi relativi ai viaggi nel sistema Telepass.
 */
public interface ViaggioService {

    /**
     * Registra un nuovo viaggio nel sistema Telepass.
     *
     * @param entry L'ID del casello di entrata.
     * @param exit  L'ID del casello di uscita.
     * @param targa La targa del veicolo associato al viaggio.
     * @throws TelepassException se si verifica un errore durante l'inserimento del viaggio.
     */
    void insertViaggio(Long entry, Long exit, String targa) throws TelepassException;

    /**
     * Ottiene l'importo totale pagato per ciascun veicolo associato a un utente.
     *
     * @param utente L'utente di cui si vogliono ottenere gli importi totali pagati per i veicoli.
     * @return Una mappa che associa ciascun veicolo all'importo totale pagato.
     * @throws TelepassException se si verifica un errore durante il recupero degli importi totali pagati.
     */
    Map<Veicolo, Float> getImportoTotalePagatoPerVeicolo(Utente utente) throws TelepassException;

    /**
     * Ottiene le statistiche relative ai caselli del sistema Telepass.
     *
     * @return Una mappa che associa ciascun casello alle relative statistiche.
     * @throws TelepassException se si verifica un errore durante il recupero delle statistiche dei caselli.
     */
    Map<Casello, StatisticsBO> getStatisticheCaselli() throws TelepassException;

    /**
     * Ottiene la lista dei viaggi per ciascun veicolo nella lista fornita.
     *
     * @param veicoli La lista dei veicoli di cui si vogliono ottenere i viaggi.
     * @return Una mappa che associa ciascun veicolo alla lista dei suoi viaggi.
     * @throws TelepassException se si verifica un errore durante il recupero dei viaggi per veicolo.
     */
    Map<Veicolo, List<Viaggio>> getViaggiPerVeicoli(List<Veicolo> veicoli) throws TelepassException;
}
