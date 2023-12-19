package dao;

import java.sql.SQLException;
import java.util.List;

public interface CaselloDAO {
    List<String> getAllAutostrade() throws SQLException;
}
