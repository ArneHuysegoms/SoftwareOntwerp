package canvas;

import diagram.Clickable;
import diagram.CommunicationsDiagram;
import diagram.Diagram;
import diagram.SequenceDiagram;
import diagram.label.Label;
import uievents.KeyEvent;
import uievents.MouseEvent;
import uievents.MouseEventType;

/**
 * Main layer between pure UI and the domain
 *
 * Interprets UIEvents and addresses the appropriate functions in the diagram.
 */
public class CanvasController {

    private Diagram activeDiagram;

    private Diagram previousDiagram;

    /**
     * Construct a new basic CanvasController
     */
    public CanvasController(){
        setActiveDiagram(new SequenceDiagram());
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
     *          the diagram that should become the active diagram
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
        } else{
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
            this.getActiveDiagram().stopEditingLabel();
            switch (keyEvent.getKeyEventType()){
                case TAB:
                    this.changeActiveDiagram();
                    break;
                case DEL:
                    this.getActiveDiagram().deleteElement();
                    break;
                case CHAR:
                    if(getActiveDiagram().selectedElementIsLabel()){
                        getActiveDiagram().addCharToLabel(keyEvent.getKeyChar());
                    }
                    break;
                case BACKSPACE:
                    this.getActiveDiagram().removeLastCharFromLabel();
                    break;
                default:
                    break;
            }
        }
        else{
          switch (keyEvent.getKeyEventType()){
              case CHAR:
                  if(getActiveDiagram().selectedElementIsLabel()){
                      getActiveDiagram().addCharToLabel(keyEvent.getKeyChar());
                  }
                  break;
              case BACKSPACE:
                  this.getActiveDiagram().removeLastCharFromLabel();
                  break;
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
                    if(this.getActiveDiagram().selectedElementIsParty()){
                        this.getActiveDiagram().changePartyPosition(mouseEvent.getPoint());
                    }
                    break;
                case PRESSED:
                    handleMousePressed(mouseEvent);
                    break;
                case RELEASE:
                    if(this.getActiveDiagram().selectedElementIsMessageStart()){
                        this.getActiveDiagram().addNewMessage(mouseEvent.getPoint());
                    }
                    break;
                case LEFTCLICK:
                    handleLeftClick(mouseEvent);
                    break;
                case LEFTDOUBLECLICK:
                    if(this.getActiveDiagram().selectedElementIsParty()){
                        getActiveDiagram().changePartyType(mouseEvent.getPoint());
                    }
                    if(getActiveDiagram().getSelectedElement() == null){
                        getActiveDiagram().addNewParty(mouseEvent.getPoint());
                    }
                    break;
                default:
                    break;
            }
        }
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
        if(selected != null) {
            if (selected.equals(newSelected) && this.getActiveDiagram().selectedElementIsLabel()) {
                this.getActiveDiagram().editLabel();
            }
        }
    }

    /**
     * Handles MouseEvents of type MouseEvent.Pressed
     *
     * @param mouseEvent the event to handle
     */
    private void handleMousePressed(MouseEvent mouseEvent){
        Clickable wouldBe = this.getActiveDiagram().wouldBeSelectedElement(mouseEvent.getPoint());
        if(! getActiveDiagram().isLabel(wouldBe)){
            getActiveDiagram().setSelectedElement(wouldBe);
        }
    }
}
