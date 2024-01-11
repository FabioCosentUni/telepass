package command.impl;

import command.AutostradeCommand;
import dao.AutostradaDAO;
import model.BusinessObject;
import model.bo.GetAutostradeOutputBO;
import model.pojo.Autostrada;
import model.pojo.Autostrade;

public class GetAutostradeCommandExecutorImpl implements AutostradeCommand {

    private AutostradaDAO autostradaDAO;

    public GetAutostradeCommandExecutorImpl(AutostradaDAO autostradaDAO) {
        this.autostradaDAO = autostradaDAO;
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
