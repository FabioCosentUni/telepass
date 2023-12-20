package dao.impl;

import dao.ViaggioDAO;
import exception.DaoException;
import model.Viaggio;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ViaggioDAOImpl extends BaseDaoImpl<Viaggio, Long> implements ViaggioDAO {

    public ViaggioDAOImpl() {
        super(Viaggio.class);
    }


    @Override
    public List<Integer> getViaggiByVeicolo(String targa) throws DaoException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<BigDecimal> query = session.createQuery("SELECT v.pedaggio FROM Viaggio v WHERE v.veicolo.targaPk = :targa", BigDecimal.class);
            query.setParameter("targa", targa);

            List<BigDecimal> pedaggioList = query.list();

            List<Integer> pedaggioIntList = new ArrayList<>();
            for (BigDecimal pedaggio : pedaggioList) {
                pedaggioIntList.add(pedaggio.intValue());
            }
            return pedaggioIntList;
        } catch (Exception e) {
            throw new DaoException("Errore durante il recupero dei pedaggi del veicolo.", e);
        }
    }

    public boolean checkVeicoloViaggi(String targa) throws DaoException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Viaggio v WHERE v.veicolo.targaPk = :targa", Long.class);
            query.setParameter("targa", targa);

            Long count = query.uniqueResult(); // Restituisce il conteggio dei record

            return count > 0; // Restituisce true se ci sono record, altrimenti false
        } catch (Exception e) {
            throw new DaoException("Errore durante la verifica dei viaggi del veicolo", e);
        }
    }
}
