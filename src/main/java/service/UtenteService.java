package service;

import exception.user.UserException;
import model.Utente;

import java.sql.SQLException;

public interface UtenteService {

     Utente login(String email, String password) throws SQLException, UserException;

     boolean register(Utente utente);

     boolean richiediNuovoVeicoloTransponder(String targa);

     boolean richiediTelepassPlus(long idTransponder);

}
