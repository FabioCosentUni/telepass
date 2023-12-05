import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransponderDAOImpl implements TransponderDAO {

    private static final TransponderDAOImpl instance = new TransponderDAOImpl();

    private TransponderDAOImpl() {
        // Costruttore privato per rendere la classe un singleton
    }

    public static TransponderDAOImpl getInstance() {
        return instance;
    }
    @Override
    public void insertTransponder(TransponderDTO transponder) throws SQLException {
        String query = "INSERT INTO TELEPASS.TB_TRANSPONDER (CODICE_TRANSP_PK, CF_UTENTE_FK, METODO_PAG, ATTIVO, PLUS) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, transponder.getCodiceTranspPk());
            statement.setString(2, transponder.getCfUtenteFk());
            statement.setString(3, transponder.getMetodoPag());
            statement.setInt(4, transponder.getAttivo());
            statement.setInt(5, transponder.getPlus());
            statement.executeUpdate();
        }
    }

    @Override
    public TransponderDTO getTransponderByCodice(String codice) throws SQLException {
        String query = "SELECT * FROM TELEPASS.TB_TRANSPONDER WHERE CODICE_TRANSP_PK = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, codice);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToTransponderDTO(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public List<TransponderDTO> getAllTransponders() throws SQLException {
        List<TransponderDTO> transponders = new ArrayList<>();
        String query = "SELECT * FROM TELEPASS.TB_TRANSPONDER";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                transponders.add(mapResultSetToTransponderDTO(resultSet));
            }
        }
        return transponders;
    }

    @Override
    public void updateTransponder(TransponderDTO transponder) throws SQLException {
        String query = "UPDATE TELEPASS.TB_TRANSPONDER SET CF_UTENTE_FK = ?, METODO_PAG = ?, ATTIVO = ?, PLUS = ? WHERE CODICE_TRANSP_PK = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, transponder.getCfUtenteFk());
            statement.setString(2, transponder.getMetodoPag());
            statement.setInt(3, transponder.getAttivo());
            statement.setInt(4, transponder.getPlus());
            statement.setString(5, transponder.getCodiceTranspPk());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteTransponder(String codice) throws SQLException {
        String query = "DELETE FROM TELEPASS.TB_TRANSPONDER WHERE CODICE_TRANSP_PK = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, codice);
            statement.executeUpdate();
        }
    }

    private TransponderDTO mapResultSetToTransponderDTO(ResultSet resultSet) throws SQLException {
        return new TransponderDTO(
                resultSet.getString("CODICE_TRANSP_PK"),
                resultSet.getString("CF_UTENTE_FK"),
                resultSet.getString("METODO_PAG"),
                resultSet.getInt("ATTIVO"),
                resultSet.getInt("PLUS")
        );
    }
}
