package dao.impl;

import dao.BaseDao;
import dao.UtenteDAO;
import model.Utente;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.sql.SQLException;
import java.util.List;

public class UtenteDAOImpl extends BaseDao<Utente, String> implements UtenteDAO {

    public UtenteDAOImpl() {
        super(Utente.class);
    }

    @Override
    public Utente getUtenteByEmail(String email) throws SQLException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Utente> query = session.createQuery("FROM Utente u WHERE u.email = :email", Utente.class);
            query.setParameter("email", email);
            if(query.list().size() == 0) {
                return null;
            }
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Errore durante il recupero dell'utente.", e);
        }
    }

}
