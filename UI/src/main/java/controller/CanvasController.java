package controller;

import action.Action;
import action.UpdateLabelAction;
import action.UpdateListAction;
import exception.UIException;
import exceptions.DomainException;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.Subwindow;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Layer between UI and the domain
 *
 * Interprets UIEvents and passes these on to the appropriate interactionController.
 */
public class CanvasController {

    private InteractionController activeInteractionController;
    private List<InteractionController> interactionControllers;

    /**
     * constructs a new empty canvascontroller
     * initialises activeInteractionController and the list of interactionControllers
     */
    public CanvasController() {
        this.activeInteractionController = null;
        this.setInteractionControllers(new ArrayList<>());
    }

    /**
     * @return activeInteractionController
     */
    public InteractionController getActiveInteractionController() {
        return activeInteractionController;
    }

    /**
     * sets activeInteractionController to the given param
     * @param activeInteractionController
     */
    public void setActiveInteractionController(InteractionController activeInteractionController) {
        this.activeInteractionController = activeInteractionController;
    }

    /**
     * @return the list of interactionControllers
     */
    public List<InteractionController> getInteractionControllers() {
        return interactionControllers;
    }

    /**
     * sets the list of interactionControllers to the given param
     * @param interactionControllers
     */
    public void setInteractionControllers(List<InteractionController> interactionControllers) {
        this.interactionControllers = interactionControllers;
    }

    /**
     * adds the param to the list of interactionControllers if it doesn't contain it already
     * @param interactionController
     */
    public void addInteractionController(InteractionController interactionController){
        if(!this.getInteractionControllers().contains(interactionController)){
            this.interactionControllers.add(interactionController);
        }
        setActiveInteractionController(interactionController);
    }

    /**
     * removes the param of the list of interactionControllers
     * if the interactionController was active, set the interactionController with the highest level as active
     * @param interactionController to be removed
     */
    public void removeInteractionController(InteractionController interactionController){
        this.interactionControllers.remove(interactionController);
        if(this.getActiveInteractionController().equals(interactionController)){
            setActiveInteractionController(findHighestLevelInteractionController());
        }
    }

    /**
     * changes the active interactionController to the param
     * @param interactionController to be changed to active
     */
    public void changeActiveInteractionController(InteractionController interactionController){
        setActiveInteractionController(interactionController);
    }

    /**
     * handles a key event
     *      | if CTRLD
     *          if the activeInteractionController exists, let it handle the keyEvent
     *      | if CTRLN
     *          create a new interactionController
     *          and let it handle the keyEvent
     *      | else if the activeInteractionController exists
     *          let it handle the keyEvent
     * @param keyEvent the keyEvent to handle
     */
    public void handleKeyEvent(KeyEvent keyEvent) throws DomainException, UIException {
        switch (keyEvent.getKeyEventType()) {
            case CTRLD:
                if(getActiveInteractionController() != null){
                    Action action = activeInteractionController.handleKeyEvent(keyEvent);
                    this.getActiveInteractionController().actionForEachSubwindow(action);
                }
                break;
            case CTRLN:
                createNewInteractionController();
                Action action = activeInteractionController.handleKeyEvent(keyEvent);
                this.getActiveInteractionController().actionForEachSubwindow(action);
                break;
            default:
                if(this.getActiveInteractionController() != null){
                    Action act = activeInteractionController.handleKeyEvent(keyEvent);
                    if(act instanceof UpdateListAction){
                        handleForEachInteractionController(act);
                    }
                    if(act instanceof UpdateLabelAction){
                        handleForEachInteractionController(new UpdateListAction());
                    }
                    this.getActiveInteractionController().actionForEachSubwindow(act);

                }
                break;
        }
    }

    public void handleForEachInteractionController(Action action){
        List<InteractionController> copy = new ArrayList<>(getInteractionControllers());
        for(InteractionController ic : getInteractionControllers()){
            ic.actionForEachSubwindow(action);
        }
        /*if(! (action instanceof DialogBoxOpenedAction)){
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
        }*/

    }

    /**
     * handles MouseEvent that affects the diagramSubwindow
     *          | if the activeInteractionController exists and it's dragging
     *              the activeInteractionController handles the mouseEvent
     *          | else if there is a clicked interactionController
     *              change it to active and let it handle the mouseEvent
     * @param mouseEvent the mouseEvent to handle
     */
    public void handleMouseEvent(MouseEvent mouseEvent) {

        if(activeInteractionController != null && activeInteractionController.isDragging()){
            Action action = activeInteractionController.handleMouseEvent(mouseEvent);
            activeInteractionController.actionForEachSubwindow(action);
        }
        else if (getAppropriateInteractionController(mouseEvent.getPoint()) != null) {
            InteractionController ic = getAppropriateInteractionController(mouseEvent.getPoint());
            if (!ic.equals(getActiveInteractionController())) {
                changeActiveInteractionController(ic);
            }
            Action action = activeInteractionController.handleMouseEvent(mouseEvent);
            if(action instanceof UpdateListAction){
                handleForEachInteractionController(action);
            }
            activeInteractionController.actionForEachSubwindow(action);
        }
    }

    /**
     * creates a new interactionController, adds it to the list of interactionControllers, and sets it active
     */
    private void createNewInteractionController(){
        InteractionController interactionController = new InteractionController();
        addInteractionController(interactionController);
    }

    /**
     * @return the interactionController with the subwindow with the highest level
     */
    public InteractionController findHighestLevelInteractionController(){
        InteractionController result = null;
        int level = -1;
        for (InteractionController ic : getInteractionControllers()) {
            Subwindow s = ic.getHighestLevelSubwindow();
            if(s.getLevel() > level){
                level = s.getLevel();
                result = ic;
            }
        }
        return result;
    }

    /**
     * @param clickedLocation location where clicked
     * @return the interactionController with the subwindow with the highest level on the clickedLocation
     */
    public InteractionController getAppropriateInteractionController(Point2D clickedLocation){
        InteractionController result = null;
        int level = -1;
        for(InteractionController ic : getInteractionControllers()){
            if(ic.getAppropriateSubwindow(clickedLocation) != null){
                Subwindow s = ic.getAppropriateSubwindow(clickedLocation);
                if(s.getLevel() > level){
                    result = ic;
                    level = s.getLevel();
                }
            }
        }
        return result;
    }

    /**
     * method that gathers and sorts all the Subwindows
     *
     * @return List containing all subwindows
     */
    public List<Subwindow> sortDiagramSubwindows() {
        List<Subwindow> result = new ArrayList<Subwindow>();

        for (InteractionController interaction : this.getInteractionControllers()) {
            result.addAll(interaction.getSubwindows());
        }

        Collections.sort(result);
        return result;
    }
}
