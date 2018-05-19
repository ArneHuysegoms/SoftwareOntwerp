package window.dialogbox;

import action.Action;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.IActionHandler;
import window.Subwindow;
import window.WindowLevelCounter;

import java.awt.geom.Point2D;

public abstract class DialogBox extends Subwindow implements IActionHandler {

    public DialogBox(Point2D pos){
        super(pos, WindowLevelCounter.getNextLevel());
    }

    @Override
    public abstract Action handleMouseEvent(MouseEvent mouseEvent);

    @Override
    public abstract Action handleKeyEvent(KeyEvent keyEvent);
}
