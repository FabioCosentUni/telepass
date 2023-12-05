import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViaggioDAOImpl implements ViaggioDAO {

    private static final ViaggioDAOImpl instance = new ViaggioDAOImpl();

    private ViaggioDAOImpl() {
        // Costruttore privato per rendere la classe un singleton
    }

    public static ViaggioDAOImpl getInstance() {
        return instance;
    }
    @Override
    public void insertViaggio(ViaggioDTO viaggio) throws SQLException {
        String query = "INSERT INTO TELEPASS.TB_VIAGGIO (TARGA_VE_PK, CASELLO_ENTRY_FK_PK, TIME_ENTRY_PK, CASELLO_EXIT_FK, TIME_EXIT, PEDAGGIO, PAGATO_FLAG) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, viaggio.getVeicoloDTO().getTarga());
            statement.setLong(2, viaggio.getCaselloEntryDTO().getIdCaselloPk());
            statement.setDate(3, new java.sql.Date(viaggio.getTimeEntry().getTime()));
            statement.setLong(4, viaggio.getCaselloExitDTO().getIdCaselloPk());
            statement.setDate(5, new java.sql.Date(viaggio.getTimeExit().getTime()));
            statement.setFloat(6, viaggio.getPedaggio());
            statement.setInt(7, viaggio.getPagatoFlag());
            statement.executeUpdate();
        }
    }

    @Override
    public ViaggioDTO getViaggioByTarga(String targa) throws SQLException {
        String query = "SELECT * FROM TELEPASS.TB_VIAGGIO WHERE TARGA_VE_PK = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, targa);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToViaggioDTO(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public List<ViaggioDTO> getAllViaggi() throws SQLException {
        List<ViaggioDTO> viaggi = new ArrayList<>();
        String query = "SELECT * FROM TELEPASS.TB_VIAGGIO";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                viaggi.add(mapResultSetToViaggioDTO(resultSet));
            }
        }
        return viaggi;
    }

    @Override
    public void updateViaggio(ViaggioDTO viaggio) throws SQLException {
        String query = "UPDATE TELEPASS.TB_VIAGGIO SET CASELLO_ENTRY_FK_PK = ?, TIME_ENTRY_PK = ?, CASELLO_EXIT_FK = ?, TIME_EXIT = ?, PEDAGGIO = ?, PAGATO_FLAG = ? WHERE TARGA_VE_PK = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, viaggio.getCaselloEntryDTO().getIdCaselloPk());
            statement.setDate(2, new java.sql.Date(viaggio.getTimeEntry().getTime()));
            statement.setLong(3, viaggio.getCaselloExitDTO().getIdCaselloPk());
            statement.setDate(4, new java.sql.Date(viaggio.getTimeExit().getTime()));
            statement.setFloat(5, viaggio.getPedaggio());
            statement.setInt(6, viaggio.getPagatoFlag());
            statement.setString(7, viaggio.getVeicoloDTO().getTarga());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteViaggio(String targa) throws SQLException {
        String query = "DELETE FROM TELEPASS.TB_VIAGGIO WHERE TARGA_VE_PK = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, targa);
            statement.executeUpdate();
        }
    }

    private ViaggioDTO mapResultSetToViaggioDTO(ResultSet resultSet) throws SQLException {
        ViaggioDTO viaggio = new ViaggioDTO();

        // Mapping dei campi del ResultSet al ViaggioDTO
        //Da implemetare getVeicoloById o passare  viaggio.setVeicoloDTO(getVeicoloByTarga(resultSet.getString("TARGA_VE_PK")));
       //Da implemetare getCaselloById o passare  viaggio.setCaselloEntryDTO(getCaselloById(resultSet.getLong("CASELLO_ENTRY_FK_PK")));
        viaggio.setTimeEntry(resultSet.getDate("TIME_ENTRY_PK"));
        viaggio.setCaselloExitDTO(getCaselloById(resultSet.getLong("CASELLO_EXIT_FK")));
        viaggio.setTimeExit(resultSet.getDate("TIME_EXIT"));
        viaggio.setPedaggio(resultSet.getFloat("PEDAGGIO"));
        viaggio.setPagatoFlag(resultSet.getInt("PAGATO_FLAG"));

        return viaggio;
    }

}
