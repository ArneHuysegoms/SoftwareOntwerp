package controller;

import command.closeWindow.CloseDiagramSubwindowCommand;
import command.closeWindow.CloseSubwindowCommand;
import diagram.DiagramElement;
import diagram.label.Label;
import diagram.message.Message;
import diagram.party.Party;
import exception.UIException;
import exceptions.DomainException;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.diagram.DiagramSubwindow;
import window.elements.button.Button;
import window.elements.button.CloseDiagramSubwindowButton;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InteractionController {
    private List<DiagramSubwindow> diagramSubwindows;
    private DiagramSubwindow activeDiagramSubwindow;
    private boolean dragging = false;

    /**
     * default constructor
     */
    public InteractionController(){
        this.diagramSubwindows = new ArrayList<>();
        this.activeDiagramSubwindow = null;
    }

    public DiagramSubwindow getHighestLevelSubwindow(){
        DiagramSubwindow highest = null;
        int level = -1;
        for(DiagramSubwindow s : diagramSubwindows){
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
    public void addNewPartyToOtherSubwindowRepos(Party party, Point2D location, DiagramSubwindow diagramSubwindow){
        for(DiagramSubwindow s : diagramSubwindows){
            if(! s.equals(diagramSubwindow)) {
                s.getFacade().addPartyToRepo(party, location);
            }
        }
        setActiveDiagramSubwindow(diagramSubwindow);
    }

    /**
     * remove a set of elements from the the diagramSubwindow repos except for the given diagramSubwindow
     *
     * @param deletedElements the elements to delete
     * @param diagramSubwindow the original diagramSubwindow
     */
    public void removeInReposInOtherSubwindows(Set<DiagramElement> deletedElements, DiagramSubwindow diagramSubwindow){
        for(DiagramSubwindow s : diagramSubwindows){
            if(! s.equals(diagramSubwindow)) {
                s.getFacade().deleteElementsInRepos(deletedElements);
                if(deletedElements.contains(s.getSelected())){
                    s.setSelected(null);
                }
            }
        }
        //check levels voor nieuwe activesubwindow
        setActiveDiagramSubwindow(getHighestLevelSubwindow());
    }

    /**
     * add a diagramSubwindow
     *
     * @param diagramSubwindow the diagramSubwindow to add
     */
    public void addSubwindow(DiagramSubwindow diagramSubwindow){
        if(! diagramSubwindows.contains(diagramSubwindow)){
            this.diagramSubwindows.add(diagramSubwindow);
        }
        setActiveDiagramSubwindow(diagramSubwindow);
    }

    /**
     * remove a diagramSubwindow
     *
     * @param diagramSubwindow the diagramSubwindow to remove
     */

    public void removeSubwindow(DiagramSubwindow diagramSubwindow){
        diagramSubwindows.remove(diagramSubwindow);
        //check levels voor nieuwe activesubwindow
        setActiveDiagramSubwindow(getHighestLevelSubwindow());
    }

    /**
     * add a set of messages to all the diagramSubwindows repos except for the given diagramSubwindow
     *
     * @param newMessages the newmesssages to add
     * @param diagramSubwindow the original diagramSubwindow
     */
    public void addNewMessagesToOtherSubwindowRepos(List<Message> newMessages, DiagramSubwindow diagramSubwindow) {
        for(DiagramSubwindow s : diagramSubwindows){
            if( ! s.equals(diagramSubwindow)){
                s.getFacade().addMessagesToRepos(newMessages);
            }
        }
    }

    /**
     *
     * @return all diagramSubwindows this mediator  mediates
     */
    public List<DiagramSubwindow> getDiagramSubwindows() {
        return diagramSubwindows;
    }

    /**
     * updates the party type in all other diagramSubwindows
     * @param oldParty the old type
     * @param newParty the new type
     * @param diagramSubwindow the original diagramSubwindow
     */
    public void updatePartyTypeInOtherSubwindows(Party oldParty, Party newParty, DiagramSubwindow diagramSubwindow)  {
        for(DiagramSubwindow s : diagramSubwindows){
            if (! s.equals(diagramSubwindow)){
                s.getFacade().changePartyTypeInRepo(oldParty, newParty);
                s.setSelected(newParty);
            }
        }
        setActiveDiagramSubwindow(diagramSubwindow);
    }

    /**
     * updates the labelcontainers of the other diagramSubwindows and sets the correct flags for label editing
     * @param selectedLabel the label that has been edited
     * @param diagramSubwindow the original diagramSubwindow
     */
    public void updateLabelContainers(Label selectedLabel, DiagramSubwindow diagramSubwindow) {
        for(DiagramSubwindow s : diagramSubwindows){
            if (! s.equals(diagramSubwindow)){
                if(s.getSelected() instanceof Label && ( s.getSelected()).equals(selectedLabel)){
                    s.stopEditingLabel();
                    s.setLabelMode(false);
                    s.setEditing(false);
                }
            }
        }
        setActiveDiagramSubwindow(diagramSubwindow);
    }

    /**
     * @return the list containing all subwindows
     *//*
    public List<SubWindowLevel> getSubwindows() {
        return subwindows;
    }

    *//**
     * sets the subwindowlevels  to the given subwindowlevels
     * @param subwindows the new subwindows
     * @throws IllegalArgumentException if the given subwindows is null
     *//*
    private void setSubwindows(List<SubWindowLevel> subwindows) throws IllegalArgumentException {
        if (subwindows == null) {
            throw new IllegalArgumentException("Subwindows may not be null");
        }
        this.subwindows = subwindows;
    }
*/
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
        this.activeDiagramSubwindow = activeDiagramSubwindow;
    }

    /**
     * removes the diagramSubwindow from the list and sets the diagramSubwindow with the highest level as active diagramSubwindow
     * @param diagramSubwindow the diagramSubwindow to remove
     */
    /*public void removeSubwindow(DiagramSubwindow diagramSubwindow) {
        SubWindowLevel toRemove = null;
        for (SubWindowLevel s : subwindows) {
            if (s.getDiagramSubwindow().equals(diagramSubwindow)) {
                toRemove = s;
            }
        }
        this.getSubwindows().remove(toRemove);
        setNewActiveSubWindow();
    }*/

    /**
     * sets the diagramSubwindow with the highest level as active diagramSubwindow
     */
    /*public void setNewActiveSubWindow() {
        SubWindowLevel s = findHighestSubwindowLevel();
        if (s != null) {
            changeActiveSubwindow(s.getDiagramSubwindow());
        } else {
            changeActiveSubwindow(null);
        }
    }*/

    /**
     * returns the diagramSubwindow with the highest level
     * @return subwindowlevel
     */
    /*public SubWindowLevel findHighestSubwindowLevel() {
        int level = -1;
        SubWindowLevel chosen = null;
        for (SubWindowLevel s : subwindows) {
            if (s.getLevel() > level) {
                chosen = s;
                level = s.getLevel();
            }
        }
        return chosen;
    }*/

    /**
     * adds a diagramSubwindow with a given level to the list of subwindows
     * @param diagramSubwindow the subindow to add
     * @param level, level of the diagramSubwindow
     */
    /*public void addSubwindow(DiagramSubwindow diagramSubwindow, int level) {
        if (diagramSubwindow == null) {
            throw new IllegalArgumentException("can't add null diagramSubwindow");
        }
        SubWindowLevel subWindowLevel = new SubWindowLevel(diagramSubwindow, level);
        this.getSubwindows().add(subWindowLevel);
    }*/

    /**
     * handles a key event
     * @param keyEvent the keyEvent to handle
     */
    public void handleKeyEvent(KeyEvent keyEvent) throws DomainException, UIException {
        switch (keyEvent.getKeyEventType()) {
            case CTRLD:
                if (getActiveDiagramSubwindow() != null) {
                    copyActiveSubWindow();
                }
                break;
            case CTRLN:
                createNewSubwindow();
                break;
            default:
                if (this.getActiveDiagramSubwindow() != null) {
                    activeDiagramSubwindow.handleKeyEvent(keyEvent);
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
                    activeDiagramSubwindow.handleMovement(mouseEvent.getPoint());
                    dragging = false;
                    break;
                case LEFTCLICK:
                    dragging = false;
                    break;
                default:
                    break;
            }
        } else {
            DiagramSubwindow diagramSubwindow = getAppropriateSubwindow(mouseEvent.getPoint());
            if (diagramSubwindow != null) {
                if (!diagramSubwindow.equals(getActiveDiagramSubwindow())) {
                    changeActiveSubwindow(diagramSubwindow);
                }
                Point2D relativePoint = getActiveDiagramSubwindow().getRelativePoint(mouseEvent.getPoint());
                mouseEvent.setPoint(relativePoint);
                diagramSubwindow.handleMouseEvent(mouseEvent);
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
        if (activeDiagramSubwindow != null) {
            if (getActiveDiagramSubwindow().isDragging()) {
                return false;
            }
            if (activeDiagramSubwindow.frameIsClicked(mouseEvent.getPoint())) {
                return true;
            }
            if (!getActiveDiagramSubwindow().isInLabelMode()) {
                /*for (SubWindowLevel subwindow : subwindows) {
                    if (subwindow.getDiagramSubwindow().frameIsClicked(mouseEvent.getPoint())) {
                        changeActiveSubwindow(subwindow.getDiagramSubwindow());
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
    private void changeActiveSubwindow(DiagramSubwindow newActiveSubWindow) {
        this.setActiveDiagramSubwindow(newActiveSubWindow);
        //this.changeLevelForActiveSubWindow();
    }

    /**
     * changes the level for the active diagramSubwindow
     */
   /* private void changeLevelForActiveSubWindow() {
        if (activeDiagramSubwindow != null) {
            for (SubWindowLevel s : subwindows) {
                if (s.getDiagramSubwindow().equals(getActiveDiagramSubwindow())) {
                    s.setLevel(getCorrectLevel());
                }
            }
        }
    }*/

    /**
     * creates a new diagramSubwindow with the correct level, adds it to the list of subwindows and sets it as active
     */
    private void createNewSubwindow() {
        DiagramSubwindow diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100, 100));
        Button button = new CloseDiagramSubwindowButton(new CloseDiagramSubwindowCommand(this, diagramSubwindow));
        diagramSubwindow.getFrame().setButton(button);
        //int level = getCorrectLevel();
        addSubwindow(diagramSubwindow);
        this.changeActiveSubwindow(diagramSubwindow);
    }

    /**
     * copies the active diagramSubwindow, sets the correct level, adds it to the list of subwindows and sets it active
     */
    private void copyActiveSubWindow() {
        if (this.getActiveDiagramSubwindow() != null) {
            DiagramSubwindow diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100, 100), activeDiagramSubwindow.getCopyOfFacade());
            Button button = new CloseDiagramSubwindowButton(new CloseDiagramSubwindowCommand(this, diagramSubwindow));
            diagramSubwindow.getFrame().setButton(button);
            //int level = getCorrectLevel();
            addSubwindow(diagramSubwindow);
            this.changeActiveSubwindow(diagramSubwindow);
        }
    }

    /**
     *
     * @return the highest level in the list of subwindows
     */
    /*private int getCorrectLevel() {
        int level = -1;
        for (SubWindowLevel s : subwindows) {
            if (s.getLevel() > level) {
                level = s.getLevel();
            }
        }
        level++;
        return level;
    }*/

    /**
     * returns the diagramSubwindow with the highest level
     * @param clickedLocation the location of the click
     * @return diagramSubwindow if a diagramSubwindow is clicked, null otherwise
     */
    /*private DiagramSubwindow getAppropriateSubwindow(Point2D clickedLocation) {
        List<SubWindowLevel> clickedSubwindows = this.getSubwindows()
                .stream()
                .filter(s -> s.getDiagramSubwindow().isClicked(clickedLocation))
                .collect(Collectors.toList());
        int level = -1;
        DiagramSubwindow highest = null;
        for (SubWindowLevel s : clickedSubwindows) {
            if (s.getLevel() > level) {
                highest = s.getDiagramSubwindow();
                level = s.getLevel();
            }
        }
        return highest;
    }*/

    public DiagramSubwindow getAppropriateSubwindow(Point2D clickedLocation) {
        List<DiagramSubwindow> clickedSubwindows = this.getDiagramSubwindows()
                .stream()
                .filter(s -> s.isClicked(clickedLocation))
                .collect(Collectors.toList());
        int level = -1;
        DiagramSubwindow highest = null;
        for (DiagramSubwindow s : clickedSubwindows) {
            if (s.getLevel() > level) {
                highest = s;
                level = s.getLevel();
            }
        }
        return highest;
    }
}
