package window.dialogbox;

import action.*;
import command.changeType.PartyCommand.ChangeToActorCommand;
import command.changeType.PartyCommand.ChangeToObjectCommand;
import diagram.message.ResultMessage;
import exception.UIException;
import exceptions.DomainException;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;
import window.elements.radiobutton.PartyRadioButton;
import window.elements.textbox.ClassTextBox;
import window.elements.textbox.InstanceTextBox;
import window.elements.textbox.MethodTextBox;
import window.elements.textbox.TextBox;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * dialogbox for changing resultmessages
 */
public class ResultMessageDialogBox extends DialogBox {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 60;

    //private TextBox labelTextBox;
    private ResultMessage resultMessage;
    private DiagramSubwindow diagramSubwindow;

    //private TextBox selected;

    public static ArrayList<DialogboxElement> RESULTMESSAGEBOXLIST;

    static {
        try {
            RESULTMESSAGEBOXLIST = new ArrayList<DialogboxElement>(Arrays.asList(
                    new MethodTextBox(new Point2D.Double(10, 40), "Method")));


        } catch (UIException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<DialogboxElement> getStaticList(){
        return RESULTMESSAGEBOXLIST;
    }

    @Override
    public void updateList() {
        for (DialogboxElement d : getStaticList()) {
            DialogboxElement clone = d.clone();
            elementList.add(clone);
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
     * create a new resultmessage dialogbox
     *
     * @param pos              the position
     * @param resultMessage    the resultmessage to change
     * @param diagramSubwindow the subwindow this dialogbox was created for
     * @throws UIException if the position is null
     */
    public ResultMessageDialogBox(Point2D pos, ResultMessage resultMessage, DiagramSubwindow diagramSubwindow) throws UIException {
        super(pos);
        this.resultMessage = resultMessage;
        this.diagramSubwindow = diagramSubwindow;
        /*this.labelTextBox = new MethodTextBox(new Point2D.Double(10, 40), "Method");
        /*/
        elementList = new ArrayList<>();
        this.setHeight(HEIGHT);
        this.setWidth(WIDTH);
        //selected = labelTextBox;
        updateList();
        this.selectedindex = 0;
        if(elementList.size() > 0){

            selected = this.elementList.get(getSelectedindex());
        }
        updateFields(resultMessage);
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
     * @return the result message this dialogbox serves
     */
    public ResultMessage getResultMessage() {
        return resultMessage;
    }

    /**
     * @return the diagramsubwindow this dialogbox was created in
     */
    public DiagramSubwindow getDiagramSubwindow() {
        return diagramSubwindow;
    }

    /**
     * handles mouse events
     *
     * @param mouseEvent the mouseEvent to handle
     * @return an action detailing the outcome of the handling
     */
    /*@Override
    public Action handleMouseEvent(MouseEvent mouseEvent) {
        return new EmptyAction();
    }*/

    /**
     * handles key events
     *
     * @param keyEvent the keyEvent to handle
     * @return an action detailing the outcome of the handling
     */
    /*@Override
    public Action handleKeyEvent(KeyEvent keyEvent) {
        switch (keyEvent.getKeyEventType()) {
            case CHAR:
                return handleChar(keyEvent);
            case BACKSPACE:
                return handleBackSpace();
            case CTRLE:
                setDesignerMode(true);
                System.out.println("DESIGNER MODE ON");
            case ENTER:
                if(designerMode){
                    setDesignerMode(false);
                    System.out.println("DESIGNER MODE OFF");
                }
        }
        return new EmptyAction();
    }*/

    /**
     * handle the backspace event
     *
     * @return an action detailing the outcome of the handling
     * @throws DomainException if illegal modifications ara made
     */
    @Override
    public Action handleBackSpace(){
        if(!designerMode) {
            if (((TextBox) selected).hasValidContents()) {
                ((TextBox) selected).deleteLastCharFromContents();
                try {
                    return changeResultMessageLabel();
                } catch (DomainException e) {
                    e.printStackTrace();
                }
            }

        }
        else if(designerMode) {
            DialogboxElement d = getStaticList().get(selectedindex);
            d.deleteCharFromDescription();
            System.out.println("SYSOUT------------" + d.getDescription());
            if (!d.isValidDescription()) {
                setInvalidDescriptionMode(true);
            }
            return new UpdateListAction();
        }
        return new EmptyAction();
    }

    /**
     * handles the char key event
     *
     * @param keyEvent the keyEvent containing the char
     * @return an action detailing the outcome of the handling
     * @throws DomainException if illegal modifications are made
     */
    @Override
    public Action handleChar(KeyEvent keyEvent) {
        if (!designerMode) {
            ((TextBox) selected).addCharToContents(keyEvent.getKeyChar());
            if (((TextBox) selected).hasValidContents()) {
                try {
                    return changeResultMessageLabel();
                } catch (DomainException e) {
                    e.printStackTrace();
                }
            }
        }
        else if (designerMode && selected != null) {
            DialogboxElement d = getStaticList().get(selectedindex);
            d.addCharToDescription(keyEvent.getKeyChar());
            if (d.isValidDescription()) {
                setInvalidDescriptionMode(false);
            }
            return new UpdateListAction();
        }
        return new EmptyAction();
    }


    /**
     * change the resultmessage label
     *
     * @return an action detailing the outcome of the handling
     * @throws DomainException if illegal modifications are made
     */
    private Action changeResultMessageLabel() throws DomainException {
        getResultMessage().getLabel().setLabel(((TextBox)selected).getContents());
        return new UpdateLabelAction(getResultMessage(), getResultMessage().getLabel());
    }

    /**
     * handles actions
     *
     * @param action the action to handle
     */
    @Override
    public void handleAction(Action action) {
        if (action instanceof RemoveInViewsAction) {
            RemoveInViewsAction a = (RemoveInViewsAction) action;
            if (a.getDeletedElements().contains(resultMessage)) {
                this.getFrame().close();
            }
        }
        if (action instanceof UpdateLabelAction) {
            UpdateLabelAction a = (UpdateLabelAction) action;
            if (a.getElement().equals(resultMessage)) {
                updateFields((ResultMessage) a.getElement());
            }
        }
        if (action instanceof UpdateListAction){
            updateList();
        }
    }

    /**
     * change all fields of this dialogbox for the given resultmessage
     *
     * @param resultMessage the resultmessage to parse
     */
    private void updateFields(ResultMessage resultMessage) {
        if(selected != null){
            ((TextBox)selected).setContents(resultMessage.getLabel().getLabel());
        }
    }
}
