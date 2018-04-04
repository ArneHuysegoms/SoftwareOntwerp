package diagram.party;

import diagram.Clickable;
import diagram.label.Label;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public abstract class Party{

    private String instanceName;
    private String className;
    private int positionInSequenceDiagram;
    private Label label;


    public Party(){

    }

    /**
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
     * @post  The new coordinate of this party is equal to the given coordinate
     *        | new.getCoordinate == coordinate
     *
     *
     */
    public Party( int positionInSequenceDiagram, Label label) throws DomainException{
        this("", "", positionInSequenceDiagram, label);
    }

    /**
     *
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
     * @post  The new coordinate of this party is equal to the given coordinate
     *        | new.getCoordinate == coordinate
     *
     *
     */
    public Party(String instanceName, String className, int positionInSequenceDiagram, Label label) throws DomainException{
        this.setLabel(label);
        this.setInstanceName(instanceName);
        this.setClassName(className);
        this.setPositionInSequenceDiagram(positionInSequenceDiagram);
    }

    /**
     * Return the instance name of this party
     */
    public String getInstanceName() {
        return instanceName;
    }

    private void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    /**
     * @param label
     *        The label belonging with this actor
     * @post  The new label of this party is equal to the given label
     *        | new.getLabel == label
     */
    private void setInstanceName(Label label){
        this.instanceName = label.getLabel().split("//:")[0];
    }

    /**
     * @return returns the classname of this Party
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className
     *        The className to set the party to
     * @post  The new className of this party is equal to the given className
     *        | new.getClassName == className
     */
    private void setClassName(String className) {
        this.className = className;
    }


    /**
     * @param label
     *        The full label that belongs tot the party
     * @post  The new className of this party is equal to the given className
     *        | new.getClassName == className
     */
    private void setClassName(Label label) {
        this.className = label.getLabel().split("//:")[1];
    }

    /**
     * @return Returns the postion of the party in the sequence diagram
     */
    public int getPositionInSequenceDiagram() {
        return positionInSequenceDiagram;
    }


    /**
     * @param positionInSequenceDiagram
     *        the position in the sequence diagram
     * @throws DomainException
     *       the postion of the party in sequenceDiagram must 0 or greater
     * @post  The new positionInSequenceDiagram of this party is equal to the given positionInSequenceDiagram
     *        | new.getPositionInSequenceDiagram == positionInSequenceDiagram
     */
    public void setPositionInSequenceDiagram(int positionInSequenceDiagram) throws DomainException {
        if (positionInSequenceDiagram < 0) {
            throw new DomainException("Position of actor in sequenceDiagram must 0 or greater");
        }
        this.positionInSequenceDiagram = positionInSequenceDiagram;
    }

    /**
     * @return  returns the full label
     */
    public String getFullLabel(){
        return this.getInstanceName() + ": " + this.getClassName();
    }

    /**
     * @param newLabel the label to edit to
     * @post  The new label of this party is equal to the given label
     *        | new.getLabel == label
     */
    public void editLabel(Label newLabel){
        this.label = newLabel;
    }

    /**
     * @return returns het label of this party
     */
    public Label getLabel() {
        return label;
    }

    /**
     * @param label the label to edit to
     * @post  The new label of this party is equal to the given label
     *        | new.getLabel == label
     */
    public void setLabel(Label label) {
        this.label = label;
    }

/*    *//*
     * method to find the correct location for the label of a Party
     *
     * @return a Point2D indicating the location
     *//*
    public abstract Point2D getCorrectLabelPosition();

    *//*
     * method to get the x location of the lifeline belonging to the party
     *
     * @return returns a double which denotes the x location of the lifeline belonging to the party
     *//*
    public abstract double getXLocationOfLifeline();*/


}
