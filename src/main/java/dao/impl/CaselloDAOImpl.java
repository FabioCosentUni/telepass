package dao.impl;

import dao.BaseDao;
import dao.CaselloDAO;
import model.Casello;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class CaselloDAOImpl extends BaseDao<Casello, String> implements CaselloDAO {


    public CaselloDAOImpl() {
        super(Casello.class);
    }

    @Override
    public List<String> getAllAutostrade() throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<String> query = session.createQuery("SELECT DISTINCT autostrada FROM Casello", String.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
