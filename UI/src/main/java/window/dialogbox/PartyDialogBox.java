package window.dialogbox;

import action.*;
import command.changeType.PartyCommand.ChangeToActorCommand;
import command.changeType.PartyCommand.ChangeToObjectCommand;
import command.closeWindow.CloseSubwindowCommand;
import diagram.party.Party;
import exception.UIException;
import uievents.KeyEvent;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;
import window.elements.radiobutton.PartyRadioButton;
import window.elements.button.CloseWindowButton;
import window.elements.radiobutton.ToActorRadioButton;
import window.elements.radiobutton.ToObjectRadioButton;
import window.elements.textbox.ClassTextBox;
import window.elements.textbox.InstanceTextBox;
import window.elements.textbox.TextBox;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * dialog box for changing parties
 */
public class PartyDialogBox extends DialogBox {

    public static final int WIDTH = 150;
    public static final int HEIGHT = 200;

    //public String TOOBJECT_DESPCRIPTION = "Object";
    //public String TOACTOR_DESCRIPTION = "Actor";

    //public String INSTANCE_DESCRIPTION = "Instance";
    //public String CLASS_DESCRIPTION = "Class";

    //private RadioButton toActor;
    //private RadioButton toObject;

    //private TextBox instanceTextBox;
    //private TextBox classTextBox;

    //private DialogboxElement selected;

    private Party party;

    private DiagramSubwindow subwindow;

    public static ArrayList<DialogboxElement> PARTYBOXLIST;

    //private int selectedindex;

    static {
        try {
            PARTYBOXLIST = new ArrayList<DialogboxElement>(Arrays.asList(new ToActorRadioButton(new ChangeToActorCommand(null, null), new Point2D.Double(10, 30), "Actor"),
                    new ToObjectRadioButton(new ChangeToObjectCommand(null, null), new Point2D.Double(85, 30), "Object"),
                    new InstanceTextBox(new Point2D.Double(10, 60), "Instance"),
                    new ClassTextBox(new Point2D.Double(10, 85), "Class")));
        } catch (UIException e) {
            e.printStackTrace();
        }
    }

    /**
     * create a new party dialog box
     *
     * @param pos       the position for this dialogbox
     * @param party     the party this dialogbox is created for
     * @param subwindow the subwindow this dialogbox was created in
     * @throws UIException if the position is null
     */
    public PartyDialogBox(Point2D pos, Party party, DiagramSubwindow subwindow) throws UIException {
        super(pos);
        this.setParty(party);

        elementList = new ArrayList<>();
        this.subwindow = subwindow;

        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        //updateFields(party);
        updateList();
        this.selectedindex = 0;
        if(elementList.size() > 0){

            selected = this.elementList.get(getSelectedindex());
        }
    }

