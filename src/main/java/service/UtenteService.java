package service;

import exception.TelepassException;
import model.Utente;
import model.Veicolo;

public interface UtenteService {

     Utente login(String email, String password) throws TelepassException;

     void register(Utente utente, Veicolo v) throws TelepassException;

     void validateUser(Utente u) throws TelepassException;

     Utente getUtenteByCF(String cf) throws TelepassException;

     Utente addVehicle(Utente u, Veicolo v) throws TelepassException;

}
