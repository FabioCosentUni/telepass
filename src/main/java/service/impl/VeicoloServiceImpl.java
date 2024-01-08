package service.impl;

import dao.VeicoloHibernateDAO;
import dao.impl.VeicoloHibernateDAOImpl;
import exception.DaoException;
import exception.TelepassError;
import exception.TelepassException;
import model.Utente;
import model.Veicolo;
import service.VeicoloService;

import java.sql.SQLException;
import java.util.List;

public class VeicoloServiceImpl implements VeicoloService {
    private final VeicoloHibernateDAO veicoloDAO;

    public VeicoloServiceImpl() {
        veicoloDAO = new VeicoloHibernateDAOImpl();
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
