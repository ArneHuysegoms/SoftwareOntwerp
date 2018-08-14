package controller;

import action.Action;
import action.DialogBoxOpenedAction;
import action.EmptyAction;
import action.UpdateListAction;
import command.closeWindow.CloseSubwindowCommand;
import exception.UIException;
import exceptions.DomainException;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.Subwindow;
import window.WindowLevelCounter;
import window.diagram.DiagramSubwindow;
import window.dialogbox.DialogBox;
import window.elements.button.Button;
import window.elements.button.CloseWindowButton;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class InteractionController{
    private List<Subwindow> subwindows;
    private Subwindow activeSubwindow;
    private DiagramSubwindow activeDiagramSubwindow;
    private boolean dragging = false;
    private HashMap<DiagramSubwindow,List<DialogBox>> map;

    /**
     * constructs an empty interactionController
     * initialises a new list of subwindows
     * initialises an active subwindow
     * initialises a map with DiagramSubwindow as key, and a list of DialogBoxes as entry
     */
    public InteractionController(){
        setSubwindows(new ArrayList<>());
        this.activeSubwindow = null;
        this.map = new HashMap<>();
    }

    /**
     * @return the map with DiagramSubwindow as key, and a list of DialogBoxes as entry
     */
    public HashMap<DiagramSubwindow, List<DialogBox>> getMap() {
        return map;
    }

    /**
     * sets the map with DiagramSubwindow as key, and a list of DialogBoxes as entry to the param
     * @param map
     */
    public void setMap(HashMap<DiagramSubwindow, List<DialogBox>> map) {
        this.map = map;
    }

    /**
     * @param diagramSubwindow
     * @return the corresponding list of DialogBoxes for the given diagramSubwindow
     */
    public List<DialogBox> getCorrespondingDialogBoxes(DiagramSubwindow diagramSubwindow){
        return this.getMap().get(diagramSubwindow);
    }

    /**
     * @return the list of subwindows
     */
    public List<Subwindow> getSubwindows() {
        return subwindows;
    }

    /**
     * sets the list of subwindows to the param
     * @param subwindows
     */
    public void setSubwindows(List<Subwindow> subwindows) {
        this.subwindows = subwindows;
    }

    /**
     *
     * @return the active subwindow
     */
    public Subwindow getActiveSubwindow() {
        return activeSubwindow;
    }

    /**
     * sets the active subwindow to the param
     * @param activeSubwindow
     */
    public void setActiveSubwindow(Subwindow activeSubwindow) {
        if(activeSubwindow != null){
            activeSubwindow.setLevel(WindowLevelCounter.getNextLevel());
            if(activeSubwindow instanceof DiagramSubwindow){
                setActiveDiagramSubwindow((DiagramSubwindow) activeSubwindow);
            }
            this.activeSubwindow = activeSubwindow;
        }
        else{
            this.activeSubwindow = null;
        }
    }

    /**
     * @return dragging
     */
    public boolean isDragging(){
        return dragging;
    }

    /**
     * @return the subwindow with the highest level
     */
    public Subwindow getHighestLevelSubwindow(){
        Subwindow highest = null;
        int level = -1;
        for(Subwindow s : subwindows){
            if(s.getLevel() > level){
                highest = s;
                level = s.getLevel();
            }
        }
        return highest;
    }

    /**
     * adds a diagramSubwindow and sets it active
     *
     * @param subwindow the diagramSubwindow to add
     */
    public void addSubwindow(Subwindow subwindow){
        if(! subwindows.contains(subwindow)){
            this.subwindows.add(subwindow);
            if(subwindow instanceof DialogBox){
                addToMap(activeDiagramSubwindow,(DialogBox)subwindow);
            }else{
                addToMap((DiagramSubwindow)subwindow,null);
            }
        }
        setActiveSubwindow(subwindow);
    }

    /**
     * adds a dialogbox to the given diagramSubwindow
     * if the diagramSubwindow is not in the map, it adds the diagramSubwindow to the map
     * @param diagramSubwindow, where the dialogbox should be added to
     * @param dialogBox, to be added to the given diagramSubwindow
     */
    public void addToMap(DiagramSubwindow diagramSubwindow, DialogBox dialogBox){
        if(this.getMap().get(diagramSubwindow) == null){
            ArrayList<DialogBox> list = new ArrayList<>();
            this.getMap().put(diagramSubwindow, list);
        }
        this.getMap().get(diagramSubwindow).add(dialogBox);
    }

    /**
     * remove a diagramSubwindow and sets the highest level subwindow active
     * if it is a DiagramSubwindow, the activeDiagramSubwindow is set to null
     *
     * @param subwindow the diagramSubwindow to remove
     */
    public void removeSubwindow(Subwindow subwindow){
        if(subwindow instanceof DialogBox){
            deleteInMap((DialogBox) subwindow);
        }else{
            deleteInMap((DiagramSubwindow) subwindow);
            setActiveDiagramSubwindow(null);
        }
        subwindows.remove(subwindow);
        setActiveSubwindow(getHighestLevelSubwindow());
    }

    /**
     * deletes a DialogBox in the map
     * @param dialogBox
     */
    public void deleteInMap(DialogBox dialogBox){
        map.values().remove(dialogBox);
    }

    /**
     * deletes a diagramSubwindow in the map, including all the dialogboxes for that diagramSubwindow
     * @param diagramSubwindow, to be deleted
     */
    public void deleteInMap(DiagramSubwindow diagramSubwindow){
        for(DialogBox d : getCorrespondingDialogBoxes(diagramSubwindow)){
            subwindows.remove(d);
        }
        map.remove(diagramSubwindow);
    }

    /**
     * @return active diagramSubwindow
     */
    public DiagramSubwindow getActiveDiagramSubwindow() {
        return activeDiagramSubwindow;
    }

    /**
     * @param activeDiagramSubwindow the new active diagramSubwindow
     */
    private void setActiveDiagramSubwindow(DiagramSubwindow activeDiagramSubwindow){
        this.activeDiagramSubwindow = activeDiagramSubwindow;
    }

    /**
     * handles a key event
     * if keyEvent is CTRLD:
     *      copy the active diagramSubwindow if it's not null
     * if keyEvent is CTRLN:
     *      create a new diagramSubwindow
     * else:
     *      get the action from the activeSubwindow and do it for every diagramSubwindow in the list
     * @param keyEvent the keyEvent to handle
     */
    public Action handleKeyEvent(KeyEvent keyEvent) throws DomainException, UIException {
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
                    return action;
                }
                break;
        }

        return new EmptyAction();
    }

    /**
     * handles MouseEvent that affects the diagramSubwindow
     *          | if dragged == false, check if diagramSubwindow currently is being dragged (update the dragging boolean)
     *          | if dragged == true, check the mouseEventType
     *                          | if RELEASE, pass the mouseEvent to activeDiagramSubwindow and set dragging == false
     *                          | if LEFTCLICK, set dragging == false
     *                          | else, do nothing
     *          | otherwise the appropriate diagramSubwindow that was clicked
     *                          | if LEFTCLICK, do nothing
     *                          | else if a diagramSubwindow is clicked
     *                              set it as the active one
     *                              get the coordinates of the click on the diagramSubwindow, relative to the diagramSubwindow
     *                              pass the mouseEvent to the appropriate diagramSubwindow
     *                              and do this action for each diagramSubwindow
     * @param mouseEvent the mouseEvent to handle
     */
    public Action handleMouseEvent(MouseEvent mouseEvent) {
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
            switch(mouseEvent.getMouseEventType()){
                case LEFTCLICK:
                    break;
                default:
                    Subwindow subwindow = getAppropriateSubwindow(mouseEvent.getPoint());
                    if (subwindow != null) {
                        changeActiveSubwindow(subwindow);
                        Point2D relativePoint = getActiveSubwindow().getRelativePoint(mouseEvent.getPoint());
                        mouseEvent.setPoint(relativePoint);
                        Action action = subwindow.handleMouseEvent(mouseEvent);
                        return action;
                    }
            }

        }
        return new EmptyAction();
    }

    /**
     * if the param action is not a DialogBoxOpenedAction
     *      handle the param action for each subwindow except the active subwindow
     * else
     *      make a new dialogBox and add it to the list of subwindows
     * @param action
     */
    public void actionForEachSubwindow(Action action){
        List<Subwindow> copy = new ArrayList<>(getSubwindows());
        if(action instanceof UpdateListAction){
            for(Subwindow s : copy){
                s.handleAction(action);
            }
        }
        else if(! (action instanceof DialogBoxOpenedAction)){
            for(Subwindow s : copy){
                if(s != getActiveSubwindow()){
                    s.handleAction(action);
                }
            }
        }
        else{
            DialogBox s = ((DialogBoxOpenedAction)action).getDialogBox();
            Button button = new CloseWindowButton(new CloseSubwindowCommand(s,this));
            s.getFrame().setButton(button);
            addSubwindow(s);
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
    }

    /**
     * creates a new diagramSubwindow with the correct level, adds it to the list of subwindows and sets it as active
     */
    private void createNewDiagramSubwindow() {
        DiagramSubwindow diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100, 100));
        Button button = new CloseWindowButton(new CloseSubwindowCommand(diagramSubwindow, this));
        diagramSubwindow.getFrame().setButton(button);
        addSubwindow(diagramSubwindow);

    }

    /**
     * copies the active diagramSubwindow, sets the correct level, adds it to the list of subwindows and sets it active
     */
    private void copyActiveDiagramSubwindow() {
        if (this.getActiveDiagramSubwindow() != null) {
            DiagramSubwindow diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100, 100), activeDiagramSubwindow.getCopyOfFacade());
            Button button = new CloseWindowButton(new CloseSubwindowCommand(diagramSubwindow, this));
            diagramSubwindow.getFrame().setButton(button);
            addSubwindow(diagramSubwindow);
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
