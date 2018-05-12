package window.dialogbox;

import exceptions.DomainException;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.Subwindow;
import window.WindowLevelCounter;

import java.awt.geom.Point2D;

public class DialogBox extends Subwindow {

    public DialogBox(Point2D pos){
        super(pos, WindowLevelCounter.getNextLevel());
    }

    @Override
    public void handleMouseEvent(MouseEvent mouseEvent) {

    }

    @Override
    public void handleKeyEvent(KeyEvent keyEvent) throws DomainException {

    }
}
