package service.impl;

import command.CommandExecutor;
import command.impl.GetAutostradeCommandExecutorImpl;
import dao.CaselloHibernateDAO;
import dao.impl.CaselloHibernateDAOImpl;
import exception.CommandExecutorException;
import exception.TelepassError;
import exception.TelepassException;
import model.Casello;
import model.bo.GetAutostradeOutputBO;
import service.CaselloService;

import java.util.Collections;
import java.util.List;

public class CaselloServiceImpl implements CaselloService {
    private final CaselloHibernateDAO caselloDAO;
    private final CommandExecutor commandExecutor;

    public CaselloServiceImpl() throws TelepassException {

        this.caselloDAO = new CaselloHibernateDAOImpl();
        try {
            this.commandExecutor = new GetAutostradeCommandExecutorImpl();
        } catch(CommandExecutorException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
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
    public GetAutostradeOutputBO getAllAutostrade() throws TelepassException {
        try {
            return (GetAutostradeOutputBO) commandExecutor.execute(null);
        } catch (CommandExecutorException e) {
            throw new TelepassException(TelepassError.GENERIC_ERROR, e);
        }
    }
}
