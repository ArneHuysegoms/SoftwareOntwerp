package controller;

import command.closeWindow.CloseSubwindowCommand;
import exception.UIException;
import exceptions.DomainException;
import window.diagram.DiagramSubwindow;
import window.Subwindow;
import uievents.KeyEvent;
import uievents.MouseEvent;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Layer between UI and the domain
 *
 * Interprets UIEvents and passes these on to the appropriate interactionController.
 */
public class CanvasController implements IHighLevelController{

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
     *
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
     *
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
     * @param interactionController
     */
    public void removeInteractionController(InteractionController interactionController){
        this.interactionControllers.remove(interactionController);
        if(this.getActiveInteractionController().equals(interactionController)){
            setActiveInteractionController(findHighestLevelInteractionController());
        }
    }

    /**
     * changes the active interactionController to the param
     * @param interactionController
     */
    public void changeActiveInteractionController(InteractionController interactionController){
        setActiveInteractionController(interactionController);
    }

    /**
     * handles a key event
     * @param keyEvent the keyEvent to handle
     */
    public void handleKeyEvent(KeyEvent keyEvent) throws DomainException, UIException {
        switch (keyEvent.getKeyEventType()) {
            case CTRLD:
                //checkForDeleteInteractionController();
                if(getActiveInteractionController() != null){
                    activeInteractionController.handleKeyEvent(keyEvent);
                }
                break;
            case CTRLN:
                createNewInteractionController();
                activeInteractionController.handleKeyEvent(keyEvent);
                break;
            default:
                if(this.getActiveInteractionController() != null){
                    activeInteractionController.handleKeyEvent(keyEvent);
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

        if(activeInteractionController != null && activeInteractionController.isDragging()){
            activeInteractionController.handleMouseEvent(mouseEvent);
        }
        else if (getAppropriateInteractionController(mouseEvent.getPoint()) != null) {
            InteractionController ic = getAppropriateInteractionController(mouseEvent.getPoint());
            if (!ic.equals(getActiveInteractionController())) {
                changeActiveInteractionController(ic);
            }
            ic.handleMouseEvent(mouseEvent);
        }
    }

    /**
     * creates a new interactionController, adds it to the list of interactionControllers, and sets it active
     */
    private void createNewInteractionController(){
        InteractionController interactionController = new InteractionController();
        this.getInteractionControllers().add(interactionController);
        this.changeActiveInteractionController(interactionController);
    }

    /*private void checkForDeleteInteractionController(){
        if(activeInteractionController.getActiveDiagramSubwindow() == null){
            if(activeInteractionController.getSubwindows().isEmpty()){
                this.activeInteractionController = null;
            }
        }
        //getSubwindows() kan nog dialogboxes bevatten?
    }*/

    /**
     *
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
     *
     * @param clickedLocation
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
}
