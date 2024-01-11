package service.impl;

import command.AutostradeExecutor;
import command.impl.GetAutostradeCommandExecutorImpl;
import dao.CaselloDAO;
import dao.impl.AutostradaDAOImpl;
import exception.TelepassError;
import exception.TelepassException;
import model.Casello;
import model.bo.GetAutostradeOutputBO;
import service.CaselloService;

import java.util.Collections;
import java.util.List;

public class CaselloServiceImpl implements CaselloService {
    private final CaselloDAO caselloDAO;

    public CaselloServiceImpl(CaselloDAO caselloDAO){
        this.caselloDAO = caselloDAO;
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
    public GetAutostradeOutputBO getAllAutostrade() throws TelepassException {
        try {
            return (GetAutostradeOutputBO) AutostradeExecutor.execute(new GetAutostradeCommandExecutorImpl(new AutostradaDAOImpl()), null);
        } catch (Exception e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }
}
