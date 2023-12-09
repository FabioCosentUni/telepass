package service.impl;

import dao.CaselloDAO;
import dao.impl.CaselloDAOImpl;
import dto.CaselloDTO;
import dto.UtenteDTO;
import service.CaselloService;

import java.util.Collections;
import java.util.List;

public class CaselloServiceImpl implements CaselloService {
    private CaselloDAO caselloDAO = new CaselloDAOImpl();

    @Override
    public boolean insertCasello(UtenteDTO utente, CaselloDTO casello) {
        try {
            if (utente.getAmministratore()==1) {
                caselloDAO.insertCasello(casello);
                return true;
            } else {
                System.out.println("Non sei autorizzato ad eseguire questa operazione");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CaselloDTO> getAllCaselli() {
        try {
            return caselloDAO.getAllCaselli();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    @Override
    public boolean updateCasello(UtenteDTO utente, CaselloDTO casello) {
        try{
            if (utente.getAmministratore()==1) {
                caselloDAO.updateCasello(casello);
                return true;
            } else {
                System.out.println("Non sei autorizzato ad eseguire questa operazione");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCaselloById(UtenteDTO utente, long caselloId) {
        try{
            if (utente.getAmministratore()==1) {
                caselloDAO.deleteCaselloById(caselloId);
                return true;
            } else {
                System.out.println("Non sei autorizzato ad eseguire questa operazione");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CaselloDTO getCaselloById(long caselloId) {
        try {
            return caselloDAO.getCaselloById(caselloId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void statisticheCasello() {

    }
}
