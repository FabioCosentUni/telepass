package service;

import exception.TelepassException;
import model.Utente;
import model.Veicolo;

import java.sql.SQLException;
import java.util.List;

public interface VeicoloService {

    void validateVeicolo(Veicolo veicolo) throws TelepassException;
}
