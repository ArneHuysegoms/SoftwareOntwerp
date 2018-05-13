package window.dialogbox;

import uievents.KeyEvent;
import uievents.MouseEvent;
import window.Subwindow;
import window.WindowLevelCounter;

import java.awt.geom.Point2D;

public abstract class DialogBox extends Subwindow {

    public DialogBox(Point2D pos){
        super(pos, WindowLevelCounter.getNextLevel());
    }

    @Override
    public abstract void handleMouseEvent(MouseEvent mouseEvent);

    @Override
    public abstract void handleKeyEvent(KeyEvent keyEvent);
}
