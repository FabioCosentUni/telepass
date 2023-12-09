package dao;

import dto.UtenteDTO;

import java.sql.SQLException;
import java.util.List;

public interface UtenteDAO {
    boolean insertUtente(UtenteDTO utente) throws SQLException;

    UtenteDTO getUtenteByCodiceFiscale(String codiceFiscale) throws SQLException;

    List<UtenteDTO> getAllUtenti() throws SQLException;

    boolean updateUtente(UtenteDTO utente) throws SQLException;

    boolean deleteUtenteByCodiceFiscale(String codiceFiscale) throws SQLException;
}
