package service.impl;

import dao.TransponderDAO;
import dao.impl.TransponderDAOImpl;
import exception.TelepassError;
import exception.TelepassException;
import model.Transponder;
import service.TransponderService;

import java.sql.SQLException;
import java.util.List;

public class TransponderServiceImpl implements TransponderService {
    public TransponderDAO transponderDAO = new TransponderDAOImpl();

    @Override
    public void insert(Transponder transponder) throws TelepassException {
        Transponder t = transponderDAO.getTransponderByCodice(transponder.getCodiceTransponder());
        if(t != null) {
            throw new TelepassException(TelepassError.TELEPASS_ALREADY_REGISTERED);
        }

        try {
            transponderDAO.insert(transponder);
        } catch (SQLException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }

    @Override
    public List<Transponder> getAllTransponders() {
        try {
            return transponderDAO.getAllTransponders();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateTransponder(Transponder transponder) {
        try {
            return transponderDAO.updateTransponder(transponder);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteTransponderById(long id) {
        try {
            return transponderDAO.deleteTransponderById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Transponder getTransponderByCodice(String codice) {
        return transponderDAO.getTransponderByCodice(codice);
    }
}
