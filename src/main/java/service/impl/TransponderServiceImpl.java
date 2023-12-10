package service.impl;

import dao.TransponderDAO;
import dao.impl.TransponderDAOImpl;
import model.Transponder;
import service.TransponderService;

import java.util.List;

public class TransponderServiceImpl implements TransponderService {
    public TransponderDAO transponderDAO = new TransponderDAOImpl();
    @Override
    public boolean insertTransponder(Transponder transponder) {
        try {
            return transponderDAO.insertTransponder(transponder);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
        try{
            return transponderDAO.updateTransponder(transponder);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteTransponderById(long id) {
        try{
            return transponderDAO.deleteTransponderById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Transponder getTransponderByCodice(long id) {
        try{
            return transponderDAO.getTransponderByCodice(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
