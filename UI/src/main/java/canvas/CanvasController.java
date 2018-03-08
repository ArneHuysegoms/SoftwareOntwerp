package canvas;

import diagram.*;
import uievents.KeyEvent;
import uievents.MouseEvent;

/**
 * Main layer between pure UI and the domain
 *
 * Interprets UIEvents and addresses the appropriate functions in the diagram.
 */
public class CanvasController {

    private DomainFacade facade;

    /**
     * Construct a new basic CanvasController
     */
    public CanvasController(){
        this.setFacade(new DomainFacade());
    }

    public DomainFacade getFacade() {
        return facade;
    }

    private void setFacade(DomainFacade facade) {
        this.facade = facade;
    }

    /**
     * Reads a key event and alters the active diagram based on it
     *
     * @param keyEvent the KeyEvent that happened in the UI, comes from the InteractrCanvas
     */
    public void handleKeyEvent(KeyEvent keyEvent){
        if(checkIfValidLable()){
            this.getFacade().stopEditingLabel();
            switch (keyEvent.getKeyEventType()){
                case TAB:
                    this.getFacade().changeActiveDiagram();
                    break;
                case DEL:
                    this.getFacade().deleteElement();
                    break;
                case CHAR:
                    if(this.getFacade().selectedElementIsLabel()){
                        this.getFacade().addCharToLabel(keyEvent.getKeyChar());
                    }
                    break;
                case BACKSPACE:
                    this.getFacade().removeLastCharFromLabel();
                    break;
                default:
                    break;
            }
        }
        else{
          switch (keyEvent.getKeyEventType()){
              case CHAR:
                  if(this.getFacade().selectedElementIsLabel()){
                      this.getFacade().addCharToLabel(keyEvent.getKeyChar());
                  }
                  break;
              case BACKSPACE:
                  this.getFacade().removeLastCharFromLabel();
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
            this.getFacade().stopEditingLabel();
            switch (mouseEvent.getMouseEventType()){
                case DRAG:
                    if(this.getFacade().selectedElementIsParty()){
                        this.getFacade().changePartyPosition(mouseEvent.getPoint());
                    }
                    break;
                case PRESSED:
                    handleMousePressed(mouseEvent);
                    break;
                case RELEASE:
                    if(this.getFacade().selectedElementIsMessageStart()){
                        this.getFacade().addNewMessage(mouseEvent.getPoint());
                    }
                    break;
                case LEFTCLICK:
                    handleLeftClick(mouseEvent);
                    break;
                case LEFTDOUBLECLICK:
                    if(this.getFacade().selectedElementIsParty()){
                        this.getFacade().changePartyType(mouseEvent.getPoint());
                    }
                    if(this.getFacade().getSelectedElement() == null){
                        this.getFacade().addNewParty(mouseEvent.getPoint());
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
        return this.getFacade().checkIfValidLable();
    }

    /**
     * handle a left click on the UI
     *
     * @param mouseEvent the MouseEvent containing the information of the event
     */
    private void handleLeftClick(MouseEvent mouseEvent){
        Clickable selected = this.getFacade().getSelectedElement();
        Clickable newSelected = this.getFacade().findSelectedElement(mouseEvent.getPoint());
        if(selected != null) {
            if (selected.equals(newSelected) && this.getFacade().selectedElementIsLabel()) {
                this.getFacade().editLabel();
            }
        }
    }

    /**
     * Handles MouseEvents of type MouseEvent.Pressed
     *
     * @param mouseEvent the event to handle
     */
    private void handleMousePressed(MouseEvent mouseEvent){
        Clickable wouldBe = this.getFacade().wouldBeSelectedElement(mouseEvent.getPoint());
        if(! this.getFacade().isLabel(wouldBe)){
            this.getFacade().setSelectedElement(wouldBe);
        }
    }
}
