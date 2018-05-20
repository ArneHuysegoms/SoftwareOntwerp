package controller;

import exception.UIException;
import exceptions.DomainException;
import uievents.KeyEvent;
import uievents.MouseEvent;

public interface IHighLevelController {

    void handleKeyEvent(KeyEvent keyEvent) throws DomainException, UIException;

    void handleMouseEvent(MouseEvent mouseEvent);
}
