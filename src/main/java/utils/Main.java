package utils;

import command.CommandExecutor;
import command.impl.GetTariffCommandExecutorImpl;
import exception.CommandExecutorException;
import model.bo.GetTariffInputBO;
import model.bo.GetTariffOutputBO;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, CommandExecutorException {
        System.out.println("Hello World!");

        CommandExecutor command = new GetTariffCommandExecutorImpl();

        GetTariffOutputBO outputBO = (GetTariffOutputBO) command.execute(new GetTariffInputBO("A24", "B"));

        System.out.println(outputBO.getTariff());
    }
}
