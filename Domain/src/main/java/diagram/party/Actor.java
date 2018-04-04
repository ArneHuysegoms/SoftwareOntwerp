package diagram.party;

import diagram.label.Label;
import diagram.label.PartyLabel;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public class Actor extends Party {


    public Actor(){
        super();
    }

    /**
     * @param positionInSequenceDiagram
     *        The position where this actor is located within the sequence diagram
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
    public Actor( int positionInSequenceDiagram, PartyLabel label) throws DomainException{
        this("", "", positionInSequenceDiagram, label);
    }


    /**
     * @param instanceName
     *        The instance name for this actor
     * @param className
     *        The class name for this actor
     * @param positionInSequenceDiagram
     *        The position where this actor is located within the sequence diagram
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
    public Actor(String instanceName, String className, int positionInSequenceDiagram, Label label) throws DomainException{
        super(instanceName, className, positionInSequenceDiagram, label);
    }

    /*
     * method to find the correct location for the label of a Party
     *
     * @return a Point2D indicating the location
     *//*
    @Override
    public Point2D getCorrectLabelPosition() {
        return new Point2D.Double(this.getCoordinate().getX() - 10, this.getCoordinate().getY() + 50);
    }

    @Override
    public double getXLocationOfLifeline() {
        return this.getCoordinate().getX();
    }*/

    /*
     * @param point2D
     *        The coordinates of the mouse where the user clicked
     * @return
     *       returns the distance between the coordinate of this actor and the given point
     @Override
     public double getDistance(Point2D point2D) {
     return this.getCoordinate().distance(point2D);
     }*/
}
