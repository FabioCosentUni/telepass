package command;

import exception.CommandExecutorException;
import model.BusinessObject;

public interface CommandExecutor {

    BusinessObject execute(BusinessObject input) throws CommandExecutorException;

}
