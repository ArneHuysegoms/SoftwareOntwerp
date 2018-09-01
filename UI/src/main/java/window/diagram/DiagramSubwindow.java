package window.diagram;

import action.*;
import diagram.DiagramElement;
import diagram.label.InvocationMessageLabel;
import diagram.label.Label;
import diagram.label.ResultMessageLabel;
import diagram.message.Message;
import diagram.message.ResultMessage;
import diagram.party.Party;
import exception.UIException;
import exceptions.DomainException;
import facade.DomainFacade;
import uievents.KeyEvent;
import uievents.MouseEvent;
import view.diagram.CommunicationView;
import view.diagram.DiagramView;
import window.IActionHandler;
import window.Subwindow;
import window.WindowLevelCounter;
import window.dialogbox.*;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Set;

/**
 * subwindow that contains a diagram
 */
public class DiagramSubwindow extends Subwindow implements IActionHandler {


    private Label label;
    private DomainFacade facade;
    private String labelContainer = "";

    private DiagramElement selected;

    /**
     * default contructor for window.diagram with default width and height
     *
     * @param pos the position of the window.diagram
     */
    public DiagramSubwindow(Point2D pos) {
        super(pos, WindowLevelCounter.getNextLevel());
        setLabelMode(false);
        setFacade(new DomainFacade());
    }

    /**
     * contructor for window.diagram with default width and height
     *
     * @param pos    the position of the window.diagram
     * @param facade the facade for this window.diagram
     */
    public DiagramSubwindow(Point2D pos, DomainFacade facade) {
        super(pos, WindowLevelCounter.getNextLevel());
        setLabelMode(false);
        setFacade(facade);
    }

    /**
     * @return true if this is currently a sequence diagram
     */
    public boolean isSequenceDiagram() {
        return this.getFacade().activeDiagramIsSequence();
    }

    /**
     * @return true if this is currently a communication diagram
     */
    public boolean isCommunicationDiagram() {
        return this.getFacade().activeDiagramIsCommunication();
    }

    /**
     * @return a copy of the facade
     */
    public DomainFacade getCopyOfFacade() {
        DomainFacade f = new DomainFacade(this.getFacade().getDiagram(), DiagramView.copy(getFacade().getSequenceView()), DiagramView.copy(getFacade().getCommunicationView()));
        if (this.getFacade().getActiveView() instanceof CommunicationView) {
            f.changeActiveDiagram();
        }
        return f;
    }


    /**
     * @return the active label
     */
    public Label getLabel() {
        return label;
    }

    /**
     * sets the active label
     *
     * @param label the new label for this window.diagram
     */
    public void setLabel(Label label) {
        this.label = label;
    }

    /**
     * @return the facade for this window.diagram
     */
    public DomainFacade getFacade() {
        return facade;
    }

    /**
     * sets the facade for this window.diagram
     *
     * @param facade the new facade for this window.diagram
     */
    private void setFacade(DomainFacade facade) {
        this.facade = facade;
    }

    /**
     * @return the text labelcontainer for the active label
     */
    public String getLabelContainer() {
        return labelContainer;
    }

    /**
     * sets the labelcontainer for the active label
     *
     * @param labelContainer the new labelcontainer for this window.diagram
     */
    public void setLabelContainer(String labelContainer) {
        this.labelContainer = labelContainer;
    }

    /**
     * @return the selected element of this window.diagram
     */
    public DiagramElement getSelected() {
        return selected;
    }

    /**
     * @param selected the new selected element for this window.diagram
     */
    public void setSelected(DiagramElement selected) {
        this.selected = selected;
    }

    /**
     * handle the given keyevent accordingly
     *
     * @param keyEvent the keyevent to handle
     * @return an action to be handled higher up
     */
    public Action handleKeyEvent(KeyEvent keyEvent) throws DomainException, UIException {
        if (!labelMode) {
            switch (keyEvent.getKeyEventType()) {
                case TAB:
                    this.getFacade().changeActiveDiagram();
                    break;
                case DEL:
                    return this.deleteElement();
                case CHAR:
                    if (selectedElementIsLabel() && editing) {
                        return this.addCharToLabel(keyEvent.getKeyChar());
                    }
                    break;
                case BACKSPACE:
                    if (selectedElementIsLabel() && editing) {
                        return this.removeLastCharFromLabel();
                    }
                    break;
                case CTRLENTER:
                    return opendialogBox();
                default:
                    break;
            }
        } else if (selectedElementIsLabel()) {
            switch (keyEvent.getKeyEventType()) {
                case CHAR:
                    return this.addCharToLabel(keyEvent.getKeyChar());
                case BACKSPACE:
                    return this.removeLastCharFromLabel();
                case CTRLENTER:
                    return opendialogBox();
                default:
                    break;
            }
        }
        return new EmptyAction();
    }

