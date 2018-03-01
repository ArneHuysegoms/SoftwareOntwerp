package canvascomponents.diagram;

import exceptions.DomainException;

import java.awt.geom.Point2D;

public class Actor extends Party {

    private Lifeline lifeline;

    public Actor(){
        super();
    }

    public Actor(String instanceName, String className, int positionInSequenceDiagram, Point2D coordinate, Lifeline lifeline) throws DomainException{
        super(instanceName, className, positionInSequenceDiagram, coordinate);
        this.setLifeline(lifeline);
    }

    public Lifeline getLifeline() {
        return lifeline;
    }

    private void setLifeline(Lifeline lifeline) {
        this.lifeline = lifeline;
    }
}
