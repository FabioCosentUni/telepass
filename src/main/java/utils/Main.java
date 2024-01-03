package utils;

import command.CommandExecutor;
import command.impl.GetTariffCommandExecutorImpl;
import exception.CommandExecutorException;
import exception.TelepassException;
import model.Casello;
import model.bo.GetTariffInputBO;
import model.bo.GetTariffOutputBO;
import model.bo.StatisticsBO;
import service.ViaggioService;
import service.impl.ViaggioServiceImpl;

import java.sql.SQLException;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws SQLException, CommandExecutorException, TelepassException {
        System.out.println("Hello World!");

        /*
        CommandExecutor command = new GetTariffCommandExecutorImpl();

        GetTariffOutputBO outputBO = (GetTariffOutputBO) command.execute(new GetTariffInputBO("A24", "B"));

        System.out.println(outputBO.getTariff());

         */

        ViaggioService viaggioService = new ViaggioServiceImpl();

        Map<Casello, StatisticsBO> statistics = viaggioService.getStatisticheCaselli();

        System.out.println(statistics);

    }
}
