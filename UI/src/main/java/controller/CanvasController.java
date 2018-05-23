package controller;

import exception.UIException;
import exceptions.DomainException;
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
     *          | if the activeInteractionController exists and it's dragging
     *              the activeInteractionController handles the mouseEvent
     *          | else if there is a clicked interactionController
     *              change it to active and let it handle the mouseEvent
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
            activeInteractionController.handleMouseEvent(mouseEvent);
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
}
