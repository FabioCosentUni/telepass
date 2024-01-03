package dao;

import model.Casello;

import java.sql.SQLException;
import java.util.List;

public interface CaselloHibernateDAO extends BaseHibernateDao<Casello, Long> {
    List<String> getAllAutostrade() throws SQLException;
}
