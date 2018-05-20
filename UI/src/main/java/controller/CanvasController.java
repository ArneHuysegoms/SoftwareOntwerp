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
import java.util.stream.Collectors;

/**
 * Main layer between pure UI and the domain
 *
 * Interprets UIEvents and addresses the appropriate functions in the diagram.
 */
public class CanvasController {

    //private List<SubWindowLevel> subwindows;
    //private DiagramSubwindow activeDiagramSubwindow;
    private boolean dragging = false;
    private InteractionController activeInteractionController;
    private List<InteractionController> interactionControllers;

    /**
     * constructs a new empty canvascontroller
     */
    public CanvasController() {
        //this.setSubwindows(new ArrayList<>());
        //this.activeDiagramSubwindow = null;
        this.activeInteractionController = null;
        this.setInteractionControllers(new ArrayList<>());
    }

    public InteractionController getActiveInteractionController() {
        return activeInteractionController;
    }

    public void setActiveInteractionController(InteractionController activeInteractionController) {
        this.activeInteractionController = activeInteractionController;
    }

    public List<InteractionController> getInteractionControllers() {
        return interactionControllers;
    }

    public void setInteractionControllers(List<InteractionController> interactionControllers) {
        this.interactionControllers = interactionControllers;
    }

    public void addInteractionController(InteractionController interactionController){
        if(!this.getInteractionControllers().contains(interactionController)){
            this.interactionControllers.add(interactionController);
        }
        setActiveInteractionController(interactionController);
    }

    public void removeInteractionController(InteractionController interactionController){
        this.interactionControllers.remove(interactionController);
        //iets active setten?
    }

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
                checkForDeleteInteractionController();
                if(getActiveInteractionController() != null){
                    activeInteractionController.handleKeyEvent(keyEvent);
                }
                /*if (getActiveDiagramSubwindow() != null) {
                    copyActiveSubWindow();
                }*/
                break;
            case CTRLN:
                createNewInteractionController();
                activeInteractionController.handleKeyEvent(keyEvent);
                //createNewSubwindow();
                break;
            default:
                if(this.getActiveInteractionController() != null){
                    activeInteractionController.handleKeyEvent(keyEvent);
                }
                /*if (this.getActiveDiagramSubwindow() != null) {
                    activeDiagramSubwindow.handleKeyEvent(keyEvent);
                }*/
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
        /*if (!dragging) {
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
        } else {*/
            InteractionController ic = getAppropriateInteractionController(mouseEvent.getPoint());
            if (ic != null) {
                if (!ic.equals(getActiveInteractionController())) {
                    changeActiveInteractionController(ic);
                }
                ic.handleMouseEvent(mouseEvent);
                /*Point2D relativePoint = getActiveDiagramSubwindow().getRelativePoint(mouseEvent.getPoint());
                mouseEvent.setPoint(relativePoint);
                diagramSubwindow.handleMouseEvent(mouseEvent);*/
            }
        //}
    }



    /*
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
/*    private boolean checkFordragging(MouseEvent mouseEvent) {
        if (activeDiagramSubwindow != null) {
            if (getActiveDiagramSubwindow().isDragging()) {
                return false;
            }
            if (activeDiagramSubwindow.frameIsClicked(mouseEvent.getPoint())) {
                return true;
            }
            if (!getActiveDiagramSubwindow().isInLabelMode()) {
                for (SubWindowLevel subwindow : subwindows) {
                    if (subwindow.getDiagramSubwindow().frameIsClicked(mouseEvent.getPoint())) {
                        changeActiveSubwindow(subwindow.getDiagramSubwindow());
                        return true;
                    }
                }
            }
        }
        return false;
    }*/


    private void createNewInteractionController(){
        InteractionController interactionController = new InteractionController();
        this.changeActiveInteractionController(interactionController);
    }

    private void checkForDeleteInteractionController(){
        if(activeInteractionController.getActiveDiagramSubwindow() == null){
            if(activeInteractionController.getSubwindows().isEmpty()){
                this.activeInteractionController = null;
            }
        }
        //getSubwindows() kan nog dialogboxes bevatten?
    }


    /**
     * creates a new diagramSubwindow with the correct level, adds it to the list of subwindows and sets it as active
     */
 /*   private void createNewSubwindow() {
        DiagramSubwindow diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100, 100), new InteractionMediator());
        Button button = new Button(new CloseSubwindowCommand(this, diagramSubwindow));
        diagramSubwindow.getFrame().setButton(button);
        int level = getCorrectLevel();
        addSubwindow(diagramSubwindow, level);
        this.changeActiveSubwindow(diagramSubwindow);
    }*/

    /**
     * copies the active diagramSubwindow, sets the correct level, adds it to the list of subwindows and sets it active
     */
   /* private void copyActiveSubWindow() {
        if (this.getActiveDiagramSubwindow() != null) {
            DiagramSubwindow diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100, 100), activeDiagramSubwindow.getCopyOfFacade(), activeDiagramSubwindow.getMediator());
            Button button = new Button(new CloseSubwindowCommand(this, diagramSubwindow));
            diagramSubwindow.getFrame().setButton(button);
            int level = getCorrectLevel();
            addSubwindow(diagramSubwindow, level);
            this.changeActiveSubwindow(diagramSubwindow);
        }
    }*/


    public InteractionController getAppropriateInteractionController(Point2D clickedLocation){
        InteractionController result = null;
        int level = -1;
        for(InteractionController ic : getInteractionControllers()){
            Subwindow s = ic.getAppropriateSubwindow(clickedLocation);
            if(s.getLevel() > level){
                result = ic;
                level = s.getLevel();
            }
        }
        return result;
    }
}
