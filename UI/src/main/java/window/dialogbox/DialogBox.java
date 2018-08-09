package window.dialogbox;

import action.Action;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.Subwindow;
import window.WindowLevelCounter;
import window.elements.DialogboxElement;

import java.awt.geom.Point2D;

/**
 * abstract superclass for dialogboxes
 */
public abstract class DialogBox extends Subwindow {

    protected boolean designerMode;

    protected DialogboxElement selected;
    protected int selectedindex;
    protected void setDesignerMode(boolean bool){
        this.designerMode = bool;
    }

    public int getSelectedindex() {
        return selectedindex;
    }

    public DialogboxElement getSelected() {
        return selected;
    }

    /**
     * create a new dialogbox at the given position
     *
     * @param position the new position
     */
    public DialogBox(Point2D position) {
        super(position, WindowLevelCounter.getNextLevel());
    }

    /**
     * handles a mouseEvent
     *
     * @param mouseEvent the mouseEvent to handle
     * @return an action detailing the change by the mouseEvent
     */
    @Override
    public abstract Action handleMouseEvent(MouseEvent mouseEvent);

    /**
     * handles a keyEvent
     *
     * @param keyEvent the keyEvent to handle
     * @return an action detailing the change by the keyEvent
     */
    @Override
    public abstract Action handleKeyEvent(KeyEvent keyEvent);


}
