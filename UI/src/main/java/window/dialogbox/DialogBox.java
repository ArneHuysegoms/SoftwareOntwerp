package window.dialogbox;

import action.Action;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.Subwindow;
import window.WindowLevelCounter;
import window.elements.DialogboxElement;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * abstract superclass for dialogboxes
 */
public abstract class DialogBox extends Subwindow {

    protected boolean designerMode;

    protected DialogboxElement selected;
    protected int selectedindex;
    protected List<DialogboxElement> elementList;
    protected void setDesignerMode(boolean bool){
        this.designerMode = bool;
    }

    protected boolean invalidDescriptionMode;

    public void setInvalidDescriptionMode(boolean invalidDescriptionMode) {
        this.invalidDescriptionMode = invalidDescriptionMode;
    }
    public boolean getInvalidDescriptionMode(){
        return invalidDescriptionMode;
    }

    public boolean getDesignerMode(){
        return designerMode;
    }

    public int getSelectedindex() {
        return selectedindex;
    }

    public DialogboxElement getSelected() {
        return selected;
    }

    public List<DialogboxElement> getElementList() {
        return elementList;
    }

    /**
     * create a new dialogbox at the given position
     *
     * @param position the new position
     */
    public DialogBox(Point2D position) {
        super(position, WindowLevelCounter.getNextLevel());
        this.invalidDescriptionMode = false;
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
