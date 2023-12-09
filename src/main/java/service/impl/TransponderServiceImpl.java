package service.impl;

import dao.TransponderDAO;
import dao.impl.TransponderDAOImpl;
import dto.TransponderDTO;
import service.TransponderService;

import java.util.List;

public class TransponderServiceImpl implements TransponderService {
    public TransponderDAO transponderDAO = new TransponderDAOImpl();
    @Override
    public boolean insertTransponder(TransponderDTO transponder) {
        try {
            return transponderDAO.insertTransponder(transponder);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<TransponderDTO> getAllTransponders() {
        try {
            return transponderDAO.getAllTransponders();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateTransponder(TransponderDTO transponder) {
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
    public TransponderDTO getTransponderByCodice(long id) {
        try{
            return transponderDAO.getTransponderByCodice(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
