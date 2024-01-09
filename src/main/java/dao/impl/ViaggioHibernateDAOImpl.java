package dao.impl;

import dao.ViaggioHibernateDAO;
import exception.DaoException;
import model.Casello;
import model.Veicolo;
import model.Viaggio;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateConfiguration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ViaggioHibernateDAOImpl extends BaseHibernateDAOImpl<Viaggio, Long> implements ViaggioHibernateDAO {

    public ViaggioHibernateDAOImpl() {
        super(Viaggio.class);
    }


    @Override
    public List<Float> getPedaggiPagatiByVeicolo(String targa) throws DaoException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Float> query = session.createQuery("SELECT v.pedaggio FROM Viaggio v WHERE v.veicolo.targaPk = :targa", Float.class);
            query.setParameter("targa", targa);

            return query.list();
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

    @Override
    public Map<Casello, Double> getPercentualiEntrateCaselli() throws DaoException {

        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            String hqlEntryCaselli = "SELECT v.caselloEntryDTO.idCaselloPk, COUNT(*) FROM Viaggio v GROUP BY v.caselloEntryDTO";

            Query<Object[]> queryEntryCaselli = session.createQuery(hqlEntryCaselli, Object[].class);
            List<Object[]> resultEntryCaselli = queryEntryCaselli.list();

            if(resultEntryCaselli.isEmpty()) {
                return null;
            }

            long totaleEntRate = resultEntryCaselli.stream().mapToLong(result -> (Long) result[1]).sum();


            return resultEntryCaselli.stream()
                    .collect(Collectors.toMap(
                            result -> session.get(Casello.class, (Long) result[0]),
                            result -> ((Long) result[1]).doubleValue() / totaleEntRate * 100
                    ));

        } catch (Exception e) {
            throw new DaoException("Errore durante il recupero delle entrate dei caselli", e);
        }

    }

    @Override
    public Map<Casello, Double> getPercentualiUsciteCaselli() throws DaoException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            String hqlExitCaselli = "SELECT v.caselloExitDTO.idCaselloPk, COUNT(*) FROM Viaggio v WHERE v.timeExit IS NOT NULL GROUP BY v.caselloExitDTO";

            Query<Object[]> queryExitCaselli = session.createQuery(hqlExitCaselli, Object[].class);
            List<Object[]> resultExitCaselli = queryExitCaselli.getResultList();

            if(resultExitCaselli.isEmpty()) {
                return null;
            }

            long totaleUscite = resultExitCaselli.stream().mapToLong(result -> (Long) result[1]).sum();

            return resultExitCaselli.stream()
                    .collect(Collectors.toMap(
                            result -> session.get(Casello.class, (Long) result[0]),
                            result -> ((Long) result[1]).doubleValue() / totaleUscite * 100
                    ));


        } catch (Exception e) {
            throw new DaoException("Errore durante il recupero delle uscite dei caselli", e);
        }
    }

    @Override
    public List<Viaggio> getViaggiPerVeicolo(Veicolo v) throws DaoException {

        try(Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Viaggio> query = session.createQuery(
                    "select v from Viaggio v " +
                            "join fetch v.veicolo veicolo " +
                            "join fetch veicolo.transponder transp " +
                            "join fetch transp.utente " +
                            "where veicolo = :veicolo",
                    Viaggio.class
            );
            query.setParameter("veicolo", v);
            List<Viaggio> viaggi = query.getResultList();
            return viaggi;
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
    /*public List<Viaggio> getViaggiPerVeicolo(Veicolo v) throws DaoException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            // Apri la sessione prima di eseguire la query
            session.beginTransaction();
            Query<Viaggio> query = session.createQuery(
                    "select v from Viaggio v " +
                            "join fetch v.veicolo veicolo " +
                            "join fetch veicolo.transponder " +
                            "where veicolo = :veicolo",
                    Viaggio.class
            );
            query.setParameter("veicolo", v);
            List<Viaggio> viaggi = query.getResultList();
            // Chiudi la transazione prima di restituire i risultati
            session.getTransaction().commit();
            return viaggi;
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e);
        }
    }*/

}
