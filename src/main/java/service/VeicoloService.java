package service;

import exception.TelepassException;
import model.Utente;
import model.Veicolo;

import java.util.List;

/**
 * Interfaccia per la gestione dei servizi relativi ai veicoli nel sistema Telepass.
 */
public interface VeicoloService {

    /**
     * Convalida un veicolo verificandone la correttezza e la disponibilità.
     *
     * @param veicolo Il veicolo da convalidare.
     * @throws TelepassException se si verifica un errore durante la convalida del veicolo.
     */
    void validateVeicolo(Veicolo veicolo) throws TelepassException;

    /**
     * Restituisce la lista dei veicoli associati a un utente.
     *
     * @param utente L'utente di cui si vogliono ottenere i veicoli associati.
     * @return La lista dei veicoli associati all'utente.
     * @throws TelepassException se si verifica un errore durante il recupero dei veicoli dell'utente.
     */
    List<Veicolo> getVeicoliUtente(Utente utente) throws TelepassException;
}
