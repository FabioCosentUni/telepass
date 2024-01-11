package service.impl;

import command.CommandExecutor;
import dao.CaselloDAO;
import exception.CommandExecutorException;
import exception.TelepassError;
import exception.TelepassException;
import model.Casello;
import model.bo.GetAutostradeOutputBO;
import service.CaselloService;

import java.util.Collections;
import java.util.List;

public class CaselloServiceImpl implements CaselloService {
    private final CaselloDAO caselloDAO;
    private final CommandExecutor commandExecutor;

    public CaselloServiceImpl(CaselloDAO caselloDAO, CommandExecutor executor){
        this.caselloDAO = caselloDAO;
        this.commandExecutor = executor;
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
