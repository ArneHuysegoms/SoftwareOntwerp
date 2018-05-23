package window;

import action.Action;

/**
 * defines an interface for handling actions
 */
public interface IActionHandler {

    /**
     * handles the given action
     *
     * @param action the action to handle
     */
    void handleAction(Action action);
}
