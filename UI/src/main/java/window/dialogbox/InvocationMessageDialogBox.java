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
import window.elements.button.FakeButtons.AddArgumentFakeButton;
import window.elements.button.FakeButtons.DeleteArgumentFakeButton;
import window.elements.button.FakeButtons.MoveDownFakeButton;
import window.elements.button.FakeButtons.MoveUpFakeButton;
import window.elements.button.MoveDownButton;
import window.elements.button.MoveUpButton;
import window.elements.textbox.ArgumentTextBox;
import window.elements.textbox.MethodTextBox;
import window.elements.textbox.TextBox;

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

    //private MethodTextBox methodTextBox;
    //private ArgumentTextBox argumentTextBox;

    //private ListBox argumentListBox;

    //private FakeButton addArgument;
    //private FakeButton deleteArgument;
    //private FakeButton moveUp;
    //private FakeButton moveDown;

    //private DialogboxElement selected;

    //private List<DialogboxElement> dialogboxElements;

    private InvocationMessageLabel invocationMessageLabel;
    private DiagramSubwindow subwindow;
    private int listBoxIndex;

    public static ArrayList<DialogboxElement> INVOCATIONMESSAGEBOXLIST;

    static {
        try{
            INVOCATIONMESSAGEBOXLIST = new ArrayList<DialogboxElement>(Arrays.asList(
                    new MethodTextBox(new Point2D.Double(10, 50), "method"),
                    new ArgumentTextBox(new Point2D.Double(10, 75), "argument"),
                    new AddArgumentButton(new AddArgumentCommand(null,null,null,null), new Point2D.Double(10, 100),""),
                    new DeleteArgumentButton(new DeleteArgumentCommand(null,null,null), new Point2D.Double(50, 100), ""),
                    new MoveDownButton(new MoveDownCommand(null,null,null,null),new Point2D.Double(90, 100),""),
                    new MoveUpButton(new MoveDownCommand(null,null,null,null),new Point2D.Double(130, 100),""),
                    new ListBox(new Point2D.Double(10, 140), "")));

        }catch (UIException e){
            e.printStackTrace();
        }
    }

    public void setListBoxIndex(int listBoxIndex) {
        this.listBoxIndex = listBoxIndex;
    }

    @Override
    public List<DialogboxElement> getStaticList(){
        return INVOCATIONMESSAGEBOXLIST;
    }

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

    private void setAllSelectedIndexes(List<ListBox> tempLB, int listBoxIndex) {
        if(tempLB != null){
            for(ListBox lb : tempLB){
                lb.setSelectedIndex(listBoxIndex);
            }
        }
    }

    private List<ArgumentTextBox> findArgumentTextBox() {
        List<ArgumentTextBox> result = new ArrayList<>();
        for (DialogboxElement ele :elementList){
            if(ele instanceof ArgumentTextBox)
                result.add( (ArgumentTextBox)ele);
        }
        return result;
    }

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
        //updateFields((InvocationMessage) subwindow.getFacade().findParentElement(invocationMessageLabel));
        //argumentListBox.setSelectedIndex(invocationMessageLabel.getArguments().size() - 1);
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
     * @return all dialogbox elements in this dialogbox
     */
    /*public List<DialogboxElement> getDialogboxElements() {
        return dialogboxElements;
    }*/

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
     * handle a mouseEvent
     *
     * @param mouseEvent the mouseEvent to handle
     * @return an action detailing the outcome of the handling
     */
    /*@Override
    public Action handleMouseEvent(MouseEvent mouseEvent) {
        switch (mouseEvent.getMouseEventType()) {
            case PRESSED:
                return handlePressed(mouseEvent.getPoint());
            default:
                break;
        }
        return new EmptyAction();
    }*/

    /**
     * handle the moouse pressed event at the given location
     *
     * @param point the point of the event
     * @return an action detailing the outcome of the handling
     */
    /*private Action handlePressed(Point2D point) {
        if (methodTextBox.isClicked(point)) {
            selected = methodTextBox;
        } else if (argumentTextBox.isClicked(point)) {
            selected = argumentTextBox;
        } else if (addArgument.isClicked(point)) {
            selected = addArgument;
            return handleAddArgument();
        } else if (argumentListBox.isClicked(point)) {
            selected = argumentListBox;
            handleArgumentListBoxClick(point);
        }
        if (additionalButtonsAreActive()) {
            if (deleteArgument.isClicked(point)) {
                selected = deleteArgument;
                return handleDeleteArgument();
            } else if (moveDown.isClicked(point)) {
                selected = moveDown;
                return handleMoveDown();
            } else if (moveUp.isClicked(point)) {
                selected = moveUp;
                return handleMoveUp();
            }
        }
        return new EmptyAction();
    }*/

    /**
     * handle a keyEvent
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
     * @throws DomainException if illegal modifications are made
     */
    public Action handleBackSpace() {
        if(!designerMode){
            if (selected instanceof TextBox) {
                TextBox t = (TextBox) selected;
                t.deleteLastCharFromContents();
                if (t instanceof MethodTextBox) {
                    try {
                        return changeMethod();
                    } catch (DomainException e) {
                        e.printStackTrace();
                    }
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
     * @throws DomainException if illegal modifications are made
     */
    public Action handleChar(KeyEvent keyEvent){
        if(!designerMode){

            if (selected instanceof TextBox) {
                TextBox t = (TextBox) selected;
                t.addCharToContents(keyEvent.getKeyChar());
                if (t instanceof MethodTextBox) {
                    try {
                        return changeMethod();
                    } catch (DomainException e) {
                        e.printStackTrace();
                    }
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
     *
     * @return an action detailing the outcome of the handling
     * @throws DomainException if illegal modifications are made
     */
    private Action changeMethod() throws DomainException {
        if (((MethodTextBox)selected).hasValidContents()) {
            invocationMessageLabel.setLabel(((MethodTextBox)selected).getContents());
            return new UpdateLabelAction(subwindow.getFacade().findParentElement(invocationMessageLabel), invocationMessageLabel);
        }
        return new EmptyAction();
    }

    /**
     * handle the space event
     *
     * @return an action detailing the outcome of the handling
     */
    /*public Action handleSpace() {
        if (addArgument == selected) {
            return handleAddArgument();
        } else if (additionalButtonsAreActive()) {
            if (deleteArgument == selected) {
                return handleDeleteArgument();
            } else if (moveDown == selected) {
                return handleMoveDown();
            } else if (moveUp == selected) {
                return handleMoveUp();
            }
        }
        return new UpdateLabelContainersAction(invocationMessageLabel);
    }*/

    /**
     * checks if the additional buttons are active
     *
     * @return true if the additional buttons are active
     */
    private boolean additionalButtonsAreActive() {
        return ((ListBox)selected).hasSelectedArgument();
    }

    /**
     * handle adding an argument
     *
     * @return an action detailing the outcome of the handling
     */
    private Action handleAddArgument() {
        if (((TextBox)selected).hasValidContents()) {
            String argumentString = ((TextBox)selected).getContents();
            ((ListBox)selected).addArgument(argumentString);
            invocationMessageLabel.addArgument(argumentString);
            return new UpdateLabelAction(subwindow.getFacade().findParentElement(invocationMessageLabel), invocationMessageLabel);
        }
        return new EmptyAction();
    }

    /**
     * handles a click on the argument list box
     *
     * @param point the point to handle
     */
    private void handleArgumentListBoxClick(Point2D point) {
        selected = ((ListBox)selected);
        ((ListBox)selected).selectArgument(point);
        invocationMessageLabel.setIndex(((ListBox)selected).getSelectedIndex());
    }

    /**
     * handles deleting an argument
     *
     * @return an action detailing the outcome of the handling
     */
    private Action handleDeleteArgument() {
        invocationMessageLabel.deleteArgument(((ListBox)selected).getSelectedIndex());
        ((ListBox)selected).removeArgument();
        return new UpdateLabelAction(subwindow.getFacade().findParentElement(invocationMessageLabel), invocationMessageLabel);
    }

    /**
     * handles moving up arguments
     *
     * @return an action detailing the outcome of the handling
     */
    private Action handleMoveUp() {
        ((ListBox)selected).moveUp();
        invocationMessageLabel.moveUp();
        return new UpdateLabelAction(subwindow.getFacade().findParentElement(invocationMessageLabel), invocationMessageLabel);
    }

    /**
     * handle moving down arguments
     *
     * @return an action detailing the outcome of the handling
     */
    private Action handleMoveDown() {
        ((ListBox)selected).moveDown();
        invocationMessageLabel.moveDown();
        return new UpdateLabelAction(subwindow.getFacade().findParentElement(invocationMessageLabel), invocationMessageLabel);
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
     * cycle the selected argument
     */
    /*public void cycleSelectedElement() {
        int oldIndex = dialogboxElements.indexOf(selected);
        selected = dialogboxElements.get((oldIndex + 1) % 7);
    }*/

    /**
     * handles an action that could change this dialogbox type
     *
     * @param action the action to handle
     */
    @Override
    public void handleAction(Action action) {
        System.out.println("HELLO");
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
            System.out.println("HELLOO");
            UpdateLabelAction a = (UpdateLabelAction) action;
            InvocationMessage invocationMessage = (InvocationMessage) subwindow.getFacade().findParentElement(invocationMessageLabel);
            if (a.getElement().equals(invocationMessage)) {
                //updateFields((InvocationMessage) a.getElement());
                updateList();
            }

            System.out.println(invocationMessageLabel.getIndex() + " is inv index2");
            System.out.println(listBoxIndex + " is lb index2");
            this.listBoxIndex = invocationMessageLabel.getIndex();
            setAllSelectedIndexes(findListBox(),listBoxIndex);

            System.out.println(invocationMessageLabel.getIndex() + " is inv index2");
            System.out.println(listBoxIndex + " is lb index2");
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

    /**
     * update all fields for the given invocation message
     *
     * @param invocationMessage the message to change the fields for
     */
    /*private void updateFields(InvocationMessage invocationMessage) {
        if (invocationMessage != null) {
            InvocationMessageLabel label = (InvocationMessageLabel) invocationMessage.getLabel();
            methodTextBox.setContents(label.getLabel());
            argumentListBox.setArguments(label.getArguments().stream()
                                            .map(s -> s.toString())
                                            .collect(Collectors.toList()));
        }
    }*/

    /**
     * sets the given element as the selected
     *
     * @param dialogboxElement the new selected dialogboxelement
     */
    /*public void setSelected(DialogboxElement dialogboxElement) {
        this.selected = dialogboxElement;
    }*/
}
