package service;

import exception.user.VehicleException;
import model.Utente;
import model.Veicolo;

import java.sql.SQLException;
import java.util.List;

public interface VeicoloService {

             Veicolo insertVeicolo(Veicolo veicolo, Utente utente) throws SQLException, VehicleException;

             List<Veicolo> getAllVeicoli();

             boolean updateVeicolo(Veicolo veicolo);

             boolean deleteVeicoloByTarga(String targa);

             Veicolo getVeicoloByTarga(String targa);

             void entraNelCasello(Veicolo veicolo, long idCasello);

             void esceDalCasello(Veicolo veicolo, long idCasello);
}
