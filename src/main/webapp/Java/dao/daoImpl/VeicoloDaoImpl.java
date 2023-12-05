import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeicoloDAOImpl implements VeicoloDAO {

    private static final VeicoloDAOImpl instance = new VeicoloDAOImpl();

    private VeicoloDAOImpl() {
        // Costruttore privato per rendere la classe un singleton
    }

    public static VeicoloDAOImpl getInstance() {
        return instance;
    }
    @Override
    public void insertVeicolo(VeicoloDTO veicolo) throws SQLException {
        String query = "INSERT INTO TELEPASS.TB_VEICOLO (TARGA_PK, MODELLO, BRAND, TIPOLOGIA_VE, COLORE, TRANSPONDER_FK) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, veicolo.getTargaPk());
            statement.setString(2, veicolo.getModello());
            statement.setString(3, veicolo.getBrand());
            statement.setString(4, veicolo.getTipologiaVe());
            statement.setString(5, veicolo.getColore());
            statement.setString(6, veicolo.getTransponderFk());
            statement.executeUpdate();
        }
    }

    @Override
    public VeicoloDTO getVeicoloByTarga(String targa) throws SQLException {
        String query = "SELECT * FROM TELEPASS.TB_VEICOLO WHERE TARGA_PK = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, targa);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToVeicoloDTO(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public List<VeicoloDTO> getAllVeicoli() throws SQLException {
        List<VeicoloDTO> veicoli = new ArrayList<>();
        String query = "SELECT * FROM TELEPASS.TB_VEICOLO";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                veicoli.add(mapResultSetToVeicoloDTO(resultSet));
            }
        }
        return veicoli;
    }

    @Override
    public void updateVeicolo(VeicoloDTO veicolo) throws SQLException {
        String query = "UPDATE TELEPASS.TB_VEICOLO SET MODELLO = ?, BRAND = ?, TIPOLOGIA_VE = ?, COLORE = ?, TRANSPONDER_FK = ? WHERE TARGA_PK = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, veicolo.getModello());
            statement.setString(2, veicolo.getBrand());
            statement.setString(3, veicolo.getTipologiaVe());
            statement.setString(4, veicolo.getColore());
            statement.setString(5, veicolo.getTransponderFk());
            statement.setString(6, veicolo.getTargaPk());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteVeicolo(String targa) throws SQLException {
        String query = "DELETE FROM TELEPASS.TB_VEICOLO WHERE TARGA_PK = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, targa);
            statement.executeUpdate();
        }
    }

    private VeicoloDTO mapResultSetToVeicoloDTO(ResultSet resultSet) throws SQLException {
        return new VeicoloDTO(
                resultSet.getString("TARGA_PK"),
                resultSet.getString("MODELLO"),
                resultSet.getString("BRAND"),
                resultSet.getString("TIPOLOGIA_VE"),
                resultSet.getString("COLORE"),
                resultSet.getString("TRANSPONDER_FK")
        );
    }
}