package service;

import exception.TelepassException;
import model.Utente;
import model.Veicolo;

import java.util.List;

public interface VeicoloService {

    void validateVeicolo(Veicolo veicolo) throws TelepassException;

    List<Veicolo> getVeicoliUtente(Utente u) throws TelepassException;
}
