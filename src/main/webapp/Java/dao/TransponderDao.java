import java.sql.SQLException;
import java.util.List;

public interface TransponderDAO {
    void insertTransponder(TransponderDTO transponder) throws SQLException;

    TransponderDTO getTransponderByCodice(String codice) throws SQLException;

    List<TransponderDTO> getAllTransponders() throws SQLException;

    void updateTransponder(TransponderDTO transponder) throws SQLException;

    void deleteTransponder(String codice) throws SQLException;
}
