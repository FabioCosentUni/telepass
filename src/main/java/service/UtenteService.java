package service;

import exception.TelepassException;
import model.Utente;
import model.Veicolo;

/**
 * Interfaccia che definisce i servizi disponibili per la gestione degli utenti nel sistema Telepass.
 */
public interface UtenteService {

    /**
     * Effettua l'accesso di un utente utilizzando codice fiscale e password.
     *
     * @param cf    Codice Fiscale dell'utente.
     * @param password La password dell'utente.
     * @return L'utente loggato.
     * @throws TelepassException se si verifica un errore durante il login.
     */
    Utente login(String cf, String password) throws TelepassException;

    /**
     * Registra un nuovo utente nel sistema, associandogli un veicolo.
     *
     * @param utente L'utente da registrare.
     * @param v      Il veicolo da associare all'utente.
     * @throws TelepassException se si verifica un errore durante la registrazione.
     */
    void register(Utente utente, Veicolo v) throws TelepassException;

    /**
     * Convalida un utente nel sistema.
     *
     * @param u L'utente da convalidare.
     * @throws TelepassException se si verifica un errore durante la convalida dell'utente.
     */
    void validateUser(Utente u) throws TelepassException;

    /**
     * Ottiene un utente dal sistema tramite il suo Codice Fiscale.
     *
     * @param cf Il Codice Fiscale dell'utente da recuperare.
     * @return L'utente associato al Codice Fiscale fornito.
     * @throws TelepassException se si verifica un errore durante il recupero dell'utente.
     */
    Utente getUtenteByCF(String cf) throws TelepassException;

    /**
     * Aggiunge un veicolo all'utente nel sistema Telepass.
     *
     * @param u L'utente a cui aggiungere il veicolo.
     * @param v Il veicolo da aggiungere all'utente.
     * @return L'utente aggiornato con il veicolo associato.
     * @throws TelepassException se si verifica un errore durante l'aggiunta del veicolo.
     */
    Utente addVehicle(Utente u, Veicolo v) throws TelepassException;
}
