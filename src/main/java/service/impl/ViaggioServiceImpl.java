package service.impl;

import dao.ViaggioDAO;
import dao.impl.ViaggioDAOImpl;
import model.Viaggio;
import service.ViaggioService;

import java.util.List;

public class ViaggioServiceImpl implements ViaggioService {
    private ViaggioDAO viaggioDAO= new ViaggioDAOImpl();
    @Override
    public boolean insertViaggio(Viaggio viaggio) {
        try {
            return viaggioDAO.insertViaggio(viaggio);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Viaggio> getAllViaggi() {
        try {
            return viaggioDAO.getAllViaggi();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateViaggio(Viaggio viaggio) {
        try {
            return viaggioDAO.updateViaggio(viaggio);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteViaggioById(long viaggioId) {
        try {
            return viaggioDAO.deleteViaggioById(viaggioId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Viaggio getViaggioById(long viaggioId) {
        try {
            return viaggioDAO.getViaggioById(viaggioId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
