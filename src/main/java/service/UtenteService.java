package service;

import exception.DaoException;
import exception.TelepassException;
import model.Utente;
import model.Veicolo;

import java.sql.SQLException;

public interface UtenteService {

     Utente login(String email, String password) throws TelepassException;

     void register(Utente utente, Veicolo v) throws TelepassException;

     boolean richiediNuovoVeicoloTransponder(String targa);

     boolean richiediTelepassPlus(long idTransponder);

     void validateUser(Utente u) throws TelepassException;

}
