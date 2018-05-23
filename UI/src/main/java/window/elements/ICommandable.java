package window.elements;

import action.Action;

/**
 * interface for performing an action
 */
public interface ICommandable {

    /**
     * perform an action
     *
     * @return an action detailing the change
     */
    Action performAction();
}
