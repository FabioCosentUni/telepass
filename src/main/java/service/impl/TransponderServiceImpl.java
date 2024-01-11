package service.impl;

import dao.TransponderDAO;
import exception.DaoException;
import exception.TelepassError;
import exception.TelepassException;
import model.Transponder;
import service.TransponderService;

import java.util.List;

public class TransponderServiceImpl implements TransponderService {

    private final TransponderDAO dao;

    public TransponderServiceImpl(TransponderDAO dao) {
        this.dao = dao;
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
    public void makePlus(Transponder transponder) throws TelepassException {
        try {
            transponder.setPlus(1);
            dao.merge(transponder);
        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }

    }

    @Override
    public void revokeTransponder(String transponderCode) throws TelepassException {
        try {
            Transponder transponder = dao.findById(transponderCode);
            if(transponder == null) {
                throw new TelepassException(TelepassError.TELEPASS_NOT_FOUND);
            }
            transponder.setUtente(null);

            transponder.getVeicoloList().forEach(v -> v.setTransponderDTO(null));

            dao.merge(transponder);

        } catch (DaoException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }

    }
}
