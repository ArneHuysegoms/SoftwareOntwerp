package window.diagram;

import action.Action;
import action.EmptyAction;
import diagram.DiagramElement;
import diagram.label.InvocationMessageLabel;
import diagram.label.Label;
import diagram.message.InvocationMessage;
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

    private List<DialogBox> dialogBoxlist;

    //TODO
    private DialogBox active;

    /**
     * default contructor for subwindow with default width and height
     *
     * @param pos the position of the subwindow
     */
    public DiagramSubwindow(Point2D pos) {
        super(pos, WindowLevelCounter.getNextLevel());
        setLabelMode(false);
        setFacade(new DomainFacade());
        dialogBoxlist = new ArrayList<>();
    }

    /**
     * contructor for subwindow with default width and height
     *
     * @param pos the position of the subwindow
     * @param facade the facade for this subwindow
     */
    public DiagramSubwindow(Point2D pos, DomainFacade facade) {
        super(pos, WindowLevelCounter.getNextLevel());
        setLabelMode(false);
        setFacade(facade);
        dialogBoxlist = new ArrayList<>();
    }

    /**
     * @return a copy of the facade
     */
    public DomainFacade getCopyOfFacade() {
        DomainFacade f = new DomainFacade(this.getFacade().getDiagram(), DiagramView.copy(getFacade().getSequenceRepo()), DiagramView.copy(getFacade().getCommunicationRepo()));
        if(this.getFacade().getActiveRepo() instanceof CommunicationView){
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
     * @param label the new label for this subwindow
     */
    public void setLabel(Label label) {
        this.label = label;
    }

    /**
     * @return the facade for this subwindow
     */
    public DomainFacade getFacade() {
        return facade;
    }

    /**
     * sets the facade for this subwindow
     *
     * @param facade the new facade for this subwindow
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
     * @param labelContainer the new labelcontainer for this subwindow
     */
    public void setLabelContainer(String labelContainer) {
        this.labelContainer = labelContainer;
    }

    /**
     *
     * @return the selected element of this subwindow
     */
    public DiagramElement getSelected() {
        return selected;
    }

    /**
     *
     * @param selected the new selected element for this subwindow
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
    //TODO update dialogBoxes
    public Action handleKeyEvent(KeyEvent keyEvent) throws DomainException, UIException {
        if (!labelMode) {
            switch (keyEvent.getKeyEventType()) {
                case TAB:
                    this.getFacade().changeActiveDiagram();
                    break;
                case DEL:
                    this.deleteElement();
                    break;
                case CHAR:
                    if (selectedElementIsLabel() && editing) {
                        this.addCharToLabel(keyEvent.getKeyChar());
                    }
                    break;
                case BACKSPACE:
                    if (selectedElementIsLabel() && editing) {
                        this.removeLastCharFromLabel();
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
                    this.addCharToLabel(keyEvent.getKeyChar());
                    break;
                case BACKSPACE:
                    this.removeLastCharFromLabel();
                    break;
                default:
                    break;
            }
        }
        //TODO replace
        return new EmptyAction();
    }

    public void opendialogBox() throws UIException {
        if(selected == null){
            DiagramDialogBox diagramBox = new DiagramDialogBox(new Point2D.Double(100, 100), this);
            closeOldDialogBoxes(diagramBox);
            dialogBoxlist.add(diagramBox);
        }
        else if(selectedElementIsParty()){
            Party p = (Party) selected;
            openPartyDialogBox(p);
        }
        else if(selectedElementIsLabel()){
            DiagramElement element = this.getFacade().findParentElement((Label) selected);
            if(element instanceof Party){
                Party p = (Party) element;
                openPartyDialogBox(p);
            }
            else if(selected instanceof InvocationMessageLabel){
                InvocationMessageDialogBox invocationMessageDialogBox = new InvocationMessageDialogBox(new Point2D.Double(100, 100), (InvocationMessageLabel) selected, this);
                closeOldDialogBoxes(invocationMessageDialogBox);
                dialogBoxlist.add(invocationMessageDialogBox);
            }
            else if(element instanceof ResultMessage){
                ResultMessageDialogBox resultMessageDialogBox = new ResultMessageDialogBox(new Point2D.Double(100, 100), (ResultMessage) element , this);
                closeOldDialogBoxes(resultMessageDialogBox);
                dialogBoxlist.add(resultMessageDialogBox);
            }
        }
    }

    private void openPartyDialogBox(Party p) throws UIException{
        PartyDialogBox partyDialogBox = new PartyDialogBox(new Point2D.Double(100,100), p, this);
        closeOldDialogBoxes(partyDialogBox);
        closeOtherPartyDialogBoxes(partyDialogBox);
        dialogBoxlist.add(partyDialogBox);
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
    }

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
    //TODO update dialogBoxes
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
                    handleReleaseClick(mouseEvent);
                    break;
                case LEFTCLICK:
                    handleLeftClick(mouseEvent);
                    break;
                case LEFTDOUBLECLICK:
                    if (selectedElementIsParty()) {
                        Party oldParty = (Party) selected;
                        Party newParty = this.getFacade().changePartyType(oldParty);
                        selected = newParty;
                        //mediator.updatePartyTypeInOtherSubwindows(oldParty, newParty, this);
                    }
                    if (this.selected == null) {
                        Party newParty = this.getFacade().addNewParty(mouseEvent.getPoint());
                        selected = newParty.getLabel();
                        startEditingLabel();
                        editing = true;
                        //mediator.addNewPartyToOtherSubwindowRepos(newParty, mouseEvent.getPoint(), this);
                    }
                    break;
                default:
                    break;
            }
        }
        //TODO replace
        return new EmptyAction();
    }

    /**
     * handle a left click on the UI
     *
     * @param mouseEvent the MouseEvent containing the information of the event
     */
    private void handleReleaseClick(MouseEvent mouseEvent) {
        this.setDragging(false);
        if (selectedElementIsMessageStart()) {
            DiagramView.MessageStart ms = (DiagramView.MessageStart) selected;
            List<Message> newMessages = this.getFacade().addNewMessage(mouseEvent.getPoint(), ms);
            selected = newMessages.get(0).getLabel();
            startEditingLabel();
            editing = true;
            //mediator.addNewMessagesToOtherSubwindowRepos(newMessages, this);
        }
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
     * start editing a label in the subwindow
     */
    private void startEditingLabel() {
        labelMode = true;
        Label labelInEdit = (Label) selected;
        labelContainer = labelInEdit.getLabel() + "I";
    }

    /**
     * delete the elements in the repos of the other subwindows
     */
    private void deleteElement() {
        if (selectedElementIsLabel()) {
            Label l = (Label) selected;
            Set<DiagramElement> deletedElements = facade.deleteElementByLabel(l);
            //mediator.removeInReposInOtherSubwindows(deletedElements, this);
            stopEditingLabel();
            selected = null;
        }
    }

    /**
     * start editing a label in the subwindow
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
     */
    private void addCharToLabel(char c) throws DomainException {
        String l = "";
        if (labelContainer.equals("")) {
            l = "";
        } else {
            l = labelContainer.substring(0, labelContainer.length() - 1);
        }
        l += c;
        l += "I";
        labelContainer = l;
        handleChangeInLabel();
    }

    /**
     * removes the last char from the active label
     */
    private void removeLastCharFromLabel() throws DomainException {
        if (labelContainer.length() > 1) {
            String l = labelContainer.substring(0, getLabelContainer().length() - 2);
            l += "I";
            labelContainer = l;
            labelMode = !checkIfValidLable();
            handleChangeInLabel();
        }
    }

    /**
     * handle a change in the active label
     */
    private void handleChangeInLabel() throws DomainException {
        if (checkIfValidLable()) {
            labelMode = false;
            Label selectedLabel = (Label) selected;
            selectedLabel.setLabel(labelContainer.substring(0, getLabelContainer().length() - 1));
            //mediator.updateLabelContainers(selectedLabel, this);
        } else {
            labelMode = true;
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
        for(DialogBox dialogBox : dialogBoxlist){
            dialogBox.handleAction(action);
        }
    }


    public Action updateDialogBoxes(Action action) {
        for (DialogBox dialogBox : dialogBoxlist) {
            dialogBox.handleAction(action);
        }
        return action;
    }

    public List<DialogBox> getDialogBoxlist(){
        return dialogBoxlist;
    }
}
