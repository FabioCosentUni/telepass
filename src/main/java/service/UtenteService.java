package service;

import exception.TelepassException;
import model.Utente;

import java.sql.SQLException;

public interface UtenteService {

     Utente login(String email, String password) throws SQLException, TelepassException;

     void register(Utente utente/*, PaymentOption paymentOption*/) throws SQLException, TelepassException;

     boolean richiediNuovoVeicoloTransponder(String targa);

     boolean richiediTelepassPlus(long idTransponder);

}
