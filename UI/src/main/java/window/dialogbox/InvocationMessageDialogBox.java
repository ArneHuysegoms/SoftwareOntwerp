package window.dialogbox;

import action.*;
import command.InvocationCommand.AddArgumentCommand;
import command.InvocationCommand.DeleteArgumentCommand;
import command.InvocationCommand.MoveDownCommand;
import diagram.DiagramElement;
import diagram.label.InvocationMessageLabel;
import diagram.message.InvocationMessage;
import exception.UIException;
import exceptions.DomainException;
import uievents.KeyEvent;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;
import window.elements.ListBox;
import window.elements.button.AddArgumentButton;
import window.elements.button.DeleteArgumentButton;
import window.elements.button.MoveDownButton;
import window.elements.button.MoveUpButton;
import window.elements.textbox.ArgumentTextBox;
import window.elements.textbox.MethodTextBox;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * dialogbox for changing invocation messages
 */
public class InvocationMessageDialogBox extends DialogBox {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;

    private InvocationMessageLabel invocationMessageLabel;
    private DiagramSubwindow subwindow;
    private int listBoxIndex;

    public static ArrayList<DialogboxElement> INVOCATIONMESSAGEBOXLIST;

    /**
     * initiate static list
     */
    static {
        try{
            INVOCATIONMESSAGEBOXLIST = new ArrayList<DialogboxElement>(Arrays.asList(
                    new MethodTextBox(new Point2D.Double(10, 50), "method"),
                    new ArgumentTextBox(new Point2D.Double(10, 75), "argument"),
                    new AddArgumentButton(new AddArgumentCommand(null,null,null,null), new Point2D.Double(10, 100),""),
                    new DeleteArgumentButton(new DeleteArgumentCommand(null,null,null), new Point2D.Double(50, 100), ""),
                    new MoveDownButton(new MoveDownCommand(null,null,null),new Point2D.Double(90, 100),""),
                    new MoveUpButton(new MoveDownCommand(null,null,null),new Point2D.Double(130, 100),""),
                    new ListBox(new Point2D.Double(10, 140), "")));

        }catch (UIException e){
            e.printStackTrace();
        }
    }

    /**
     * set list box index
     * @param listBoxIndex index of the now selected element in the listbox
     */
    public void setListBoxIndex(int listBoxIndex) {
        this.listBoxIndex = listBoxIndex;
    }

    /**
     *
     * @return the static list
     */
    @Override
    public List<DialogboxElement> getStaticList(){
        return INVOCATIONMESSAGEBOXLIST;
    }

    /**
     * syncs the static list and the private list
     * also sets the selected elements for the list itself and the listbox
     */
    @Override
    public void updateList() {
        List<ListBox> tempLB;
        List<ArgumentTextBox> tempTB;
        this.elementList = new ArrayList<>();
        for (DialogboxElement d : INVOCATIONMESSAGEBOXLIST) {
            DialogboxElement clone = d.clone();
            clone.update(invocationMessageLabel);
            clone.update(getSubwindow(),invocationMessageLabel,null,null);
            elementList.add(clone);
        }

        tempLB = findListBox();
        tempTB = findArgumentTextBox();
        setAllSelectedIndexes(tempLB,listBoxIndex);

        if(listBoxIndex < 0){
            setAllSelectedIndexes(tempLB,invocationMessageLabel.getArguments().size() - 1);
        }

        for (DialogboxElement d : elementList) {
            d.update(getSubwindow(),invocationMessageLabel,tempLB.get(0),tempTB.get(0));
        }
        if(elementList.size() == 0){
            selected = null;
        }
        else if(selectedindex > elementList.size()-1){
            selectedindex = 0;
            selected = this.elementList.get(selectedindex);
        }else{

            selected = this.elementList.get(selectedindex);
        }
    }

    /**
     * sets all indices of listboxes
     * @param tempLB
     * @param listBoxIndex
     */
    private void setAllSelectedIndexes(List<ListBox> tempLB, int listBoxIndex) {
        if(tempLB != null){
            for(ListBox lb : tempLB){
                lb.setSelectedIndex(listBoxIndex);
            }
        }
    }

