package service.impl;

import dao.ViaggioDAO;
import dao.impl.ViaggioDAOImpl;
import dto.ViaggioDTO;
import service.ViaggioService;

import java.util.List;

public class ViaggioServiceImpl implements ViaggioService {
    private ViaggioDAO viaggioDAO= new ViaggioDAOImpl();
    @Override
    public boolean insertViaggio(ViaggioDTO viaggio) {
        try {
            return viaggioDAO.insertViaggio(viaggio);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ViaggioDTO> getAllViaggi() {
        try {
            return viaggioDAO.getAllViaggi();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateViaggio(ViaggioDTO viaggio) {
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
    public ViaggioDTO getViaggioById(long viaggioId) {
        try {
            return viaggioDAO.getViaggioById(viaggioId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
