package service.impl;

import dao.impl.ViaggioDAOImpl;
import exception.DaoException;
import exception.TelepassError;
import exception.TelepassException;
import model.Utente;
import model.Veicolo;
import model.Viaggio;
import oracle.ucp.util.Pair;
import service.ViaggioService;

import java.util.ArrayList;
import java.util.List;

public class ViaggioServiceImpl implements ViaggioService {
    private ViaggioDAOImpl viaggioDAO= new ViaggioDAOImpl();
    @Override
    public boolean insertViaggio(Viaggio viaggio) {
        /*
        try {
            return viaggioDAO.save(viaggio);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

         */
        return false;
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
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }

        return importoTotaleList;
    }

}