    /**
     * opens a dialogbox
     *
     * @return an action detailing the handling of this method
     * @throws UIException if illegal modifications are made
     */
    public Action opendialogBox() throws UIException {
        DialogBox dialogBox = null;
        if (selected == null) {
            dialogBox = new DiagramDialogBox(new Point2D.Double(getPosition().getX()+10, getPosition().getY()+10), this);
        } else if (selectedElementIsParty()) {
            Party p = (Party) selected;
            dialogBox = new PartyDialogBox(new Point2D.Double(getPosition().getX()+10, getPosition().getY()+10), p, this);
        } else if (selectedElementIsLabel()) {
            DiagramElement element = this.getFacade().findParentElement((Label) selected);
            if (element instanceof Party) {
                Party p = (Party) element;
                dialogBox = new PartyDialogBox(new Point2D.Double(getPosition().getX()+10, getPosition().getY()+10), p, this);
            } else if (selected instanceof InvocationMessageLabel) {
                dialogBox = new InvocationMessageDialogBox(new Point2D.Double(getPosition().getX()+10, getPosition().getY()+10), (InvocationMessageLabel) selected, this);
            } else if (selected instanceof ResultMessageLabel) {
                dialogBox = new ResultMessageDialogBox(new Point2D.Double(getPosition().getX()+10, getPosition().getY()+10), (ResultMessage) element, this);
            }
        }
        if (dialogBox != null) {
            return new DialogBoxOpenedAction(dialogBox);
        }
        return new EmptyAction();
    }

    /**
     * change the diagram type of this diagramsubwindow
     */
    public void changeActiveDiagram() {
        this.getFacade().changeActiveDiagram();
    }

    /**
     * @return true if the active diagram is a sequencediagram
     */
    public boolean activeDiagamIsSequence() {
        return getFacade().activeDiagramIsSequence();
    }

    /**
     * @return true if the active diagram is a communicationdiagram
     */
    public boolean activeDiagramIsCommunication() {
        return getFacade().activeDiagramIsCommunication();
    }

    /**
     * Reads a mouse event and alters the active diagram based on it
     *
     * @param mouseEvent the MouseEvent that happened in the UI, comes from the InteractrCanvas
     * @return an action to be handled higher up
     */
    public Action handleMouseEvent(MouseEvent mouseEvent) {
        if (!labelMode) {
            switch (mouseEvent.getMouseEventType()) {
                case DRAG:
                    this.setDragging(true);
                    if (selectedElementIsParty()) {
                        Party p = (Party) selected;
                        this.getFacade().changePartyPosition(mouseEvent.getPoint(), p);
                    }
                    break;
                case PRESSED:
                    handleMousePressed(mouseEvent);
                    break;
                case RELEASE:
                    return handleReleaseClick(mouseEvent);
                case LEFTCLICK:
                    handleLeftClick(mouseEvent);
                    break;
                case LEFTDOUBLECLICK:
                    if (selectedElementIsParty()) {
                        Party oldParty = (Party) selected;
                        Party newParty = this.getFacade().changePartyType(oldParty);
                        selected = newParty;
                        return new UpdatePartyTypeAction(oldParty, newParty);
                    }
                    if (this.selected == null) {
                        Party newParty = this.getFacade().addNewParty(mouseEvent.getPoint());
                        selected = newParty.getLabel();
                        startEditingLabel();
                        editing = true;
                        return new AddNewPartyToViewsAction(newParty, mouseEvent.getPoint());
                    }
                    break;
                default:
                    break;
            }
        }
        return new EmptyAction();
    }

    /**
     * handle a left click on the UI
     *
     * @param mouseEvent the MouseEvent containing the information of the event
     * @return an action detailing that the messages have to be added elsewhere
     */
    private Action handleReleaseClick(MouseEvent mouseEvent) {
        this.setDragging(false);
        if (selectedElementIsMessageStart()) {
            DiagramView.MessageStart ms = (DiagramView.MessageStart) selected;
            List<Message> newMessages = this.getFacade().addNewMessage(mouseEvent.getPoint(), ms);
            try {
                selected = newMessages.get(0).getLabel();
                startEditingLabel();
                editing = true;
                return new AddNewMessagesInViewsAction(newMessages);
            }catch (NullPointerException e){

            }
        }
        return new EmptyAction();
    }

    /**
     * handles a leftclick for the given mouseEvent
     *
     * @param mouseEvent the mouseEvent to handle
     */
    private void handleLeftClick(MouseEvent mouseEvent) {

    }

    /**
     * @return true if the selected element is a party, false otherwise
     */
    private boolean selectedElementIsParty() {
        return selected instanceof Party;
    }

    /**
     * @return true if the selected element is a party, false otherwise
     */
    private boolean selectedElementIsLabel() {
        return selected instanceof Label;
    }

    /**
     * @return true if the selected element is a party, false otherwise
     */
    private boolean selectedElementIsMessageStart() {
        return selected instanceof DiagramView.MessageStart;
    }