    public void updateList(){
        elementList = new ArrayList<>();
        for (DialogboxElement e : PARTYBOXLIST){
            DialogboxElement clone = e.clone();
            clone.update(subwindow,party);
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
     * @return the party this dialogbox edits
     */
    public Party getParty() {
        return party;
    }

    /**
     * the party this dialogbox needs to edit
     *
     * @param party the party for this dialogbox
     */
    public void setParty(Party party) {
        this.party = party;
    }

    /**
     * @return the subwindow this dialogbox edits
     */
    public DiagramSubwindow getSubwindow() {
        return subwindow;
    }

    @Override
    public List<DialogboxElement> getStaticList(){
        return PARTYBOXLIST;
    }

    /**
     * handle a mouse event
     *
     * @param mouseEvent the mouseEvent to handle
     * @return an action detailing the outcome of the handling
     */
    /*@Override
    public Action handleMouseEvent(MouseEvent mouseEvent) {
        if(invalidDescriptionMode){
            return new EmptyAction();
        }
        if(designerMode){
            switch (mouseEvent.getMouseEventType()) {
                case LEFTDOUBLECLICK:
                    DialogboxElement last = null;
                    for(DialogboxElement ele:PARTYBOXLIST){
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
                        PARTYBOXLIST.add(last);
                        updateList();
                    }
                    break;
                case DRAG:
                    setDragging(true);
                    break;
                case RELEASE:
                        if(isDragging()){
                            try {
                                PARTYBOXLIST.get(selectedindex).setCoordinate(mouseEvent.getPoint());
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
    }*/

    /**
     * handle a keyEvent
     *
     * @param keyEvent the keyEvent to handle
     * @return an action detailing the outcome of the handling
     */
    /*@Override
    public Action handleKeyEvent(KeyEvent keyEvent) {
        if(invalidDescriptionMode){
            switch (keyEvent.getKeyEventType()) {
                case CHAR:
                    return handleChar(keyEvent);
            }
            return new EmptyAction();
        }
        else{
            switch (keyEvent.getKeyEventType()) {
                case TAB:
                    cycleSelectedElement();
                    break;
                case SPACE:
                    return handleSpace();
                case CHAR:
                    return handleChar(keyEvent);
                case BACKSPACE:
                    return handleBackSpace();
                case CTRLE:
                    setDesignerMode(true);
                    break;
                case ENTER:
                    if(designerMode){
                        setDesignerMode(false);
                    }
                    break;
                case DEL:
                    if(designerMode){
                        DialogboxElement last = null;
                        for(DialogboxElement ele:PARTYBOXLIST){
                            if(ele.getCoordinate().equals(selected.getCoordinate())){
                                last = ele;
                            }
                        }

                        if(last != null){
                            PARTYBOXLIST.remove(last);
                        }
                        updateList();
                        cycleSelectedElement();
                    }
                    break;

            }
            return new EmptyAction();
        }

    }*/


    /**
     * handle a mouse press event
     *
     * @param mouseEvent the mouse event to handle
     * @return an action detailing the outcome of the handling
     */
    /*private Action handleMousePress(MouseEvent mouseEvent) {
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
            Action action = selected.performAction();
            handleAction(action);
            return action;
        }
        else{
            return new EmptyAction();
        }

    }*/

    /**
     * handle the backspace event
     *
     * @return an action detailing the outcome of the handling
     */
    @Override
    protected Action handleBackSpace() {
        if(!designerMode){
            if (selected instanceof TextBox) {
                TextBox t = (TextBox) selected;
                t.deleteLastCharFromContents();
                if (t.hasValidContents()) {
                    return changePartyLabel(t.getContents());
                }
            }
            return new EmptyAction();
        }
        else{
            DialogboxElement d = PARTYBOXLIST.get(selectedindex);
            d.deleteCharFromDescription();
            if(!d.isValidDescription()){
                setInvalidDescriptionMode(true);
            }
            return new UpdateListAction();
        }
    }



    /**
     * handles a char keyEvent
     *
     * @param keyEvent the keyEvent with the char
     * @return an action detailing the outcome of the handling
     */
    @Override
    public Action handleChar(KeyEvent keyEvent) {
        if(!designerMode){
            if (selected instanceof TextBox) {
                TextBox t = (TextBox) selected;
                t.addCharToContents(keyEvent.getKeyChar());
                if (t.hasValidContents()) {
                    return changePartyLabel(t.getContents());
                }
            }
            return new EmptyAction();
        }
        else{
            if(selected != null){
                DialogboxElement d = PARTYBOXLIST.get(selectedindex);
                d.addCharToDescription(keyEvent.getKeyChar());
                if(d.isValidDescription()){
                    setInvalidDescriptionMode(false);
                }

            }
            return new UpdateListAction();
        }

    }


    /**
     * handle changing the party label
     *
     * @return an action detailing the outcome of the handling
     */
    private Action changePartyLabel(String contents) {
        try {
            if (selected instanceof InstanceTextBox) {
                TextBox t = (TextBox) selected;
                String oldLabel = party.getLabel().getLabel();
                String[] split = oldLabel.split(":");
                if (split.length == 1) {
                    if(party.getLabel().isValidLabel(t.getContents() + ":" + split[0])) {
                        party.getLabel().setLabel(t.getContents() + ":" + split[0]);
                    }
                } else {
                    if(party.getLabel().isValidLabel(t.getContents() + ":" + split[1])) {
                        party.getLabel().setLabel(t.getContents() + ":" + split[1]);
                    }
                }
            } else {
                TextBox t = (TextBox) selected;
                String oldLabel = party.getLabel().getLabel();
                String[] split = oldLabel.split(":");
                if (split.length == 1) {
                    //if (!this.getInstanceTextBox().getContents().isEmpty() && Character.isLowerCase(this.getInstanceTextBox().getContents().charAt(0))) {
                    //    party.getLabel().setLabel(getInstanceTextBox().getContents() + ":" + t.getContents());
                    //} else {
                    if(party.getLabel().isValidLabel(":" + t.getContents())) {
                        party.getLabel().setLabel(":" + t.getContents());
                    }
                    //}
                } else {
                    if(party.getLabel().isValidLabel(split[0] + ":" + t.getContents())) {
                        party.getLabel().setLabel(split[0] + ":" + t.getContents());
                    }
                }
            }
            updateList();
            return new UpdateLabelAction(party, party.getLabel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new EmptyAction();
    }

    /**
     * handle an action
     *
     * @param action the action to handle
     */
    @Override
    public void handleAction(Action action) {
        if (action instanceof RemoveInViewsAction) {
            RemoveInViewsAction a = (RemoveInViewsAction) action;
            if (a.getDeletedElements().contains(party)) {
                this.getFrame().close();
            }
        } else if (action instanceof UpdatePartyTypeAction) {
            UpdatePartyTypeAction a = (UpdatePartyTypeAction) action;
            if (a.getOldParty().equals(party)) {
                try {
                    PartyDialogBox partyDialogBox = new PartyDialogBox(this.getPosition(), a.getNewParty(), subwindow);
                    CloseWindowButton closeWindowButton = (CloseWindowButton) subwindow.getFrame().getButton();
                    CloseSubwindowCommand close = (CloseSubwindowCommand) closeWindowButton.getCommand();
                    close.getInteractionController().addSubwindow(partyDialogBox);
                    close.getInteractionController().addToMap(subwindow, partyDialogBox);
                    partyDialogBox.createFrame(new CloseWindowButton(new CloseSubwindowCommand(partyDialogBox, close.getInteractionController())));
                    if (subwindow.getSelected() == a.getOldParty()) {
                        subwindow.setSelected(a.getNewParty());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.getFrame().close();
            }
        }
        if (action instanceof UpdateLabelAction) {
            UpdateLabelAction a = (UpdateLabelAction) action;
            if (a.getElement().equals(party)) {
                //updateFields((Party) a.getElement());
                updateList();
            }
        }
        if(action instanceof UpdateListAction){
            System.out.println("-------------- REACHED UpdateListAction");
            updateList();
        }

    }

    /**
     * update the fields of this dialogbox for the given party
     *
     * @param party the party to change the fields for
     */
    /*private void updateFields(Party party) {
        String[] labels = party.getLabel().getLabel().split(":");
        if (labels.length == 2) {
            instanceTextBox.setContents(labels[0]);
            classTextBox.setContents(labels[1]);
        } else {
            classTextBox.setContents(labels[0]);
        }
    }*/
}
