package window;

import action.Action;
import exception.UIException;
import exceptions.DomainException;
import uievents.KeyEvent;
import uievents.MouseEvent;

public interface ILowLevelController {

    Action handleKeyEvent(KeyEvent keyEvent) throws DomainException, UIException;

    Action handleMouseEvent(MouseEvent mouseEvent);
}
