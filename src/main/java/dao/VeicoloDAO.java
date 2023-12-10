package dao;

import model.Veicolo;

import java.sql.SQLException;
import java.util.List;

public interface VeicoloDAO {
    boolean insertVeicolo(Veicolo veicolo) throws SQLException;

    Veicolo getVeicoloByTarga(String targa) throws SQLException;

    List<Veicolo> getAllVeicoli() throws SQLException;

    boolean updateVeicolo(Veicolo veicolo) throws SQLException;

    boolean deleteVeicoloByTarga(String targa) throws SQLException;
}
