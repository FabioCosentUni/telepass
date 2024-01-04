package dao.impl;

import dao.CaselloHibernateDAO;
import model.Casello;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class CaselloHibernateDAOImpl extends BaseHibernateDaoImpl<Casello, Long> implements CaselloHibernateDAO {


    public CaselloHibernateDAOImpl() {
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