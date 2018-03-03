package canvascomponents.diagram;

import exceptions.DomainException;

import java.awt.geom.Point2D;

public class Object extends Party{

    public static final int WIDTH = 80;
    public static final int HEIGHT = 50;

    public Object(){
        super();
    }

    public Object( int positionInSequenceDiagram, Point2D point2D, PartyLabel label) throws DomainException{
        this("", "", positionInSequenceDiagram, point2D, label);
    }

    public Object(String instanceName, String className, int positionInSequenceDiagram, Point2D positionInCommunicationsDiagram, PartyLabel label) throws DomainException {
        super(instanceName, className, positionInSequenceDiagram, positionInCommunicationsDiagram, label);
    }
    @Override
    public boolean isClicked(Point2D point2D) {
        double clickX = point2D.getX();
        double clickY = point2D.getY();
        double startX = this.getCoordinate().getX();
        double startY = this.getCoordinate().getY();
        double endX = startX + WIDTH;
        double endY = startY + HEIGHT;
        return (clickX >= startX && clickX <= endX) && (clickY >= startY && clickY <= endY);
    }

    @Override
    public double getDistance(Point2D point2D) {
        return this.getCoordinate().distance(point2D);
    }
}
