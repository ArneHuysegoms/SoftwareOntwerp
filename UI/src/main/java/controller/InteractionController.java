package controller;

import action.Action;
import command.closeWindow.CloseSubwindowCommand;
import exception.UIException;
import exceptions.DomainException;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.Subwindow;
import window.WindowLevelCounter;
import window.diagram.DiagramSubwindow;
import window.elements.button.Button;
import window.elements.button.CloseWindowButton;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InteractionController implements IHighLevelController{
    private List<Subwindow> subwindows;
    private Subwindow activeSubwindow;
    //private List<DiagramSubwindow> diagramSubwindows;
    private DiagramSubwindow activeDiagramSubwindow;
    private boolean dragging = false;

    /**
     * default constructor
     */
    public InteractionController(){
        this.subwindows = new ArrayList<>();
        this.activeSubwindow = null;
    }

    public List<Subwindow> getSubwindows() {
        return subwindows;
    }

    public void setSubwindows(List<Subwindow> subwindows) {
        this.subwindows = subwindows;
    }

    public Subwindow getActiveSubwindow() {
        return activeSubwindow;
    }

    public void setActiveSubwindow(Subwindow activeSubwindow) {
        if(activeSubwindow instanceof DiagramSubwindow){
            setActiveDiagramSubwindow((DiagramSubwindow) activeSubwindow);
        }
        this.activeSubwindow = activeSubwindow;
    }

    public Subwindow getHighestLevelSubwindow(){
        Subwindow highest = null;
        int level = -1;
        for(Subwindow s : subwindows){
            if(s.getLevel() > level){
                highest = s;
            }
        }
        return highest;
    }

    /**
     * add a new party to all the diagramSubwindows repos except for the given diagramSubwindow
     *
     * @param party  the party to add
     * @param location the location of the new Party
     * @param diagramSubwindow the original diagramSubwindow
     */
    /*public void addNewPartyToOtherSubwindowRepos(Party party, Point2D location, DiagramSubwindow diagramSubwindow){
        for(Subwindow s : this.subwindows){
            if(s instanceof DiagramSubwindow) {
                if (!s.equals(diagramSubwindow)) {
                    ((DiagramSubwindow) s).getFacade().addPartyToRepo(party, location);
                    //TODO ook dialogboxes updaten?
                }
            }
        }
        setActiveSubwindow(diagramSubwindow);
    }*/

    /**
     * remove a set of elements from the the diagramSubwindow repos except for the given diagramSubwindow
     *
     * @param deletedElements the elements to delete
     * @param diagramSubwindow the original diagramSubwindow
     */
    /*public void removeInReposInOtherSubwindows(Set<DiagramElement> deletedElements, DiagramSubwindow diagramSubwindow){
        for(Subwindow s : this.subwindows){
            if(s instanceof DiagramSubwindow){
                if(! s.equals(diagramSubwindow)) {
                    ((DiagramSubwindow)s).getFacade().deleteElementsInRepos(deletedElements);
                    if(deletedElements.contains(((DiagramSubwindow)s).getSelected())){
                        ((DiagramSubwindow)s).setSelected(null);
                        // TODO ook dialogboxes updaten??
                    }
                }
            }

        }
        setActiveSubwindow(getHighestLevelSubwindow());
    }*/

    /**
     * add a diagramSubwindow
     *
     * @param subwindow the diagramSubwindow to add
     */
    public void addSubwindow(Subwindow subwindow){
        if(! subwindows.contains(subwindow)){
            this.subwindows.add(subwindow);
        }
        setActiveSubwindow(subwindow);
    }

    /**
     * remove a diagramSubwindow
     *
     * @param subwindow the diagramSubwindow to remove
     */

    public void removeSubwindow(Subwindow subwindow){
        subwindows.remove(subwindow);
        setActiveSubwindow(getHighestLevelSubwindow());
    }

    /**
     * add a set of messages to all the diagramSubwindows repos except for the given diagramSubwindow
     *
     * @param newMessages the newmesssages to add
     * @param diagramSubwindow the original diagramSubwindow
     */
    /*public void addNewMessagesToOtherSubwindowRepos(List<Message> newMessages, DiagramSubwindow diagramSubwindow) {
        for(Subwindow s : this.subwindows){
            if(s instanceof DiagramSubwindow){
                if( ! s.equals(diagramSubwindow)){
                    ((DiagramSubwindow)s).getFacade().addMessagesToRepos(newMessages);
                }
            }

        }
    }*/

    /**
     *
     * @return all diagramSubwindows this mediator  mediates
     */
    /*public List<DiagramSubwindow> getDiagramSubwindows() {
        return diagramSubwindows;
    }*/

    /**
     * updates the party type in all other diagramSubwindows
     * @param oldParty the old type
     * @param newParty the new type
     * @param diagramSubwindow the original diagramSubwindow
     */
    /*public void updatePartyTypeInOtherSubwindows(Party oldParty, Party newParty, DiagramSubwindow diagramSubwindow)  {
        for(Subwindow s : this.subwindows){
            if(s instanceof DiagramSubwindow) {
                if (!s.equals(diagramSubwindow)) {
                    ((DiagramSubwindow) s).getFacade().changePartyTypeInRepo(oldParty, newParty);
                    ((DiagramSubwindow) s).setSelected(newParty);
                }
            }
        }
        setActiveSubwindow(diagramSubwindow);
    }*/

    /**
     * updates the labelcontainers of the other diagramSubwindows and sets the correct flags for label editing
     * @param selectedLabel the label that has been edited
     * @param diagramSubwindow the original diagramSubwindow
     */
    /*public void updateLabelContainers(Label selectedLabel, DiagramSubwindow diagramSubwindow) {
        for(Subwindow s : this.subwindows){
            if(s instanceof DiagramSubwindow){
                if (! s.equals(diagramSubwindow)){
                    if(((DiagramSubwindow) s).getSelected() instanceof Label && ( ((DiagramSubwindow) s).getSelected()).equals(selectedLabel)){
                        ((DiagramSubwindow) s).stopEditingLabel();
                        ((DiagramSubwindow) s).setLabelMode(false);
                        ((DiagramSubwindow) s).setEditing(false);
                    }
                }
            }

        }
        setActiveSubwindow(diagramSubwindow);
    }*/

    /**
     *
     * @return active diagramSubwindow
     */
    public DiagramSubwindow getActiveDiagramSubwindow() {
        return activeDiagramSubwindow;
    }

    /**
     *
     * @param activeDiagramSubwindow the new active diagramSubwindow
     */
    private void setActiveDiagramSubwindow(DiagramSubwindow activeDiagramSubwindow){
        activeDiagramSubwindow.setLevel(WindowLevelCounter.getNextLevel());
        this.activeDiagramSubwindow = activeDiagramSubwindow;
    }

    /**
     * handles a key event
     * @param keyEvent the keyEvent to handle
     */
    public void handleKeyEvent(KeyEvent keyEvent) throws DomainException, UIException {
        switch (keyEvent.getKeyEventType()) {
            case CTRLD:
                if (getActiveDiagramSubwindow() != null) {
                    copyActiveDiagramSubwindow();
                }
                break;
            case CTRLN:
                createNewDiagramSubwindow();
                break;
            default:
                if (this.getActiveDiagramSubwindow() != null) {
                    Action action = activeSubwindow.handleKeyEvent(keyEvent);
                    actionForEachDiagramSubwindow(action);
                }
                break;
        }
    }

    /**
     * handles MouseEvent that affects the diagramSubwindow
     *          | if dragged == false, check if diagramSubwindow currently is being dragged (update the dragging boolean)
     *          | if dragged == true, check the mouseEventType
     *                          | if RELEASE, pass the mouseEvent to activeDiagramSubwindow and set dragging == false
     *                          | if LEFTCLICK, set dragging == false
     *          | otherwise the appropriate diagramSubwindow that was clicked
     *                          | if the diagramSubwindow exists
     *                              | if the diagramSubwindow is not the active one, set it as the active one
     *                              get the coordinates of the click on the diagramSubwindow, relative to the diagramSubwindow
     *                              pass the mouseEvent to the appropriate diagramSubwindow
     * @param mouseEvent the mouseEvent to handle
     */
    public void handleMouseEvent(MouseEvent mouseEvent) {
        if (!dragging) {
            dragging = checkFordragging(mouseEvent);
        }
        if (dragging) {
            switch (mouseEvent.getMouseEventType()) {
                case RELEASE:
                    activeSubwindow.handleMovement(mouseEvent.getPoint());
                    dragging = false;
                    break;
                case LEFTCLICK:
                    dragging = false;
                    break;
                default:
                    break;
            }
        } else {
            Subwindow subwindow = getAppropriateSubwindow(mouseEvent.getPoint());
            if (subwindow != null) {
                if (!subwindow.equals(getActiveSubwindow())) {
                    changeActiveSubwindow(subwindow);
                }
                Point2D relativePoint = getActiveSubwindow().getRelativePoint(mouseEvent.getPoint());
                mouseEvent.setPoint(relativePoint);
                Action action = subwindow.handleMouseEvent(mouseEvent);
                actionForEachDiagramSubwindow(action);

            }
        }
    }

    public void actionForEachDiagramSubwindow(Action action){
        for(Subwindow s : getSubwindows()){
            if(s instanceof DiagramSubwindow){
                ((DiagramSubwindow)s).handleAction(action);
            }
        }
    }

    /**
     * checks if  the mouseEvent results in a drag
     *
     * @param mouseEvent the mouseEvent to check for
     * @return if the active diagramSubwindow exists
     *              | if the active diagramSubwindow is being dragged
     *                  return false
     *              | if the active diagramSubwindow's frame has been clicked
     *                  return true
     *              | if the active diagramSubwindow is not in label mode
     *                  if a diagramSubwindow has been clicked
     *                  return true
     *         else return false
     */
    private boolean checkFordragging(MouseEvent mouseEvent) {
        if (activeSubwindow != null) {
            if (getActiveSubwindow().isDragging()) {
                return false;
            }
            if (activeSubwindow.frameIsClicked(mouseEvent.getPoint())) {
                return true;
            }
            if (!getActiveSubwindow().isLabelMode()) {
                /*for (SubWindowLevel window.diagram : subwindows) {
                    if (window.diagram.getDiagramSubwindow().frameIsClicked(mouseEvent.getPoint())) {
                        changeActiveSubwindow(window.diagram.getDiagramSubwindow());
                        return true;
                    }
                }*/
                changeActiveSubwindow(getAppropriateSubwindow(mouseEvent.getPoint()));
                return true;
            }
        }
        return false;
    }

    /**
     * sets the active diagramSubwindow to newActiveSubWindow and changes the level to the highest
     *
     * @param newActiveSubWindow the new actie diagramSubwindow
     *
     */
    private void changeActiveSubwindow(Subwindow newActiveSubWindow) {
        this.setActiveSubwindow(newActiveSubWindow);
        //this.changeLevelForActiveSubWindow();
    }

    /**
     * creates a new diagramSubwindow with the correct level, adds it to the list of subwindows and sets it as active
     */
    private void createNewDiagramSubwindow() {
        DiagramSubwindow diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100, 100));
        Button button = new CloseWindowButton(new CloseSubwindowCommand(diagramSubwindow, this));
        diagramSubwindow.getFrame().setButton(button);
        //int level = getCorrectLevel();
        addSubwindow(diagramSubwindow);
        this.changeActiveSubwindow(diagramSubwindow);
    }

    /**
     * copies the active diagramSubwindow, sets the correct level, adds it to the list of subwindows and sets it active
     */
    private void copyActiveDiagramSubwindow() {
        if (this.getActiveDiagramSubwindow() != null) {
            DiagramSubwindow diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100, 100), activeDiagramSubwindow.getCopyOfFacade());
            Button button = new CloseWindowButton(new CloseSubwindowCommand(diagramSubwindow, this));
            diagramSubwindow.getFrame().setButton(button);
            //int level = getCorrectLevel();
            addSubwindow(diagramSubwindow);
            this.changeActiveSubwindow(diagramSubwindow);
        }
    }

    /**
     * returns the subwindow with the highest level
     * @param clickedLocation the location of the click
     * @return subwindow if a subwindow is clicked, null otherwise
     */
    public Subwindow getAppropriateSubwindow(Point2D clickedLocation) {
        List<Subwindow> clickedSubwindows = this.getSubwindows()
                .stream()
                .filter(s -> s.isClicked(clickedLocation))
                .collect(Collectors.toList());
        int level = -1;
        Subwindow highest = null;
        for (Subwindow s : clickedSubwindows) {
            if (s.getLevel() > level) {
                highest = s;
                level = s.getLevel();
            }
        }
        return highest;
    }
}
