package window.dialogbox;

import action.*;
import command.changeType.PartyCommand.ChangeToActorCommand;
import command.changeType.PartyCommand.ChangeToObjectCommand;
import command.closeWindow.CloseSubwindowCommand;
import diagram.party.Party;
import exception.UIException;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;
import window.elements.radiobutton.PartyRadioButton;
import window.elements.radiobutton.RadioButton;
import window.elements.button.CloseWindowButton;
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

    public static final String TOOBJECT_DESPCRIPTION = "Object";
    public static final String TOACTOR_DESCRIPTION = "Actor";

    public static final String INSTANCE_DESCRIPTION = "Instance";
    public static final String CLASS_DESCRIPTION = "Class";

    //private RadioButton toActor;
    //private RadioButton toObject;

    //private TextBox instanceTextBox;
    //private TextBox classTextBox;

    //private DialogboxElement selected;

    private List<DialogboxElement> elementList;

    private Party party;

    private DiagramSubwindow subwindow;

    public static ArrayList<DialogboxElement> PARTYBOXLIST;

    //private int selectedindex;

    static {
        try {
            PARTYBOXLIST = new ArrayList<DialogboxElement>(Arrays.asList(new PartyRadioButton(new ChangeToActorCommand(null, null), new Point2D.Double(10, 30), TOACTOR_DESCRIPTION),
                        new PartyRadioButton(new ChangeToObjectCommand(null, null), new Point2D.Double(85, 30), TOOBJECT_DESPCRIPTION),
                        new InstanceTextBox(new Point2D.Double(10, 60), INSTANCE_DESCRIPTION),
                        new ClassTextBox(new Point2D.Double(10, 85), CLASS_DESCRIPTION)));
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
        /*toActor = new RadioButton(new ChangeToActorCommand(subwindow, party), new Point2D.Double(10, 30), TOACTOR_DESCRIPTION);
        toObject = new RadioButton(new ChangeToObjectCommand(subwindow, party), new Point2D.Double(85, 30), TOOBJECT_DESPCRIPTION);
        instanceTextBox = new InstanceTextBox(new Point2D.Double(10, 60), INSTANCE_DESCRIPTION);
        classTextBox = new ClassTextBox(new Point2D.Double(10, 85), CLASS_DESCRIPTION);*/
        elementList = new ArrayList<>();
        /*elementList.add(toActor);
        elementList.add(toObject);
        elementList.add(instanceTextBox);
        elementList.add(classTextBox);
        selected = toActor;
*/
        this.subwindow = subwindow;

        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        //updateFields(party);
        updateList();
        this.selectedindex = 0;
        selected = this.elementList.get(getSelectedindex());
    }

    public void updateList(){
        for (DialogboxElement e : PARTYBOXLIST){
            DialogboxElement clone = e.clone();
            clone.update(subwindow,party);
            elementList.add(clone);
        }
    }

    /*public int getSelectedindex() {
        return selectedindex;
    }*/

    /**
     * @return the radiobutton for changing to actors
     */
    /*public RadioButton getToActor() {
        return toActor;
    }*/

    /**
     * @return the radiobutton for changing to objects
     */
    /*public RadioButton getToObject() {
        return toObject;
    }

    /**
     * @return the textbox for the instance string
     */
    /*public TextBox getInstanceTextBox() {
        return instanceTextBox;
    }

    /**
     * @return the textbox for changing the class string
     */
    /*public TextBox getClassTextBox() {
        return classTextBox;
    }

    /**
     * @return the currently selected dialogboxelement
     */
    /*public DialogboxElement getSelected() {
        return selected;
    }

    /**
     * @return all dialogbox elements in this dialogbox
     */
    public List<DialogboxElement> getElementList() {
        return elementList;
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

    /**
     * handle a mouse event
     *
     * @param mouseEvent the mouseEvent to handle
     * @return an action detailing the outcome of the handling
     */
    @Override
    public Action handleMouseEvent(MouseEvent mouseEvent) {
        switch (mouseEvent.getMouseEventType()) {
            case LEFTDOUBLECLICK:
                if(designerMode){
                    for(DialogboxElement ele:PARTYBOXLIST){
                        if(ele.isClicked(mouseEvent.getPoint())){
                            ele.clone(); //TODO
                        }
                    }
                    System.out.println("FUCKFFLDSJMKLJKLMSDJMLSDFJMLJLKFDMLKDFD");
                }
                break;
            case PRESSED:
                if(!designerMode) {
                    return handleMousePress(mouseEvent);
                }
        }
        return new EmptyAction();
    }

    /**
     * handle a keyEvent
     *
     * @param keyEvent the keyEvent to handle
     * @return an action detailing the outcome of the handling
     */
    @Override
    public Action handleKeyEvent(KeyEvent keyEvent) {
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
                System.out.println("DESIGNER MODE ON");
                break;
            case ENTER:
                if(designerMode){
                    setDesignerMode(false);
                    System.out.println("DESIGNER MODE OFF");
                }
                break;
        }
        return new EmptyAction();
    }

    /**
     * handle a mouse press event
     *
     * @param mouseEvent the mouse event to handle
     * @return an action detailing the outcome of the handling
     */
    private Action handleMousePress(MouseEvent mouseEvent) {
        if (toActor.isClicked(mouseEvent.getPoint())) {
            selected = toActor;
            Action action = toActor.performAction();
            handleAction(action);
            return action;
        } else if (toObject.isClicked(mouseEvent.getPoint())) {
            selected = toObject;
            Action action = toObject.performAction();
            handleAction(action);
            return action;
        } else if (instanceTextBox.isClicked(mouseEvent.getPoint())) {
            selected = instanceTextBox;
        } else if (classTextBox.isClicked(mouseEvent.getPoint())) {
            selected = classTextBox;
        }
        return new EmptyAction();
    }

    /**
     * handle the backspace event
     *
     * @return an action detailing the outcome of the handling
     */
    private Action handleBackSpace() {
        if (selected instanceof TextBox) {
            TextBox t = (TextBox) selected;
            t.deleteLastCharFromContents();
            if (t.hasValidContents()) {
                return changePartyLabel();
            }
        }
        return new EmptyAction();
    }

    /**
     * handles a char keyEvent
     *
     * @param keyEvent the keyEvent with the char
     * @return an action detailing the outcome of the handling
     */
    private Action handleChar(KeyEvent keyEvent) {
        if (selected instanceof TextBox) {
            TextBox t = (TextBox) selected;
            t.addCharToContents(keyEvent.getKeyChar());
            if (t.hasValidContents()) {
                return changePartyLabel();
            }
        }
        return new EmptyAction();
    }

    /**
     * cycle the selected element
     */
    private void cycleSelectedElement() {
        /*int oldIndex = elementList.indexOf(selected);
        selected = elementList.get((oldIndex + 1) % 4);*/
        selectedindex++;
        if(getSelectedindex() < elementList.size()){
            selected = elementList.get(getSelectedindex());
        }
        else{
            //TODO what if list size is 0?
            selectedindex = 0;
            selected = elementList.get(getSelectedindex());
        }
    }

    /**
     * handle the space event
     *
     * @return an action detailing the outcome of the handling
     */
    private Action handleSpace() {
        if (selected instanceof RadioButton) {
            if (selected == toActor) {
                Action action = toActor.performAction();
                handleAction(action);
                return action;
            } else if (selected == toObject) {
                Action action = toObject.performAction();
                handleAction(action);
                return action;
            }
        }
        return new EmptyAction();
    }

    /**
     * handle changing the party label
     *
     * @return an action detailing the outcome of the handling
     */
    private Action changePartyLabel() {
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
                    if (!this.getInstanceTextBox().getContents().isEmpty() && Character.isLowerCase(this.getInstanceTextBox().getContents().charAt(0))) {
                        party.getLabel().setLabel(getInstanceTextBox().getContents() + ":" + t.getContents());
                    } else {
                        if(party.getLabel().isValidLabel(":" + t.getContents())) {
                            party.getLabel().setLabel(":" + t.getContents());
                        }
                    }
                } else {
                    if(party.getLabel().isValidLabel(split[0] + ":" + t.getContents())) {
                        party.getLabel().setLabel(split[0] + ":" + t.getContents());
                    }
                }
            }
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
                updateFields((Party) a.getElement());
            }
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
