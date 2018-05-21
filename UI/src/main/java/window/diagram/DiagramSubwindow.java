package window.diagram;

import action.*;
import diagram.DiagramElement;
import diagram.label.InvocationMessageLabel;
import diagram.label.Label;
import diagram.message.Message;
import diagram.message.ResultMessage;
import diagram.party.Party;
import exception.UIException;
import exceptions.DomainException;
import facade.DomainFacade;
import view.diagram.CommunicationView;
import view.diagram.DiagramView;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.IActionHandler;
import window.Subwindow;
import window.WindowLevelCounter;
import window.dialogbox.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DiagramSubwindow extends Subwindow implements IActionHandler {


    private Label label;
    private DomainFacade facade;
    private String labelContainer = "";

    private DiagramElement selected;

    //private List<DialogBox> dialogBoxlist;

    //private DialogBox activeDialogBox;

    /**
     * default contructor for window.diagram with default width and height
     *
     * @param pos the position of the window.diagram
     */
    public DiagramSubwindow(Point2D pos) {
        super(pos, WindowLevelCounter.getNextLevel());
        setLabelMode(false);
        setFacade(new DomainFacade());
        //dialogBoxlist = new ArrayList<>();
    }

    /**
     * contructor for window.diagram with default width and height
     *
     * @param pos the position of the window.diagram
     * @param facade the facade for this window.diagram
     */
    public DiagramSubwindow(Point2D pos, DomainFacade facade) {
        super(pos, WindowLevelCounter.getNextLevel());
        setLabelMode(false);
        setFacade(facade);
        //dialogBoxlist = new ArrayList<>();
    }

    /*public void addDialogBox(DialogBox dialogBox){
        this.dialogBoxlist.add(dialogBox);
    }*/

    /**
     *
     * @return true if this is currently a sequence diagram
     */
    public boolean isSequenceDiagram(){
        return this.getFacade().activeDiagramIsSequence();
    }

    /**
     *
     * @return true if this is currently a communication diagram
     */
    public boolean isCommunicationDiagram(){
        return this.getFacade().activeDiagramIsCommunication();
    }

    /**
     * @return a copy of the facade
     */
    public DomainFacade getCopyOfFacade() {
        DomainFacade f = new DomainFacade(this.getFacade().getDiagram(), DiagramView.copy(getFacade().getSequenceView()), DiagramView.copy(getFacade().getCommunicationView()));
        if(this.getFacade().getActiveView() instanceof CommunicationView){
            f.changeActiveDiagram();
        }
        return f;
    }

    /**
     * update the container with the label
     *
     * @param c the new char for the label
     */
    public void updateLabelContainer(char c) {
        setLabelContainer(labelContainer + c);
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
     *
     * @return the selected element of this window.diagram
     */
    public DiagramElement getSelected() {
        return selected;
    }

    /**
     *
     * @param selected the new selected element for this window.diagram
     */
    public void setSelected(DiagramElement selected) {
        this.selected = selected;
    }

    /**
     * handle the given keyevent accordingly
     *
     * @param keyEvent the keyevent to handle
     *
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
                    opendialogBox();
                    break;
                default:
                    break;
            }
        } else if (selectedElementIsLabel()) {
            switch (keyEvent.getKeyEventType()) {
                case CHAR:
                    return this.addCharToLabel(keyEvent.getKeyChar());
                case BACKSPACE:
                    return this.removeLastCharFromLabel();
                default:
                    break;
            }
        }
        return new EmptyAction();
    }

    /*public void removeDialogBox(DialogBox dialogBox){
        this.dialogBoxlist.remove(dialogBox);
        if(activeDialogBox == dialogBox){
            this.activeDialogBox = null;
        }
    }*/

    public Action opendialogBox() throws UIException {
        DialogBox dialogBox = null;
        if(selected == null){
            dialogBox = new DiagramDialogBox(new Point2D.Double(100, 100), this);
            //closeOldDialogBoxes(dialogBox);
            //dialogBoxlist.add(dialogBox);
            //activeDialogBox = dialogBox;
        }
        else if(selectedElementIsParty()){
            Party p = (Party) selected;
            dialogBox = new PartyDialogBox(new Point2D.Double(100,100), p, this);
        }
        else if(selectedElementIsLabel()){
            DiagramElement element = this.getFacade().findParentElement((Label) selected);
            if(element instanceof Party){
                Party p = (Party) element;
                dialogBox = new PartyDialogBox(new Point2D.Double(100,100), p, this);
            }
            else if(selected instanceof InvocationMessageLabel){
                dialogBox = new InvocationMessageDialogBox(new Point2D.Double(100, 100), (InvocationMessageLabel) selected, this);
                //closeOldDialogBoxes(dialogBox);
                //dialogBoxlist.add(dialogBox);
                //activeDialogBox = dialogBox;
            }
            else if(element instanceof ResultMessage){
                dialogBox = new ResultMessageDialogBox(new Point2D.Double(100, 100), (ResultMessage) element , this);
                //closeOldDialogBoxes(resultMessageDialogBox);
                //dialogBoxlist.add(resultMessageDialogBox);
                //activeDialogBox = resultMessageDialogBox;
            }
        }
        if(dialogBox != null) {
            return new DialogBoxOpenedAction(dialogBox);
        }
        return new EmptyAction();
    }

    /*private Action openPartyDialogBox(Party p) throws UIException{
        DialogBox partyDialogBox = new PartyDialogBox(new Point2D.Double(100,100), p, this);
        //closeOldDialogBoxes(partyDialogBox);
        //closeOtherPartyDialogBoxes(partyDialogBox);
        //dialogBoxlist.add(partyDialogBox);
        //activeDialogBox = partyDialogBox;
        return new DialogBoxOpenedAction(partyDialogBox);
    }

    private void closeOldDialogBoxes(DialogBox newBox){
        List<DialogBox> olds = dialogBoxlist.stream()
                                    .filter(d -> d.getClass() != newBox.getClass())
                                    .collect(Collectors.toList());
        dialogBoxlist.removeAll(olds);
    }

    private void closeOtherPartyDialogBoxes(PartyDialogBox dialogBox){
        List<DialogBox> olds = dialogBoxlist.stream()
                .filter(d -> d.getClass() != dialogBox.getClass())
                .map(d -> (PartyDialogBox) d)
                .filter(d -> d.getParty() != dialogBox.getParty())
                .collect(Collectors.toList());
        dialogBoxlist.removeAll(olds);
    }*/

    public void changeActiveDiagram(){
        this.getFacade().changeActiveDiagram();
    }

    public boolean activeDiagamIsSequence(){
        return getFacade().activeDiagramIsSequence();
    }

    public boolean activeDiagramIsCommunication(){
        return getFacade().activeDiagramIsCommunication();
    }

    /**
     * Reads a mouse event and alters the active diagram based on it
     *
     * @param mouseEvent the MouseEvent that happened in the UI, comes from the InteractrCanvas
     *
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
                        //mediator.updatePartyTypeInOtherSubwindows(oldParty, newParty, this);
                    }
                    if (this.selected == null) {
                        Party newParty = this.getFacade().addNewParty(mouseEvent.getPoint());
                        selected = newParty.getLabel();
                        startEditingLabel();
                        editing = true;
                        return new AddNewPartyToReposAction(newParty, mouseEvent.getPoint());
                        //mediator.addNewPartyToOtherSubwindowRepos(newParty, mouseEvent.getPoint(), this);
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
     *
     * @return an action detailing that the messages have to be added elsewhere
     */
    private Action handleReleaseClick(MouseEvent mouseEvent) {
        this.setDragging(false);
        if (selectedElementIsMessageStart()) {
            DiagramView.MessageStart ms = (DiagramView.MessageStart) selected;
            List<Message> newMessages = this.getFacade().addNewMessage(mouseEvent.getPoint(), ms);
            selected = newMessages.get(0).getLabel();
            startEditingLabel();
            editing = true;
            //TODO open dialogboxes?
            return new AddNewMessagesInRepos(newMessages);
            //mediator.addNewMessagesToOtherSubwindowRepos(newMessages, this);
        }
        return new EmptyAction();
    }

    /**
     * handles a leftclick for the given mouseEvent
     * @param mouseEvent the mouseEvent to handle
     */
    private void handleLeftClick(MouseEvent mouseEvent) {

    }

    /**
     *
     * @return true if the selected element is a party, false otherwise
     */
    private boolean selectedElementIsParty() {
        return selected instanceof Party;
    }

    /**
     *
     * @return true if the selected element is a party, false otherwise
     */
    private boolean selectedElementIsLabel() {
        return selected instanceof Label;
    }

    /**
     *
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
        } 
        else{
            editing = false;
            stopEditingLabel();
            if(newSelected instanceof Label){
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
            //mediator.removeInReposInOtherSubwindows(deletedElements, this);
            stopEditingLabel();
            selected = null;
            return new RemoveInReposAction(deletedElements);
        }
        return new EmptyAction();
    }

    /**
     * start editing a label in the window.diagram
     */
    public void stopEditingLabel() {
        labelMode = false;
        labelContainer = "";
        //selected = null;
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
     * @return an action detailing what needs to happen in other subwindows
     */
    private Action handleChangeInLabel() throws DomainException {
        if (checkIfValidLable()) {
            labelMode = false;
            Label selectedLabel = (Label) selected;
            selectedLabel.setLabel(labelContainer.substring(0, getLabelContainer().length() - 1));
            //mediator.updateLabelContainers(selectedLabel, this);
            DiagramElement diagramElement = this.getFacade().findParentElement(selectedLabel);
            return new UpdateLabelAction(diagramElement, selectedLabel);
            //return new UpdateLabelContainersAction(selectedLabel);
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
            return l.isValidLabel(getLabelContainer().substring(0, getLabelContainer().length() - 1)) && !getLabelContainer().equals("");
        }
        return true;
    }


    @Override
    public void handleAction(Action action) {
        action.performAction(this);
    }


    /*public Action updateDialogBoxes(Action action) {
        for (DialogBox dialogBox : dialogBoxlist) {
            dialogBox.handleAction(action);
        }
        return action;
    }

    public List<DialogBox> getDialogBoxlist(){
        return dialogBoxlist;
    }*/
}
