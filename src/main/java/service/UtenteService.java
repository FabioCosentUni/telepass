package service;

import model.Utente;

import java.util.List;

public interface UtenteService {

     boolean insertUtente(Utente utente);

     List<Utente> getAllUtenti();

     boolean updateUtente(Utente utente);

     boolean deleteUtenteByCodiceFiscale(String codiceFiscale);

     Utente getUtenteByCodiceFiscale(String codiceFiscale);

     boolean richiediNuovoVeicoloTransponder(String targa);

     boolean richiediTelepassPlus(long idTransponder);

}