    /**
     * Handles MouseEvents of type MouseEvent.Pressed
     *
     * @param mouseEvent the event to handle
     */
    private void handleMousePressed(MouseEvent mouseEvent) {
        DiagramElement oldSelected = this.selected;
        DiagramElement newSelected = this.getFacade().findSelectedElement(mouseEvent.getPoint());
        if (oldSelected != null && oldSelected.equals(newSelected) && oldSelected instanceof Label) {
            editing = true;
            selected = newSelected;
            this.startEditingLabel();
        } else {
            editing = false;
            stopEditingLabel();
            if (newSelected instanceof Label) {
                labelContainer = ((Label) newSelected).getLabel() + "I";
            }
            selected = newSelected;
        }
    }

    /**
     * start editing a label in the window.diagram
     */
    private void startEditingLabel() {
        labelMode = true;
        Label labelInEdit = (Label) selected;
        labelContainer = labelInEdit.getLabel() + "I";
        if(labelInEdit instanceof InvocationMessageLabel){
            InvocationMessageLabel invocationMessageLabel = (InvocationMessageLabel) labelInEdit;
            labelContainer = invocationMessageLabel.toString() + "I";
        }
    }

    /**
     * delete the elements in the repos of the other subwindows
     *
     * @return an action detailing what needs to happen in other subwindows
     */
    private Action deleteElement() {
        if (selectedElementIsLabel()) {
            Label l = (Label) selected;
            Set<DiagramElement> deletedElements = facade.deleteElementByLabel(l);
            stopEditingLabel();
            selected = null;
            return new RemoveInViewsAction(deletedElements);
        }
        return new EmptyAction();
    }

    /**
     * start editing a label in the window.diagram
     */
    public void stopEditingLabel() {
        labelMode = false;
        labelContainer = "";
    }

    /**
     * adds the given char to the active label
     *
     * @param c the char to add
     * @return an action detailing what needs to happen in other subwindows
     */
    private Action addCharToLabel(char c) throws DomainException {
        String l = "";
        if (labelContainer.equals("")) {
            l = "";
        } else {
            l = labelContainer.substring(0, labelContainer.length() - 1);
        }
        l += c;
        l += "I";
        labelContainer = l;
        return handleChangeInLabel();
    }

    /**
     * removes the last char from the active label
     *
     * @return an action detailing what needs to happen in other subwindows
     */
    private Action removeLastCharFromLabel() throws DomainException {
        if (labelContainer.length() > 1) {
            String l = labelContainer.substring(0, getLabelContainer().length() - 2);
            l += "I";
            labelContainer = l;
            labelMode = !checkIfValidLable();
            return handleChangeInLabel();
        }
        return new EmptyAction();
    }

    /**
     * handle a change in the active label
     *
     * @return an action detailing what needs to happen in other subwindows
     */
    private Action handleChangeInLabel() throws DomainException {
        if (selected instanceof InvocationMessageLabel) {
            return handleInvocationMessageLabelChange();
        } else if (checkIfValidLable()) {
            labelMode = false;
            Label selectedLabel = (Label) selected;
            selectedLabel.setLabel(labelContainer.substring(0, getLabelContainer().length() - 1));
            DiagramElement diagramElement = this.getFacade().findParentElement(selectedLabel);
            return new UpdateLabelAction(diagramElement, selectedLabel);
        } else {
            labelMode = true;
            return new EmptyAction();
        }
    }

    /**
     * handles the change in a invocationMessage label
     *
     * @return an action detailing what happenend by execting this method
     * @throws DomainException if illegal modifications are made
     */
    private Action handleInvocationMessageLabelChange() throws DomainException {
        InvocationMessageLabel inv = (InvocationMessageLabel) selected;
        String toParse = labelContainer.substring(0, getLabelContainer().length() - 1);
        if (inv.isValidCompleteLabel(toParse)) {
            labelMode = false;
            inv.setCompleteLabel(toParse);
            DiagramElement diagramElement = this.getFacade().findParentElement(inv);
            return new UpdateLabelAction(diagramElement, inv);
        } else {
            labelMode = true;
            return new EmptyAction();
        }
    }

    /**
     * check if the active label is valid
     *
     * @return true if the label is valid
     */
    public boolean checkIfValidLable() {
        if (getLabelContainer().equals("") || getLabelContainer().equals("I")) {
            return false;
        }
        if (selectedElementIsLabel() && labelContainer.length() > 0) {
            Label l = (Label) selected;
            if(l instanceof InvocationMessageLabel){
                return ((InvocationMessageLabel) l).isValidCompleteLabel(getLabelContainer().substring(0, getLabelContainer().length() - 1));
            }
            return l.isValidLabel(getLabelContainer().substring(0, getLabelContainer().length() - 1)) && !getLabelContainer().equals("");
        }
        return true;
    }


    /**
     * handles the given action
     *
     * @param action the action to handle
     */
    @Override
    public void handleAction(Action action) {
        action.performAction(this);
    }

}
