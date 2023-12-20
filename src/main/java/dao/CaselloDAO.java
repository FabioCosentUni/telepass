package dao;

import model.Casello;

import java.sql.SQLException;
import java.util.List;

public interface CaselloDAO extends BaseDao<Casello, Long> {
    List<String> getAllAutostrade() throws SQLException;
}
