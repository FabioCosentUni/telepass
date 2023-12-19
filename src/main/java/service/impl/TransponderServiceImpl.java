package service.impl;

import dao.BaseDao;
import dao.TransponderDAO;
import dao.impl.TransponderDAOImpl;
import exception.DaoException;
import exception.TelepassError;
import exception.TelepassException;
import model.Transponder;
import service.TransponderService;

import java.sql.SQLException;
import java.util.List;

public class TransponderServiceImpl implements TransponderService {

    private final TransponderDAOImpl dao = new TransponderDAOImpl();

    @Override
    public void insert(Transponder transponder) throws TelepassException {
        try {
            Transponder t = dao.findById(transponder.getCodiceTransponder());

            if (t != null) {
                throw new TelepassException(TelepassError.TELEPASS_ALREADY_REGISTERED);
            }

            dao.save(transponder);

        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }

    @Override
    public List<Transponder> getActiveTrasponders() throws TelepassException {

        try {
            return dao.getActiveTransponders();
        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR,e);
        }

    }

    @Override
    public void updateTransponder(Transponder transponder) {
    }
}
