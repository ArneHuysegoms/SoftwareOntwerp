package canvas;

import canvascomponents.Clickable;
import canvascomponents.diagram.*;
import uievents.KeyEvent;
import uievents.MouseEvent;

import java.awt.geom.Point2D;

/**
 * Main layer between pure UI and the domain
 *
 * Interprets UIEvents and addresses the appropriate functions in the diagram.
 */
public class CanvasMakeUp {

    private Diagram activeDiagram;

    private Diagram previousDiagram;

    /**
     * Construct a new basic CanvasMakeUp
     */
    public CanvasMakeUp(){
        activeDiagram = new SequenceDiagram();
    }

    /**
     *
     * @return the diagram that is the active view at the moment
     */
    public Diagram getActiveDiagram() {
        return activeDiagram;
    }

    /**
     *
     * @param activeDiagram
     *          the diagram that should beome the active diagram
     */
    private void setActiveDiagram(Diagram activeDiagram) {
        this.activeDiagram = activeDiagram;
    }

    /**
     * Change the active diagram to a diagram of the other type
     */
    public void changeActiveDiagram(){
        if(this.getActiveDiagram() instanceof SequenceDiagram){
            Diagram communication = new CommunicationsDiagram(activeDiagram.getParties(), activeDiagram.getFirstMessage(), activeDiagram.getSelectedElement(),
                    activeDiagram.getLabelContainer(), activeDiagram.isLabelMode(), activeDiagram.isValidLabel(), activeDiagram.isMessageMode());
            if(previousDiagram != null ) {
                communication.resetPartyPositions(previousDiagram.getParties());
            }
            this.previousDiagram = activeDiagram;
            activeDiagram = communication;
        }
        else{
            Diagram sequence =  new SequenceDiagram(activeDiagram.getParties(), activeDiagram.getFirstMessage(), activeDiagram.getSelectedElement(),
                    activeDiagram.getLabelContainer(), activeDiagram.isLabelMode(), activeDiagram.isValidLabel(), activeDiagram.isMessageMode());
            sequence.resetToSequencePositions();
            this.previousDiagram = activeDiagram;
            activeDiagram = sequence;
        }
    }

    /**
     * Reads a key event and alters the active diagram based on it
     *
     * @param keyEvent the KeyEvent that happened in the UI, comes from the InteractrCanvas
     */
    public void handleKeyEvent(KeyEvent keyEvent){
        if(checkIfValidLable()){
            switch (keyEvent.getKeyEventType()){
                case TAB:
                    this.changeActiveDiagram();
                    break;
                case DEL:
                    this.getActiveDiagram().deleteElement();
                    break;
                case CHAR:
                case COLON:
                    if(getActiveDiagram().getSelectedElement() instanceof Label){
                        getActiveDiagram().addCharToLabel(keyEvent.getKeyChar());
                    }
                    break;
                case BACKSPACE:
                    this.getActiveDiagram().removeLastCharFromLabel();
                default:
                    break;
            }
        }
        else{
          switch (keyEvent.getKeyEventType()){
              case CHAR:
              case COLON:
                  if(getActiveDiagram().getSelectedElement() instanceof Label){
                      getActiveDiagram().addCharToLabel(keyEvent.getKeyChar());
                  }
                  break;
              case BACKSPACE:
                  this.getActiveDiagram().removeLastCharFromLabel();
              default:
                  break;
          }
        }
    }

    /**
     * Reads a mouse event and alters the active diagram based on it
     *
     * @param mouseEvent the MouseEvent that happened in the UI, comes from the InteractrCanvas
     */
    public void handleMouseEvent(MouseEvent mouseEvent){
        if(checkIfValidLable()){
            this.getActiveDiagram().stopEditingLabel();
            switch (mouseEvent.getMouseEventType()){
                case DRAG:
                    if(this.getActiveDiagram().getSelectedElement() instanceof Party){
                        this.getActiveDiagram().changePartyPosition(mouseEvent.getPoint());
                    }
                    break;
                case RELEASE:
                    if(this.getActiveDiagram().getSelectedElement() instanceof Party){
                        this.getActiveDiagram().changePartyPosition(mouseEvent.getPoint());
                    }
                    else if(this.getActiveDiagram().getSelectedElement() instanceof Diagram.MessageStart){
                        this.getActiveDiagram().addNewMessage(mouseEvent.getPoint());
                    }
                    break;
                case LEFTCLICK:
                    handleLeftClick(mouseEvent);
                    break;
                case LEFTDOUBLECLICK:
                    this.getActiveDiagram().changePartyPosition(mouseEvent.getPoint());
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * checks if the active diagram is in LabelMode, which will block some of the functionality
     *
     * @return true if the active diagram is in labelMode, false otherwise
     */
    private boolean checkIfInLabelMode(){
        return this.activeDiagram.isLabelMode();
    }

    /**
     *
     * @return true if the label in edit is valid, false otherwise
     */
    private boolean checkIfValidLable(){
        return this.getActiveDiagram().isValidLabel();
    }

    /**
     * handle a left click on the UI
     *
     * @param mouseEvent the MouseEvent containing the information of the event
     */
    private void handleLeftClick(MouseEvent mouseEvent){
        Clickable selected = activeDiagram.getSelectedElement();
        Clickable newSelected = activeDiagram.findSelectedElement(mouseEvent.getPoint());
        if(selected.equals(newSelected) && selected instanceof Label){
            this.getActiveDiagram().editLabel();
        }
    }
}