    /**
     * finds all argumentTextBoxes
     * @return list of argument text boxes
     */
    private List<ArgumentTextBox> findArgumentTextBox() {
        List<ArgumentTextBox> result = new ArrayList<>();
        for (DialogboxElement ele :elementList){
            if(ele instanceof ArgumentTextBox)
                result.add( (ArgumentTextBox)ele);
        }
        return result;
    }

    /**
     * finds all listboxes
     * @return list of list boxes
     */
    private List<ListBox> findListBox() {
        List<ListBox> result = new ArrayList<>();
        for (DialogboxElement ele :elementList){
            if(ele instanceof ListBox)
                result.add((ListBox)ele);
        }
        return result;
    }

    /**
     * create a new invocation message dialog box
     *
     * @param pos                    the new position for the invocation message dialog box
     * @param invocationMessageLabel the label this dialogbox servers a purpose for
     * @param subwindow              the subwindow this dialogbox was created for
     * @throws UIException if the pos is null
     */
    public InvocationMessageDialogBox(Point2D pos, InvocationMessageLabel invocationMessageLabel, DiagramSubwindow subwindow) throws UIException {
        super(pos);
        this.invocationMessageLabel = invocationMessageLabel;
        this.elementList = new ArrayList<>();
        this.listBoxIndex = -1;
        this.subwindow = subwindow;
        this.setHeight(HEIGHT);
        this.setWidth(WIDTH);
        updateList();
        invocationMessageLabel.setIndex(invocationMessageLabel.getArguments().size() - 1);
    }

    /**
     * @return the default width for this dialogbox type
     */
    public static int getWIDTH() {
        return WIDTH;
    }

    /**
     * @return the default height for this dialogbox type
     */
    public static int getHEIGHT() {
        return HEIGHT;
    }

    /**
     * @return the currently selected dialogbox element
     */
    public DialogboxElement getSelected() {
        return selected;
    }

    /**
     * @return the label that's being edited by this invocation message label
     */
    public InvocationMessageLabel getInvocationMessageLabel() {
        return invocationMessageLabel;
    }

    /**
     * @return the subwindow this dialogbox was created for
     */
    public DiagramSubwindow getSubwindow() {
        return subwindow;
    }

    /**
     * handle a keyEvent, needs to be overwritten because ARROWKEYUP and ARROWKEYDOWN
     *
     * @param keyEvent the keyEvent to handle
     * @return an action detailing the outcome of the handling
     */
    @Override
    public Action handleKeyEvent(KeyEvent keyEvent) {
        if(invalidDescriptionMode){
            switch (keyEvent.getKeyEventType()) {
                case CHAR:
                    return handleChar(keyEvent);
            }
            return new EmptyAction();
        }
        if(designerMode){
            switch (keyEvent.getKeyEventType()) {
                case CHAR:
                    return handleChar(keyEvent);
                case BACKSPACE:
                    return handleBackSpace();
                case ENTER:
                    setDesignerMode(false);
                    break;
                case DEL:
                    DialogboxElement last = null;
                    for(DialogboxElement ele:getStaticList()){
                        if(ele.getCoordinate().equals(selected.getCoordinate())){
                            last = ele;
                        }
                    }
                    if(last != null){
                        getStaticList().remove(last);
                    }
                    updateList();
                    cycleSelectedElement();
                    break;
            }
        }

        else{
            switch (keyEvent.getKeyEventType()) {
                case TAB:
                    cycleSelectedElement();
                    break;
                case ARROWKEYUP:
                    return handleArrowKeyUp();
                case ARROWKEYDOWN:
                    return handleArrowKeyDown();
                case SPACE:
                    return handleSpace();
                case CHAR:
                    return handleChar(keyEvent);
                case BACKSPACE:
                    return handleBackSpace();
                case CTRLE:
                    setDesignerMode(true);
                    break;
            }
        }
        return new EmptyAction();
    }

