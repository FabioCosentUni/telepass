package service.impl;

import dao.impl.ViaggioDAOImpl;
import model.Viaggio;
import service.ViaggioService;

import java.util.List;

public class ViaggioServiceImpl implements ViaggioService {
    private ViaggioDAOImpl viaggioDAO= new ViaggioDAOImpl();
    @Override
    public boolean insertViaggio(Viaggio viaggio) {
        try {
            return viaggioDAO.save(viaggio);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
        try {
            return viaggioDAO.update(viaggio);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteViaggioById(long viaggioId) {
        try {
            return viaggioDAO.delete(viaggioDAO.findById(viaggioId));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
}
