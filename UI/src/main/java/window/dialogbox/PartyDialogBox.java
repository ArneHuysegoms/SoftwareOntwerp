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

    private Party party;

    private DiagramSubwindow subwindow;

    public static ArrayList<DialogboxElement> PARTYBOXLIST;

    /**
     * initiate static list
     */
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
        updateList();
        this.selectedindex = 0;
        if (elementList.size() > 0) {

            selected = this.elementList.get(getSelectedindex());
        }
    }

    /**
     * syncs the static list with the private list
     * also sets the selected element
     */
    public void updateList() {
        elementList = new ArrayList<>();
        for (DialogboxElement e : PARTYBOXLIST) {
            DialogboxElement clone = e.clone();
            clone.update(subwindow, party);
            elementList.add(clone);
        }

        if (elementList.size() == 0) {
            selected = null;
        } else if (selectedindex > elementList.size() - 1) {
            selectedindex = 0;
            selected = this.elementList.get(selectedindex);
        } else {
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
    public List<DialogboxElement> getStaticList() {
        return PARTYBOXLIST;
    }

    /**
     * handle the backspace event
     *
     * @return an action detailing the outcome of the handling
     */
    @Override
    protected Action handleBackSpace() {
        if (!designerMode) {
            selected.deleteLastCharFromContents();
            if (selected.hasValidContents()) {
                return changePartyLabel(selected.getContents());
            }
            return new EmptyAction();
        } else {
            DialogboxElement d = PARTYBOXLIST.get(selectedindex);
            d.deleteCharFromDescription();
            if (!d.isValidDescription()) {
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
        if (!designerMode) {
            selected.addCharToContents(keyEvent.getKeyChar());
            if (selected.hasValidContents()) {
                return changePartyLabel(selected.getContents());
            }
            return new EmptyAction();
        } else {
            if (selected != null) {
                DialogboxElement d = PARTYBOXLIST.get(selectedindex);
                d.addCharToDescription(keyEvent.getKeyChar());
                if (d.isValidDescription()) {
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
                    if (party.getLabel().isValidLabel(t.getContents() + ":" + split[0])) {
                        party.getLabel().setLabel(t.getContents() + ":" + split[0]);
                    }
                } else {
                    if (party.getLabel().isValidLabel(t.getContents() + ":" + split[1])) {
                        party.getLabel().setLabel(t.getContents() + ":" + split[1]);
                    }
                }
            } else {
                TextBox t = (TextBox) selected;
                String oldLabel = party.getLabel().getLabel();
                String[] split = oldLabel.split(":");
                if (split.length == 1) {
                    if (party.getLabel().isValidLabel(":" + t.getContents())) {
                        party.getLabel().setLabel(":" + t.getContents());
                    }
                } else {
                    if (party.getLabel().isValidLabel(split[0] + ":" + t.getContents())) {
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
                    partyDialogBox.selectedindex = this.selectedindex;
                    partyDialogBox.selected = this.selected;
                    if (this.designerMode) {
                        partyDialogBox.setDesignerMode(true);
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
                updateList();
            }
        }
        if (action instanceof UpdateListAction) {
            updateList();
        }

    }

}
