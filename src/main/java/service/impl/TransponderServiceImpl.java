package service.impl;

import dao.TransponderDAO;
import dao.impl.TransponderDAOImpl;
import exception.DaoException;
import exception.TelepassError;
import exception.TelepassException;
import model.Transponder;
import service.TransponderService;

import java.util.ArrayList;
import java.util.List;

public class TransponderServiceImpl implements TransponderService {

    private final TransponderDAO dao;

    public TransponderServiceImpl() {
        this.dao = new TransponderDAOImpl();
    }

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
    public List<Transponder> getActiveTransponders() throws TelepassException {

        try {
            return dao.getActiveTransponders();
        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR,e);
        }

    }

    @Override
    public void updateTransponder(Transponder transponder) {
    }

    @Override
    public void revokeTransponder(String transponderCode) throws TelepassException {
        try {
            Transponder transponder = dao.findById(transponderCode);
            if(transponder == null) {
                throw new TelepassException(TelepassError.TELEPASS_NOT_FOUND);
            }
            transponder.setUtente(null);
            transponder.setVeicoloList(new ArrayList<>());

            dao.merge(transponder);

        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }

    }
}
