package service;

import model.Veicolo;

import java.util.List;

public interface VeicoloService {

             boolean insertVeicolo(Veicolo veicolo);

             List<Veicolo> getAllVeicoli();

             boolean updateVeicolo(Veicolo veicolo);

             boolean deleteVeicoloByTarga(String targa);

             Veicolo getVeicoloByTarga(String targa);

             void entraNelCasello(Veicolo veicolo, long idCasello);

             void esceDalCasello(Veicolo veicolo, long idCasello);
}
