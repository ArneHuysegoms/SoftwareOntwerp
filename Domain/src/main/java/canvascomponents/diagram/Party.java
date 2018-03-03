package canvascomponents.diagram;

import canvascomponents.Clickable;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public abstract class Party implements Clickable{

    private String instanceName;
    private String className;
    private int positionInSequenceDiagram;
    private Point2D coordinate;
    private PartyLabel label;
    /*private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
*/


    public Party(){

    }

    /**
     * /**
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
    public Party(String instanceName, String className, int positionInSequenceDiagram, Point2D coordinate, PartyLabel label) throws DomainException{
        this.setLabel(label);
        this.setInstanceName(instanceName);
        this.setClassName(className);
        this.setPositionInSequenceDiagram(positionInSequenceDiagram);
        this.setCoordinate(coordinate);
    }

    /**
     * Return the left upmost coordinate of this party
     */
    public Point2D getCoordinate() {
        return coordinate;
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
    private void setInstanceName(PartyLabel label){
        this.instanceName = label.getLabel().split("//:")[0];
    }

    public String getClassName() {
        return className;
    }

    private void setClassName(String className) {
        this.className = className;
    }

    private void setClassName(PartyLabel label) {
        this.className = label.getLabel().split("//:")[1];
    }

    public int getPositionInSequenceDiagram() {
        return positionInSequenceDiagram;
    }

    private void setPositionInSequenceDiagram(int positionInSequenceDiagram) throws DomainException{
        if(positionInSequenceDiagram < 0){
            throw new DomainException("Position of actor in sequenceDiagram must 0 or greater");
        }
        this.positionInSequenceDiagram = positionInSequenceDiagram;
    }

    public void setCoordinate(Point2D coordinate) {
        this.coordinate = coordinate;
    }

    public String getFullLabel(){
        return this.getInstanceName() + ": " + this.getClassName();
    }

    public void editLabel(PartyLabel newLabel){
        this.label = newLabel;
    }


    public Label getLabel() {
        return label;
    }

    public void setLabel(PartyLabel label) {
        this.label = label;
    }

    @Override
    public abstract boolean isClicked(Point2D point2D);


}