    /**
     * handle the backspace event
     *
     * @return an action detailing the outcome of the handling
     */
    public Action handleBackSpace() {
        if(!designerMode){

            selected.deleteLastCharFromContents();
            if (selected instanceof MethodTextBox) {
                try {
                    return changeMethod();
                } catch (DomainException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            DialogboxElement d = INVOCATIONMESSAGEBOXLIST.get(selectedindex);
            d.deleteCharFromDescription();
            if(!d.isValidDescription()){
                setInvalidDescriptionMode(true);
            }
            return new UpdateListAction();
        }

        return new EmptyAction();
    }

    /**
     * handle a char KeyEvent
     *
     * @param keyEvent the keyEvent containing the char
     * @return an action detailing the outcome of the handling
     */
    public Action handleChar(KeyEvent keyEvent){
        if(!designerMode){

            selected.addCharToContents(keyEvent.getKeyChar());
            if (selected instanceof MethodTextBox) {
                try {
                    return changeMethod();
                } catch (DomainException e) {
                    e.printStackTrace();
                }
            }
            return new EmptyAction();
        }
        else{
            if(selected != null){
                DialogboxElement d = INVOCATIONMESSAGEBOXLIST.get(selectedindex);
                d.addCharToDescription(keyEvent.getKeyChar());
                if(d.isValidDescription()){
                    setInvalidDescriptionMode(false);
                }

            }
            return new UpdateListAction();
        }
    }

    /**
     * change the method of the invocation message
     * selected can only be MethodTextBox here
     *
     * @return an action detailing the outcome of the handling
     * @throws DomainException if illegal modifications are made
     */
    private Action changeMethod() throws DomainException {
        if ((selected).hasValidContents()) {
            invocationMessageLabel.setLabel((selected).getContents());
            return new UpdateLabelAction(subwindow.getFacade().findParentElement(invocationMessageLabel), invocationMessageLabel);
        }
        return new EmptyAction();
    }

    /**
     * handle the array key down event
     */
    private Action handleArrowKeyDown() {
        invocationMessageLabel.setIndex(invocationMessageLabel.getIndex() + 1);
        this.listBoxIndex = invocationMessageLabel.getIndex();
        setAllSelectedIndexes(findListBox(),listBoxIndex);
        return new UpdateLabelAction(subwindow.getFacade().findParentElement(invocationMessageLabel), invocationMessageLabel);
    }

    /**
     * handle the arrow key up event
     */
    private Action handleArrowKeyUp() {
        invocationMessageLabel.setIndex(invocationMessageLabel.getIndex() - 1);
        this.listBoxIndex = invocationMessageLabel.getIndex();
        setAllSelectedIndexes(findListBox(),listBoxIndex);
        return new UpdateLabelAction(subwindow.getFacade().findParentElement(invocationMessageLabel), invocationMessageLabel);
    }

    /**
     * handles an action that could change this dialogbox type
     *
     * @param action the action to handle
     */
    @Override
    public void handleAction(Action action) {
        if (action instanceof RemoveInViewsAction) {
            RemoveInViewsAction a = (RemoveInViewsAction) action;
            for(DiagramElement element : a.getDeletedElements()) {
                if(element instanceof InvocationMessage) {
                    InvocationMessage inv = (InvocationMessage) element;
                    if (inv.getLabel() == invocationMessageLabel) {
                        this.getFrame().close();
                    }
                }
            }
        }
        if (action instanceof UpdateLabelAction) {
            UpdateLabelAction a = (UpdateLabelAction) action;
            InvocationMessage invocationMessage = (InvocationMessage) subwindow.getFacade().findParentElement(invocationMessageLabel);
            if (a.getElement().equals(invocationMessage)) {
                updateList();
            }
            this.listBoxIndex = invocationMessageLabel.getIndex();
            setAllSelectedIndexes(findListBox(),listBoxIndex);
        }
        if(action instanceof UpdateListAction){
            updateList();
        }
        if(action instanceof UpdateInvocationMessageLabelAction){
            invocationMessageLabel.setIndex(((UpdateInvocationMessageLabelAction)action).getIndex());
            int index = ((UpdateInvocationMessageLabelAction)action).getIndex();
            setListBoxIndex(index);
            setAllSelectedIndexes(findListBox(), index);
        }
    }
}
