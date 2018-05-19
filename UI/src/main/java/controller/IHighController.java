package controller;

import exception.UIException;
import exceptions.DomainException;
import uievents.KeyEvent;
import uievents.MouseEvent;

public interface IHighController {

    void handleKeyEvent(KeyEvent keyEvent) throws DomainException, UIException;

    void handleMouseEvent(MouseEvent mouseEvent);
}
