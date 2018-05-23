package window;

import action.Action;
import exception.UIException;
import exceptions.DomainException;
import uievents.KeyEvent;
import uievents.MouseEvent;

/**
 * Controller that handles events on a subwindow level
 */
public interface ILowLevelController {

    /**
     * handle the given mouseEvent
     *
     * @param mouseEvent the mouseEvent to handle
     * @return an action detailing what happened in the handling of the event
     */
    Action handleMouseEvent(MouseEvent mouseEvent);

    /**
     * handles the given keyEvent
     *
     * @param keyEvent the keyEvent to handle
     * @return an action detailing what happened in the handling of the event
     * @throws DomainException if illegal modifications in domain
     * @throws UIException     if illegal modifications in UI
     */
    Action handleKeyEvent(KeyEvent keyEvent) throws DomainException, UIException;
}
