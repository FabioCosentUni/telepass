package service.impl;

import command.CommandExecutor;
import command.impl.GetTariffCommandExecutorImpl;
import dao.impl.CaselloHibernateDAOImpl;
import dao.impl.VeicoloHibernateDAOImpl;
import dao.impl.ViaggioHibernateDAOImpl;
import exception.CommandExecutorException;
import exception.DaoException;
import exception.TelepassError;
import exception.TelepassException;
import model.Casello;
import model.Utente;
import model.Veicolo;
import model.Viaggio;
import model.bo.GetTariffInputBO;
import model.bo.GetTariffOutputBO;
import model.bo.StatisticsBO;
import oracle.ucp.util.Pair;
import service.ViaggioService;
import utils.ClasseEnum;
import utils.ViaggioBuilder;
import utils.ViaggioBuilderImpl;

import java.math.BigDecimal;
import java.util.*;

public class ViaggioServiceImpl implements ViaggioService {
    private static final float VELOCITA_MEDIA = 90;
    private final ViaggioHibernateDAOImpl viaggioDAO;
    private final CaselloHibernateDAOImpl caselloDAO;
    private final VeicoloHibernateDAOImpl veicoloDAO;
    private final CommandExecutor executor;

    public ViaggioServiceImpl() {
        this.viaggioDAO = new ViaggioHibernateDAOImpl();
        this.caselloDAO = new CaselloHibernateDAOImpl();
        this.veicoloDAO = new VeicoloHibernateDAOImpl();
        this.executor = new GetTariffCommandExecutorImpl();
    }

    @Override
    public void insertViaggio(Long entry, Long exit, String v) throws TelepassException {
        try {
            Casello entryCasello = caselloDAO.findById(entry);
            Casello exitCasello = caselloDAO.findById(exit);

            if (v == null || v.isEmpty()) {
                //TODO
                //Caso in cui si prova ad eseguire una simulazione senza aver selezionato un veicolo
                //Configurare messaggio di errore e far reindirizzare alla pagina di simulazione
            }

            Veicolo veicolo = veicoloDAO.findById(v);

            GetTariffInputBO getTariffInputBO = new GetTariffInputBO(entryCasello.getAutostrada().toUpperCase(), Objects.requireNonNull(ClasseEnum.getClasseEnumByName(veicolo.getTipologiaVe().toUpperCase())).getClassCode());
            GetTariffOutputBO tariffa = (GetTariffOutputBO) executor.execute(getTariffInputBO);

            Date timeEntry = new Date();
            ViaggioBuilder viaggioBuilder = new ViaggioBuilderImpl();

            Viaggio viaggio = viaggioBuilder.setCaselloEntrata(entryCasello)
                    .setCaselloUscita(exitCasello)
                    .setVeicolo(veicolo)
                    .setTimeEntry(timeEntry)
                    .setTimeExit(new Date(timeEntry.getTime() + getTimeToExitMillis(entryCasello, exitCasello)))
                    .setPedaggio(calculatePedaggio(tariffa.getTariff(), entryCasello, exitCasello))
                    .setPagatoFlag(1)
                    .build();

            viaggioDAO.save(viaggio);

        } catch (DaoException | CommandExecutorException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }

    private long getTimeToExitMillis(Casello entryCasello, Casello exitCasello) {
        int distanceKm = Math.abs(exitCasello.getKm() - entryCasello.getKm());

        return (long) ((distanceKm / VELOCITA_MEDIA) * 60 * 60 * 1000);
    }

    private float calculatePedaggio(BigDecimal tariff, Casello entryCasello, Casello exitCasello) {
        return Math.abs(exitCasello.getKm() - entryCasello.getKm()) * tariff.floatValue();
    }

    @Override
    public List<Viaggio> getAllViaggi() {
        try {
            return viaggioDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateViaggio(Viaggio viaggio) {
        /*
        try {
            return viaggioDAO.update(viaggio);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

         */
        return false;
    }

    @Override
    public boolean deleteViaggioById(long viaggioId) {
        /*
        try {
            return viaggioDAO.delete(viaggioDAO.findById(viaggioId));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

         */
        return false;
    }

    @Override
    public Viaggio getViaggioById(long viaggioId) {
        try {
            return viaggioDAO.findById(viaggioId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Pair<Veicolo, Integer>> getImportoTotalePagatoPerVeicolo(Utente u) throws TelepassException {
        List<Pair<Veicolo, Integer>> importoTotaleList = new ArrayList<>();
        try {
            for (Veicolo v : u.getTransponder().getVeicoloList()) {

                if (viaggioDAO.checkVeicoloViaggi(v.getTargaPk())) {
                    List<Integer> importiViaggi = viaggioDAO.getViaggiByVeicolo(v.getTargaPk());
                    Integer importoTotale = importiViaggi.stream().reduce(0, Integer::sum);

                    importoTotaleList.add(new Pair<>(v, importoTotale));
                } else {
                    importoTotaleList.add(new Pair<>(v, 0));
                }
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }

        return importoTotaleList;
    }

    @Override
    public Map<Casello, StatisticsBO> getStatisticheCaselli() throws TelepassException {
        try {
            Map<Casello, StatisticsBO> statisticheCaselli = new HashMap<>();

            Map<Casello, Double> statisticheEntrata = viaggioDAO.getPercentualiEntrateCaselli();
            Map<Casello, Double> statisticheUscita = viaggioDAO.getPercentualiUsciteCaselli();

            for (Casello c : statisticheEntrata.keySet()) {
                statisticheCaselli.put(c, new StatisticsBO(statisticheEntrata.get(c), 0D));
            }

            for (Casello c : statisticheUscita.keySet()) {
                if (statisticheCaselli.get(c) != null) {
                    statisticheCaselli.get(c).setExitPercentage(statisticheUscita.get(c));
                } else {
                    statisticheCaselli.put(c, new StatisticsBO(0D, statisticheUscita.get(c)));
                }
            }

            return statisticheCaselli;

        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }

}
