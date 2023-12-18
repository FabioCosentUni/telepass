package service;

import exception.user.UserException;
import model.Utente;

import java.sql.SQLException;

public interface UtenteService {

     Utente login(String email, String password) throws SQLException, UserException;

     void register(Utente utente/*, PaymentOption paymentOption*/) throws SQLException, UserException;

     boolean richiediNuovoVeicoloTransponder(String targa);

     boolean richiediTelepassPlus(long idTransponder);

}
