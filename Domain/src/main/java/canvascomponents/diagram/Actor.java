package canvascomponents.diagram;

import canvascomponents.Clickable;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public class Actor extends Party{

    private Lifeline lifeline;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;


    public Actor(){
        super();
    }

    public Actor( int positionInSequenceDiagram, Point2D point2D, PartyLabel label) throws DomainException{
        this("", "", positionInSequenceDiagram, point2D, null,label);
    }


    /**
     * @param instanceName
     *        The instance name for this actor
     * @param className
     *        The class name for this actor
     * @param positionInSequenceDiagram
     *        The position where this actor is located within the sequence diagram
     * @param coordinate
     *        The coordinates of the left upmost point of this actor
     * @param label
     *        The label belonging with this actor
     * @post  The new instanceName of this instance is equal to the given instanceName
     *        | new.getInstceName = instanceName
     */
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
        double startX = this.getCoordinate().getX() - WIDTH/2;
        double startY = this.getCoordinate().getY();
        double endX = startX + WIDTH/2;
        double endY = startY + HEIGHT;
        return (clickX >= startX && clickX <= endX) && (clickY >= startY && clickY <= endY);
    }

    @Override
    public double getDistance(Point2D point2D) {
        return this.getCoordinate().distance(point2D);
    }
}
