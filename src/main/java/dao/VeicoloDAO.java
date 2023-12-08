package dao;

import dto.VeicoloDTO;

import java.sql.SQLException;
import java.util.List;

public interface VeicoloDAO {
    void insertVeicolo(VeicoloDTO veicolo) throws SQLException;

    VeicoloDTO getVeicoloByTarga(String targa) throws SQLException;

    List<VeicoloDTO> getAllVeicoli() throws SQLException;

    void updateVeicolo(VeicoloDTO veicolo) throws SQLException;

    void deleteVeicoloByTarga(String targa) throws SQLException;
}
