package command.impl;

import command.CommandExecutor;
import exception.CommandExecutorException;
import model.BusinessObject;
import model.bo.GetTariffInputBO;
import model.bo.GetTariffOutputBO;
import model.pojo.Autostrada;
import model.pojo.Autostrade;
import model.pojo.Classe;
import utils.JAXBUtils;

import java.io.File;

public class GetTariffCommandExecutorImpl implements CommandExecutor {

    @Override
    public BusinessObject execute(BusinessObject input) throws CommandExecutorException {

        try {

            if (!(input instanceof GetTariffInputBO)) {
                throw new CommandExecutorException("Wrong input");
            }

            GetTariffInputBO getTariffInputBO = (GetTariffInputBO) input;

            GetTariffOutputBO output = new GetTariffOutputBO();

            Autostrade autostrade = JAXBUtils.unmarshall(new File("src/main/resources/xml/Autostrade.xml"), Autostrade.class);

            for (Autostrada autostrada : autostrade.getAutostrada()) {
                if (autostrada.getNome().equals(getTariffInputBO.getAutostrada())) {
                    for (Classe classe : autostrada.getTariffario().getClasse()) {
                        if (classe.getNome().equals(getTariffInputBO.getCategoria())) {
                            output.setTariff(classe.getValue());
                            return output;
                        }
                    }
                }
            }

            return null;

        } catch (Exception e) {
            throw new CommandExecutorException(e);
        }

    }
}
