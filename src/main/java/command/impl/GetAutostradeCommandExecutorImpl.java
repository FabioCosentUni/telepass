package command.impl;

import command.CommandExecutor;
import dao.AutostradaDAO;
import dao.impl.AutostradaDAOImpl;
import exception.CommandExecutorException;
import model.BusinessObject;
import model.bo.GetAutostradeOutputBO;
import model.pojo.Autostrada;
import model.pojo.Autostrade;

public class GetAutostradeCommandExecutorImpl implements CommandExecutor {

    private AutostradaDAO autostradaDAO;

    public GetAutostradeCommandExecutorImpl() throws CommandExecutorException {
        try {
            this.autostradaDAO = new AutostradaDAOImpl();
        } catch (Exception e) {
            throw new CommandExecutorException(e.getMessage(), e);
        }
    }

    @Override
    public BusinessObject execute(BusinessObject input) {
        GetAutostradeOutputBO outputBO = new GetAutostradeOutputBO();

        Autostrade autostrade = autostradaDAO.findAll();

        for (Autostrada autostrada : autostrade.getAutostrada()) {
            outputBO.getAutostrade().add(autostrada.getNome());
        }

        return outputBO;
    }
}
