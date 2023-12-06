package dao;

import dto.ViaggioDTO;

import java.sql.SQLException;
import java.util.List;

public interface ViaggioDAO {
    void insertViaggio(ViaggioDTO viaggio) throws SQLException;

    ViaggioDTO getViaggioByTarga(String targa) throws SQLException;

    List<ViaggioDTO> getAllViaggi() throws SQLException;

    void updateViaggio(ViaggioDTO viaggio) throws SQLException;

    void deleteViaggio(String targa) throws SQLException;
}
