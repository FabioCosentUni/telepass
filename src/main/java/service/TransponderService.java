package service;

import exception.TelepassException;
import model.Transponder;

import java.util.List;

/**
 * Interfaccia che definisce i servizi disponibili per la gestione dei transponder nel sistema Telepass.
 */
public interface TransponderService {

    /**
     * Inserisce un nuovo transponder nel sistema Telepass.
     *
     * @param transponder Il transponder da inserire.
     * @throws TelepassException se si verifica un errore durante l'inserimento del transponder.
     */
    void insert(Transponder transponder) throws TelepassException;

    /**
     * Restituisce una lista di transponder attivi nel sistema Telepass.
     *
     * @return Una lista di transponder attivi.
     * @throws TelepassException se si verifica un errore durante il recupero dei transponder attivi.
     */
    List<Transponder> getActiveTransponders() throws TelepassException;

    /**
     * Rende il contratto Telepass come Telepass+.
     *
     * @param transponder Il transponder su cui effettuare l'operazione.
     * @throws TelepassException se si verifica un errore durante l'aggiunta del credito.
     */
    void makePlus(Transponder transponder) throws TelepassException;

    /**
     * Revoca un transponder dal sistema Telepass.
     *
     * @param transponderCode Il codice del transponder da revocare.
     * @throws TelepassException se si verifica un errore durante la revoca del transponder.
     */
    void revokeTransponder(String transponderCode) throws TelepassException;
}
