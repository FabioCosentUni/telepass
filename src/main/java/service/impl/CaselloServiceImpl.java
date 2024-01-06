package service.impl;

import dao.CaselloHibernateDAO;
import dao.impl.CaselloHibernateDAOImpl;
import model.Casello;
import service.CaselloService;

import java.util.Collections;
import java.util.List;

public class CaselloServiceImpl implements CaselloService {
    private final CaselloHibernateDAO caselloDAO;

    public CaselloServiceImpl() {
        this.caselloDAO = new CaselloHibernateDAOImpl();
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
}
