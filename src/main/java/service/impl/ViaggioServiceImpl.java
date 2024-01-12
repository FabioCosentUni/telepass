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

/**
 * Implementazione del servizio ViaggioService per la gestione dei viaggi nel sistema Telepass.
 */
public class ViaggioServiceImpl implements ViaggioService {

    private static final float VELOCITA_MEDIA = 90;
    private final ViaggioDAO viaggioDAO;
    private final CaselloDAO caselloDAO;
    private final VeicoloDAO veicoloDAO;

    /**
     * Costruttore che inizializza l'implementazione con i DAO necessari.
     *
     * @param viaggioDAO DAO per la gestione dei viaggi.
     * @param caselloDAO DAO per la gestione dei caselli.
     * @param veicoloDAO DAO per la gestione dei veicoli.
     */
    public ViaggioServiceImpl(ViaggioDAO viaggioDAO, CaselloDAO caselloDAO, VeicoloDAO veicoloDAO) {
        this.viaggioDAO = viaggioDAO;
        this.caselloDAO = caselloDAO;
        this.veicoloDAO = veicoloDAO;
    }

    /**
     * Registra un nuovo viaggio nel sistema Telepass.
     *
     * @param entry Identificativo del casello di entrata.
     * @param exit  Identificativo del casello di uscita.
     * @param v     Targa del veicolo.
     * @throws TelepassException in caso di errore durante l'inserimento del viaggio.
     */
    @Override
    public void insertViaggio(Long entry, Long exit, String v) throws TelepassException {
        try {
            Casello entryCasello = caselloDAO.findById(entry);
            Casello exitCasello = caselloDAO.findById(exit);

            Veicolo veicolo = veicoloDAO.findById(v);

            GetTariffInputBO getTariffInputBO = new GetTariffInputBO(entryCasello.getAutostrada().toUpperCase(), Objects.requireNonNull(ClasseVeicoloEnum.getClasseEnumByName(veicolo.getTipologiaVe().toUpperCase())).getClassCode());
            GetTariffOutputBO tariffa = (GetTariffOutputBO) AutostradeExecutor.execute(new GetTariffCommandExecutorImpl(new AutostradaDAOImpl()), getTariffInputBO);

            Date timeEntry = new Date();

            //pattern builder
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

    /**
     * Calcola il tempo di uscita dall'autostrada, supponendo che l'auto stia viaggiando a 90 KM/H.
     *
     * @param entryCasello Casello di entrata.
     * @param exitCasello  Casello di uscita.
     * @return Il tempo necessario per uscire dall'autostrada in millisecondi.
     */
    private long getTimeToExitMillis(Casello entryCasello, Casello exitCasello) {
        int distanceKm = Math.abs(exitCasello.getKm() - entryCasello.getKm());

        return (long) ((distanceKm / VELOCITA_MEDIA) * 60 * 60 * 1000);
    }

    /**
     * Calcola il pedaggio per il viaggio.
     *
     * @param tariff           Tariffa dell'autostrada.
     * @param entryCasello     Casello di entrata.
     * @param exitCasello      Casello di uscita.
     * @return Il pedaggio calcolato.
     */
    private float calculatePedaggio(BigDecimal tariff, Casello entryCasello, Casello exitCasello) {
        return Math.abs(exitCasello.getKm() - entryCasello.getKm()) * tariff.floatValue();
    }

    /**
     * Ottiene l'importo totale pagato per ogni veicolo associato a un utente.
     *
     * @param u L'utente di cui ottenere gli importi.
     * @return Una mappa contenente i veicoli come chiavi e gli importi totali pagati come valori.
     * @throws TelepassException se si verifica un errore durante l'ottenimento degli importi.
     */
    @Override
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
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }

    /**
     * Ottiene le statistiche sui caselli, indicando le percentuali di entrate e uscite per ciascun casello.
     *
     * @return Una mappa contenente i caselli come chiavi e le statistiche come valori.
     * @throws TelepassException se si verifica un errore durante l'ottenimento delle statistiche.
     */
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

    /**
     * Ottiene i viaggi associati a una lista di veicoli.
     *
     * @param veicoli La lista di veicoli di cui ottenere i viaggi.
     * @return Una mappa contenente i veicoli come chiavi e le liste di viaggi come valori.
     * @throws TelepassException se si verifica un errore durante l'ottenimento dei viaggi.
     */
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
