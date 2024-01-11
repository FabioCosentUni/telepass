package command;

import exception.CommandExecutorException;
import model.BusinessObject;

public class AutostradeExecutor {

    public static BusinessObject execute(AutostradeCommand command, BusinessObject input) throws CommandExecutorException {
        return command.execute(input);
    }
}
