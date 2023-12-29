package service;

import exception.DaoException;
import exception.TelepassException;
import model.Utente;
import model.Veicolo;

import java.sql.SQLException;

public interface UtenteService {

     Utente login(String email, String password) throws TelepassException;

     void register(Utente utente, Veicolo v) throws TelepassException;

     void validateUser(Utente u) throws TelepassException;

     Utente getUtenteByCF(String cf) throws TelepassException;

}
