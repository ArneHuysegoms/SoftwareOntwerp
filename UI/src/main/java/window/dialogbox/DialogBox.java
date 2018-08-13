package window.dialogbox;

import action.Action;
import action.EmptyAction;
import action.UpdateListAction;
import exception.UIException;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.Subwindow;
import window.WindowLevelCounter;
import window.elements.DialogboxElement;
import window.elements.radiobutton.RadioButton;

import java.awt.geom.Point2D;
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

    public abstract List<DialogboxElement> getStaticList();
    /**
     * create a new dialogbox at the given position
     *
     * @param position the new position
     */
    public DialogBox(Point2D position) {
        super(position, WindowLevelCounter.getNextLevel());
        this.invalidDescriptionMode = false;
    }

    public abstract void updateList();

    /**
     * handles a mouseEvent
     *
     * @param mouseEvent the mouseEvent to handle
     * @return an action detailing the change by the mouseEvent
     */
    @Override
    public Action handleMouseEvent(MouseEvent mouseEvent){
        if(invalidDescriptionMode){
            return new EmptyAction();
        }
        if(designerMode){
            switch (mouseEvent.getMouseEventType()) {
                case LEFTDOUBLECLICK:
                    DialogboxElement last = null;
                    for(DialogboxElement ele:getStaticList()){
                        if(ele.isClicked(mouseEvent.getPoint())){
                            last = ele;
                        }
                    }
                    if(last != null){
                        last = last.clone();
                        try {
                            last.setCoordinate(new Point2D.Double(last.getCoordinate().getX() + 30, last.getCoordinate().getY() + 30));
                        }catch(UIException e){
                            e.printStackTrace();
                        }
                        getStaticList().add(last);
                        updateList();
                    }
                    break;
                case DRAG:
                    setDragging(true);
                    break;
                case RELEASE:
                    if(isDragging()){
                        try {
                            getStaticList().get(selectedindex).setCoordinate(mouseEvent.getPoint());
                            selected = elementList.get(selectedindex);
                        }catch(UIException e){
                            e.printStackTrace();
                        }

                    }
                    setDragging(false);
                    updateList();
                    break;
                case PRESSED:
                    return handleMousePress(mouseEvent);
            }
            return new UpdateListAction();
        }
        else{
            switch (mouseEvent.getMouseEventType()) {
                case PRESSED:
                    return handleMousePress(mouseEvent);
            }
        }
        return new EmptyAction();
    }

    private Action handleMousePress(MouseEvent mouseEvent) {
        updateList();
        if(elementList.size() < 1){
            return new EmptyAction();
        }
        for(int i = 0; i < elementList.size(); i++){
            if(elementList.get(i).isClicked(mouseEvent.getPoint())){
                selected = elementList.get(i);
                selectedindex = i;
            }
        }
        if(designerMode){
            return new EmptyAction();
        }
        if(selected.isClicked(mouseEvent.getPoint())){
            System.out.println("------- " + ((RadioButton)selected).getCommand());
            Action action = selected.performAction();
            handleAction(action);
            return action;
        }
        else{
            return new EmptyAction();
        }

    }

    /**
     * handles a keyEvent
     *
     * @param keyEvent the keyEvent to handle
     * @return an action detailing the change by the keyEvent
     */
    @Override
    public abstract Action handleKeyEvent(KeyEvent keyEvent);


}
