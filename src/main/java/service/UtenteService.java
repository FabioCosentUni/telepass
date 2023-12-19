package service;

import exception.DaoException;
import exception.TelepassException;
import model.Utente;
import model.Veicolo;

import java.sql.SQLException;

public interface UtenteService {

     Utente login(String email, String password) throws SQLException, TelepassException, DaoException;

     void register(Utente utente, Veicolo v) throws SQLException, TelepassException, DaoException;

     boolean richiediNuovoVeicoloTransponder(String targa);

     boolean richiediTelepassPlus(long idTransponder);

}
