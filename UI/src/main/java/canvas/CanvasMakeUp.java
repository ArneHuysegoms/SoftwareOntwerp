package canvas;

import canvascomponents.diagram.CommunicationsDiagram;
import canvascomponents.diagram.Diagram;
import canvascomponents.diagram.SequenceDiagram;
import uievents.KeyEvent;
import uievents.MouseEvent;

import java.awt.geom.Point2D;

public class CanvasMakeUp {

    private Diagram activeDiagram;

    private Diagram communicationDiagram;
    private Diagram sequenceDiagram;

    public CanvasMakeUp(){
        communicationDiagram = new CommunicationsDiagram();
        communicationDiagram.addNewParty(new Point2D.Double(25, 25));

        sequenceDiagram = new SequenceDiagram();
        sequenceDiagram.addNewParty(new Point2D.Double(25, 51));

        activeDiagram = sequenceDiagram;

    }

    public Diagram getActiveDiagram() {
        return activeDiagram;
    }

    private void setActiveDiagram(Diagram activeDiagram) {
        this.activeDiagram = activeDiagram;
    }

    public void changeActiveDiagram(){
        if(activeDiagram == communicationDiagram){
            activeDiagram = sequenceDiagram;
        }
        else{
            activeDiagram = communicationDiagram;
        }
    }

    public void handleKeyEvent(KeyEvent keyEvent){

    }

    public void handleMouseEvent(MouseEvent mouseEvent){
        if(! checkIfInLabelMode()){

        }
    }

    private boolean checkIfInLabelMode(){
        return this.activeDiagram.isLabelMode();
    }
}
