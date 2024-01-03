package service.impl;

import dao.CaselloHibernateDAO;
import dao.impl.CaselloHibernateDAOImpl;
import model.Casello;
import model.Utente;
import service.CaselloService;

import java.util.Collections;
import java.util.List;

public class CaselloServiceImpl implements CaselloService {
    private final CaselloHibernateDAO caselloDAO = new CaselloHibernateDAOImpl();

    @Override
    public boolean insertCasello(Utente utente, Casello casello) {
        try {
            if (utente.getAmministratore()==1) {
                caselloDAO.save(casello);
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
    public List<Casello> getAllCaselli() {
        try {
            return caselloDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    @Override
    public List<String> getAllAutostrade() {
        try {
            return caselloDAO.getAllAutostrade();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public boolean updateCasello(Utente utente, Casello casello) {
        try{
            if (utente.getAmministratore()==1) {
                caselloDAO.update(casello);
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
    public boolean deleteCaselloById(Utente utente, long caselloId) {
        try{
            if (utente.getAmministratore()==1) {
                caselloDAO.delete(caselloDAO.findById(caselloId));
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
    public Casello getCaselloById(long caselloId) {
        try {
            return caselloDAO.findById(caselloId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
