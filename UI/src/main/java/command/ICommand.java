package command;

import action.Action;

/**
 * interface for commands that perform an action and return an action
 */
public interface ICommand {

    /**
     * perform an action
     *
     * @return retursn the outcome of the handling
     */
    Action performAction();
}
