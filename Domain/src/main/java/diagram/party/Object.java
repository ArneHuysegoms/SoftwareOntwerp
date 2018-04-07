package diagram.party;

import diagram.label.Label;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public class Object extends Party {

    public Object(){
        super();
    }


    /**
     * @param positionInSequenceDiagram
     *        The position where this object is located within the sequence diagram
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
    public Object( int positionInSequenceDiagram, Label label) throws DomainException{
        this("", "", positionInSequenceDiagram, label);
    }


    /**
     * /**
     * @param instanceName
     *        The instance name for this actor
     * @param className
     *        The class name for this actor
     * @param positionInSequenceDiagram
     *        The position where this actor is located within the sequence diagram
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
    public Object(String instanceName, String className, int positionInSequenceDiagram, Label label) throws DomainException {
        super(instanceName, className, positionInSequenceDiagram, label);
    }
}
