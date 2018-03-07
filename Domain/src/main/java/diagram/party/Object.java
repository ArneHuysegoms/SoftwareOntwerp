package diagram.party;

import diagram.label.Label;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public class Object extends Party {

    public static final int WIDTH = 80;
    public static final int HEIGHT = 50;

    public Object(){
        super();
    }


    /**
     * @param positionInSequenceDiagram
     *        The position where this object is located within the sequence diagram
     * @param point2D
     *        The coordinates of the middle, most upper point of this object
     * @param label
     *        The label belonging with this object
     * @throws DomainException
     *         This object cannot have the given positionInSequenceDiagram, coordinate or label
     * @post  The new label of this party is equal to the given label
     *        | new.getLabel == label
     * @post  The new positionInSequenceDiagram of this party is equal to the given positionInSequenceDiagram
     *        | new.getPositionInSequenceDiagram == positionInSequenceDiagram
     * @post  The new coordinate of this party is equal to the given coordinate
     *        | new.getCoordinate == coordinate
     *
     *
     */

    public Object( int positionInSequenceDiagram, Point2D point2D, Label label) throws DomainException{
        this("", "", positionInSequenceDiagram, point2D, label);
    }


    /**
     * /**
     * @param instanceName
     *        The instance name for this actor
     * @param className
     *        The class name for this actor
     * @param positionInSequenceDiagram
     *        The position where this actor is located within the sequence diagram
     * @param positionInCommunicationsDiagram
     *        The position where this actor is located within the communications diagram
     * @param label
     *        The label belonging with this actor
     * @throws DomainException
     *         This Actor cannot have the given instanceName, className, positionInSequenceDiagram, coordinate or label
     * @post  The new instanceName of this instance is equal to the given instanceName
     *        | new.getInstanceName == instanceName;
     * @post  The new label of this party is equal to the given label
     *        | new.getLabel == label
     * @post  The new className of this party is equal to the given className
     *        | new.getClassName == className
     * @post  The new positionInSequenceDiagram of this party is equal to the given positionInSequenceDiagram
     *        | new.getPositionInSequenceDiagram == positionInSequenceDiagram
     * @post  The new positionInCommunicationDiagram of this party is equal to the given positionInCommunicationDiagram
     *        | new.getPostionInCommunicationsDiagram == postionInCommunicationsDiagram
     *
     *
     */

    public Object(String instanceName, String className, int positionInSequenceDiagram, Point2D positionInCommunicationsDiagram, Label label) throws DomainException {
        super(instanceName, className, positionInSequenceDiagram, positionInCommunicationsDiagram, label);
    }

    /**
     * @param point2D
     *        The coordinates of the mouse where the user clicked
     * @return
     *        True if the clicked coordinates are within the coordinates of the image of this message
     */
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

    /**
     * method to find the correct location for the label of a Party
     *
     * @return a Point2D indicating the location
     */
    @Override
    public Point2D getCorrectLabelPosition() {
        return new Point2D.Double(this.getCoordinate().getX() + 5, this.getCoordinate().getY() + 25);
    }

    @Override
    public double getXLocationOfLifeline() {
        return this.getCoordinate().getX() + WIDTH/2;
    }

    /**
     * @param point2D
     *        The coordinates of the mouse where the user clicked
     * @return
     *       returns the distance between the coordinate of this message and the given point
     */
    @Override
    public double getDistance(Point2D point2D) {
        return this.getCoordinate().distance(point2D);
    }
}
