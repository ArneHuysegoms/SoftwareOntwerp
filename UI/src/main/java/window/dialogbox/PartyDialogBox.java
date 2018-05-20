package window.dialogbox;

import action.*;
import command.changeType.ChangeToActorCommand;
import command.changeType.ChangeToObjectCommand;
import diagram.party.Party;
import exception.UIException;
import exceptions.DomainException;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;
import window.elements.RadioButton;
import window.elements.textbox.ClassTextBox;
import window.elements.textbox.InstanceTextBox;
import window.elements.textbox.TextBox;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class PartyDialogBox extends DialogBox {

    public static final int WIDTH = 150;
    public static final int HEIGHT = 200;

    public static final String TOOBJECT_DESPCRIPTION = "Object";
    public static final String TOACTOR_DESCRIPTION = "Actor";

    public static final String INSTANCE_DESCRIPTION = "Instance";
    public static final String CLASS_DESCRIPTION = "Class";

    private RadioButton toActor;
    private RadioButton toObject;

    private TextBox instanceTextBox;
    private TextBox classTextBox;

    private DialogboxElement selected;

    private List<DialogboxElement> elementList;

    private Party party;

    private DiagramSubwindow subwindow;

    public PartyDialogBox(Point2D pos, Party party, DiagramSubwindow subwindow) throws UIException {
        super(pos);
        this.setParty(party);
        toActor = new RadioButton(new ChangeToActorCommand(subwindow, party), new Point2D.Double(10, 20), TOACTOR_DESCRIPTION);
        toObject = new RadioButton(new ChangeToObjectCommand(subwindow, party), new Point2D.Double(85, 20), TOOBJECT_DESPCRIPTION);
        instanceTextBox = new InstanceTextBox(new Point2D.Double(10, 50), INSTANCE_DESCRIPTION);
        classTextBox = new ClassTextBox(new Point2D.Double(10, 75), CLASS_DESCRIPTION);
        elementList = new ArrayList<>();
        elementList.add(toActor);
        elementList.add(toObject);
        elementList.add(instanceTextBox);
        elementList.add(classTextBox);
        selected = toActor;

        this.subwindow = subwindow;

        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        updateFields(party);
    }

    public RadioButton getToActor() {
        return toActor;
    }

    public RadioButton getToObject() {
        return toObject;
    }

    public TextBox getInstanceTextBox() {
        return instanceTextBox;
    }

    public TextBox getClassTextBox() {
        return classTextBox;
    }

    public DialogboxElement getSelected() {
        return selected;
    }

    public List<DialogboxElement> getElementList() {
        return elementList;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public DiagramSubwindow getSubwindow() {
        return subwindow;
    }

    @Override
    public Action handleMouseEvent(MouseEvent mouseEvent) {
        switch (mouseEvent.getMouseEventType()) {
            case PRESSED:
                return handleMousePress(mouseEvent);
        }
        return new EmptyAction();
    }

    @Override
    public Action handleKeyEvent(KeyEvent keyEvent) {
        try {
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
            }
        }
        catch (DomainException e){
            e.printStackTrace();
        }
        return new EmptyAction();
    }

    private Action handleMousePress(MouseEvent mouseEvent) {
        if (toActor.isClicked(mouseEvent.getPoint())) {
            selected = toActor;
            return toActor.performAction();
        } else if (toObject.isClicked(mouseEvent.getPoint())) {
            selected = toObject;
            return toObject.performAction();
        } else if (instanceTextBox.isClicked(mouseEvent.getPoint())) {
            selected = instanceTextBox;
        } else if (classTextBox.isClicked(mouseEvent.getPoint())) {
            selected = classTextBox;
        }
        return new EmptyAction();
    }

    private Action handleBackSpace() throws DomainException{
        if (selected instanceof TextBox) {
            TextBox t = (TextBox) selected;
            t.deleteLastCharFromContents();
            if (t.hasValidContents()) {
                return changePartyLabel();
            }
        }
        return new EmptyAction();
    }

    private Action handleChar(KeyEvent keyEvent) throws DomainException{
        if (selected instanceof TextBox) {
            TextBox t = (TextBox) selected;
            t.addCharToContents(keyEvent.getKeyChar());
            if (t.hasValidContents()) {
                return changePartyLabel();
            }
        }
        return new EmptyAction();
    }

    private void cycleSelectedElement() {
        int oldIndex = elementList.indexOf(selected);
        selected = elementList.get((oldIndex + 1) % 4);
    }

    private Action handleSpace() {
        if (selected instanceof RadioButton) {
            return ((RadioButton) selected).performAction();
        }
        return new EmptyAction();
    }

    private Action changePartyLabel() {
        try {
            if (selected instanceof InstanceTextBox) {
                TextBox t = (TextBox) selected;
                String oldLabel = party.getLabel().getLabel();
                String[] split = oldLabel.split(":");
                if (split.length == 1) {
                    party.getLabel().setLabel(t.getContents() + ":" + split[0]);
                } else {
                    party.getLabel().setLabel(t.getContents() + ":" + split[1]);
                }
            } else {
                TextBox t = (TextBox) selected;
                String oldLabel = party.getLabel().getLabel();
                String[] split = oldLabel.split(":");
                if (split.length == 1) {
                    if (!this.getInstanceTextBox().getContents().isEmpty() && Character.isLowerCase(this.getInstanceTextBox().getContents().charAt(0))) {
                        party.getLabel().setLabel(getInstanceTextBox().getContents() + ":" + t.getContents());
                    } else {
                        party.getLabel().setLabel(":" + t.getContents());
                    }
                } else {
                    party.getLabel().setLabel(split[0] + " :" + t.getContents());
                }
            }
            return new UpdateLabelContainersAction(party.getLabel());
        }
        catch (Exception e){

        }
        return new EmptyAction();
    }

    @Override
    public void handleAction(Action action) {
        if(action instanceof RemoveInReposAction) {
            RemoveInReposAction a = (RemoveInReposAction) action;
            if(a.getDeletedElements().contains(party)){
                this.getFrame().close();
            }
        }
        else if(action instanceof UpdatePartyTypeAction){
            UpdatePartyTypeAction a = (UpdatePartyTypeAction) action;
            if(a.getOldParty().equals(party)){
                this.getFrame().close();
                subwindow.setSelected(a.getNewParty());
                try {
                    subwindow.opendialogBox();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        if(action instanceof UpdateLabelAction){
            UpdateLabelAction a = (UpdateLabelAction) action;
            if(a.getElement().equals(party)){
                updateFields((Party) a.getElement());
            }
        }
    }

    private void updateFields(Party party) {
        String[] labels = party.getLabel().getLabel().split(":");
        if(labels.length == 2){
            instanceTextBox.setContents(labels[0]);
            classTextBox.setContents(labels[1]);
        }
        else{
            classTextBox.setContents(labels[0]);
        }
    }
}
