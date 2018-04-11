package diagram.party;

import diagram.DiagramElement;
import diagram.label.Label;
import exceptions.DomainException;

public abstract class Party extends DiagramElement {

    private String instanceName;
    private String className;
    private Label label;


    public Party(){

    }

    /**
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
     * @post  The new coordinate of this party is equal to the given coordinate
     *        | new.getCoordinate == coordinate
     *
     *
     */
    public Party(Label label) throws DomainException{
        this("", "", label);
    }

    /**
     *
     * @param instanceName
     *        The instance name for this actor
     * @param className
     *        The class name for this actor
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
     * @post  The new coordinate of this party is equal to the given coordinate
     *        | new.getCoordinate == coordinate
     *
     *
     */
    public Party(String instanceName, String className, Label label) throws DomainException{
        this.setLabel(label);
        this.setInstanceName(instanceName);
        this.setClassName(className);
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
