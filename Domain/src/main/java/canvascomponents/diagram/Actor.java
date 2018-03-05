package canvascomponents.diagram;

import canvascomponents.Clickable;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public class Actor extends Party{
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;


    public Actor(){
        super();
    }

    /**
     * @param positionInSequenceDiagram
     *        The position where this actor is located within the sequence diagram
     * @param point2D
     *        The coordinates of the middle, most upper point of this actor
     * @param label
     *        The label belonging with this actor
     * @throws DomainException
     *         This Actor cannot have the given instanceName, className, positionInSequenceDiagram, coordinate or label
     * @post  The new label of this party is equal to the given label
     *        | new.getLabel == label
     * @post  The new className of this party is equal to the given className
     *        | new.getClassName == className
     * @post  The new positionInSequenceDiagram of this party is equal to the given positionInSequenceDiagram
     *        | new.getPositionInSequenceDiagram == positionInSequenceDiagram
     * @post  The new coordinate of this party is equal to the given coordinate
     *        | new.getCoordinate == coordinate
     *
     *
     */

    public Actor( int positionInSequenceDiagram, Point2D point2D, PartyLabel label) throws DomainException{
        this("", "", positionInSequenceDiagram, point2D, label);
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
     *        | new.getInstanceName = instanceName
     * @post  The new className of this instance is equal to the given instanceName
     *        | new.getInstanceName = instanceName
     * @post  The new label of this party is equal to the given label
     *        | new.getLabel == label
     * @post  The new positionInSequenceDiagram of this party is equal to the given positionInSequenceDiagram
     *        | new.getPositionInSequenceDiagram == positionInSequenceDiagram
     * @post  The new coordinate of this party is equal to the given coordinate
     *        | new.getCoordinate == coordinate
     */
    public Actor(String instanceName, String className, int positionInSequenceDiagram, Point2D coordinate, Label label) throws DomainException{
        super(instanceName, className, positionInSequenceDiagram, coordinate, label);
    }

    /**
     * @param point2D
     *        The coordinates of the mouse where the user clicked
     * @return
     *        True if the clicked coordinates are within the coordinates of the image of this actor
     */
    @Override
    public boolean isClicked(Point2D point2D) {
        double clickX = point2D.getX();
        double clickY = point2D.getY();
        double startX = this.getCoordinate().getX() - WIDTH/2;
        double startY = this.getCoordinate().getY();
        double endX = startX + WIDTH;
        double endY = startY + HEIGHT;
        return (clickX >= startX && clickX <= endX) && (clickY >= startY && clickY <= endY);
    }

    /**
     * method to find the correct location for the label of a Party
     *
     * @return a Point2D indicating the location
     */
    @Override
    public Point2D getCorrectLabelPosition() {
        return new Point2D.Double(this.getCoordinate().getX() - 10, this.getCoordinate().getY() + 50);
    }

    /**
     * @param point2D
     *        The coordinates of the mouse where the user clicked
     * @return
     *       returns the distance between the coordinate of this actor and the given point
     */
    @Override
    public double getDistance(Point2D point2D) {
        return this.getCoordinate().distance(point2D);
    }
}
