package service.impl;

import command.AutostradeExecutor;
import command.impl.GetAutostradeCommandExecutorImpl;
import command.impl.GetTariffCommandExecutorImpl;
import dao.CaselloDAO;
import dao.VeicoloDAO;
import dao.ViaggioDAO;
import dao.impl.AutostradaDAOImpl;
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
import service.ViaggioService;
import utils.ClasseVeicoloEnum;
import utils.ViaggioBuilder;
import utils.ViaggioBuilderImpl;

import java.math.BigDecimal;
import java.util.*;

public class ViaggioServiceImpl implements ViaggioService {
    private static final float VELOCITA_MEDIA = 90;
    private final ViaggioDAO viaggioDAO;
    private final CaselloDAO caselloDAO;
    private final VeicoloDAO veicoloDAO;

    public ViaggioServiceImpl(ViaggioDAO viaggioDAO, CaselloDAO caselloDAO, VeicoloDAO veicoloDAO) {
        this.viaggioDAO = viaggioDAO;
        this.caselloDAO = caselloDAO;
        this.veicoloDAO = veicoloDAO;
    }

    @Override
    public void insertViaggio(Long entry, Long exit, String v) throws TelepassException {
        try {
            Casello entryCasello = caselloDAO.findById(entry);
            Casello exitCasello = caselloDAO.findById(exit);

            Veicolo veicolo = veicoloDAO.findById(v);

            GetTariffInputBO getTariffInputBO = new GetTariffInputBO(entryCasello.getAutostrada().toUpperCase(), Objects.requireNonNull(ClasseVeicoloEnum.getClasseEnumByName(veicolo.getTipologiaVe().toUpperCase())).getClassCode());
            GetTariffOutputBO tariffa = (GetTariffOutputBO) AutostradeExecutor.execute(new GetTariffCommandExecutorImpl(new AutostradaDAOImpl()),getTariffInputBO);

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

        } catch (Exception e) {
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

    public Map<Veicolo, Float> getImportoTotalePagatoPerVeicolo(Utente u) throws TelepassException {

        Map<Veicolo, Float> importoTotaleMap = new HashMap<>();

        try {

            for (Veicolo v : u.getTransponder().getVeicoloList()) {

                if (viaggioDAO.checkVeicoloViaggi(v.getTargaPk())) {
                    List<Float> importiViaggi = viaggioDAO.getPedaggiPagatiByVeicolo(v.getTargaPk());
                    Float importoTotale = importiViaggi.stream().reduce(0F, Float::sum);

                    importoTotaleMap.put(v, importoTotale);
                } else {
                    importoTotaleMap.put(v, 0F);
                }
            }

            return importoTotaleMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }

    }

    @Override
    public Map<Casello, StatisticsBO> getStatisticheCaselli() throws TelepassException {
        try {
            Map<Casello, StatisticsBO> statisticheCaselli = new HashMap<>();

            Map<Casello, Double> statisticheEntrata = viaggioDAO.getPercentualiEntrateCaselli();
            Map<Casello, Double> statisticheUscita = viaggioDAO.getPercentualiUsciteCaselli();


            if (statisticheEntrata == null || statisticheUscita == null) {
                return new HashMap<>();
            }

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

    @Override
    public Map<Veicolo, List<Viaggio>> getViaggiPerVeicoli(List<Veicolo> veicoli) throws TelepassException {
        Map<Veicolo, List<Viaggio>> viaggiMap = new HashMap<>();
        List<Viaggio> viaggi;
        try {
            for (Veicolo v : veicoli) {
                viaggi = viaggioDAO.getViaggiPerVeicolo(v);
                if (viaggi != null && !viaggi.isEmpty())
                    viaggiMap.put(v, viaggi);
            }

            return viaggiMap;
        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }

    }

}
