package dao;

import dto.VeicoloDTO;

import java.sql.SQLException;
import java.util.List;

public interface VeicoloDAO {
    boolean insertVeicolo(VeicoloDTO veicolo) throws SQLException;

    VeicoloDTO getVeicoloByTarga(String targa) throws SQLException;

    List<VeicoloDTO> getAllVeicoli() throws SQLException;

    boolean updateVeicolo(VeicoloDTO veicolo) throws SQLException;

    boolean deleteVeicoloByTarga(String targa) throws SQLException;
}
