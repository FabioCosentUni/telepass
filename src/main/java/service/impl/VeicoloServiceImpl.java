package service.impl;

import dao.VeicoloDAO;
import exception.DaoException;
import exception.TelepassError;
import exception.TelepassException;
import model.Utente;
import model.Veicolo;
import service.VeicoloService;

import java.util.List;

public class VeicoloServiceImpl implements VeicoloService {
    private final VeicoloDAO veicoloDAO;

    public VeicoloServiceImpl(VeicoloDAO veicoloDAO) {
        this.veicoloDAO = veicoloDAO;
    }

    @Override
    public void validateVeicolo(Veicolo veicolo) throws TelepassException {
        try {

            if (veicoloDAO.findById(veicolo.getTargaPk()) != null)
                throw new TelepassException(TelepassError.VEHICLE_ALREADY_REGISTERED);

        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }

    @Override
    public List<Veicolo> getVeicoliUtente(Utente u) throws TelepassException {
        try {
            return veicoloDAO.getVeicoliUtente(u);
        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }
}
