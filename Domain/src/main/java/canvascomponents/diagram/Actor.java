package canvascomponents.diagram;

import canvascomponents.Clickable;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public class Actor extends Party implements Clickable {

    private Lifeline lifeline;
    public static final int width = 160;
    public static final int height = 160;


    public Actor(){
        super();
    }

    public Actor(String instanceName, String className, int positionInSequenceDiagram, Point2D coordinate, Lifeline lifeline, PartyLabel label) throws DomainException{
        super(instanceName, className, positionInSequenceDiagram, coordinate, label);
        this.setLifeline(lifeline);
    }

    public Lifeline getLifeline() {
        return lifeline;
    }

    private void setLifeline(Lifeline lifeline) {
        this.lifeline = lifeline;
    }

    @Override
    public boolean isClicked(Point2D point2D) {
        double clickX = point2D.getX();
        double clickY = point2D.getY();
        double startX = this.getCoordinate().getX();
        double startY = this.getCoordinate().getY();
        double endX = startX + width;
        double endY = startY + height;
        return (clickX >= startX && clickX <= endX) && (clickY >= startY && clickY <= endY);
    }
}
