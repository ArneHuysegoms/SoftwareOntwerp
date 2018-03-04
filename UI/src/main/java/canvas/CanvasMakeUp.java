package canvas;

import canvascomponents.diagram.Diagram;
import canvascomponents.diagram.SequenceDiagram;
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
        activeDiagram.addNewParty(new Point2D.Double(25, 51));
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
     *
     * //TODO
     */
    public void changeActiveDiagram(){

    }

    /**
     * Reads a key event and alters the active diagram based on it
     *
     * @param keyEvent the KeyEvent that happened in the UI, comes from the InteractrCanvas
     */
    public void handleKeyEvent(KeyEvent keyEvent){

    }

    /**
     * Reads a mouse event and alters the active diagram based on it
     *
     * @param mouseEvent the MouseEvent that happened in the UI, comes from the InteractrCanvas
     */
    public void handleMouseEvent(MouseEvent mouseEvent){
        if(! checkIfInLabelMode()){
            switch (mouseEvent.getMouseEventType()){
                case DRAG:
                    break;
                case RELEASE:
                    break;
                case LEFTCLICK:

                    break;
                case LEFTDOUBLECLICK:
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
}
