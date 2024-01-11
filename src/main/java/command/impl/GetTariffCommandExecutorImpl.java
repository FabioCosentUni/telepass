package command.impl;

import command.AutostradeCommand;
import dao.AutostradaDAO;
import exception.CommandExecutorException;
import model.BusinessObject;
import model.bo.GetTariffInputBO;
import model.bo.GetTariffOutputBO;
import model.pojo.Autostrada;
import model.pojo.Autostrade;
import model.pojo.Classe;

/**
 * Implementazione dell'interfaccia {@link AutostradeCommand} per l'ottenimento della tariffa
 * basata sull'input fornito.
 */
public class GetTariffCommandExecutorImpl implements AutostradeCommand {

    private final AutostradaDAO autostradaDAO;

    public GetTariffCommandExecutorImpl(AutostradaDAO autostradaDAO) {
        this.autostradaDAO = autostradaDAO;
    }

    /**
     * Esegue il comando per ottenere la tariffa in base all'input fornito.
     *
     * @param input L'oggetto di business di input per ottenere il tariffario. Dev'essere un'istanza di {@link GetTariffInputBO}.
     * @return L'oggetto di business di output contenente il tariffario corrispondente all'input. {@link GetTariffOutputBO}
     * @throws CommandExecutorException se si verifica un errore durante l'esecuzione del comando.
     */
    @Override
    public BusinessObject execute(BusinessObject input) throws CommandExecutorException {
        try {
            if (!(input instanceof GetTariffInputBO)) {
                throw new CommandExecutorException("Input non corretto");
            }

            GetTariffInputBO getTariffInputBO = (GetTariffInputBO) input;
            GetTariffOutputBO output = new GetTariffOutputBO();

            if(getTariffInputBO.getAutostrada() == null || getTariffInputBO.getCategoria() == null) {
                throw new CommandExecutorException("Input non corretto");
            }


            //Ottiene il file XML contenente le autostrade e le rispettive tariffe.
            Autostrade autostrade = autostradaDAO.findAll();

            //Tra tutte le autostrade presenti nel file XML, cerca quella corrispondente all'input fornito.
            //Se l'autostrada è presente, cerca la classe del veicolo corrispondente all'input fornito.
            //Se la classe è presente, restituisce il valore della tariffa corrispondente.
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
