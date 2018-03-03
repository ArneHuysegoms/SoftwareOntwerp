package canvascomponents.diagram;

import canvascomponents.Clickable;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public class Party{

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

    public Party(String instanceName, String className, int positionInSequenceDiagram, Point2D coordinate, PartyLabel label) throws DomainException{
        this.setLabel(label);
        this.setInstanceName(instanceName);
        this.setClassName(className);
        this.setPositionInSequenceDiagram(positionInSequenceDiagram);
        this.setCoordinate(coordinate);
    }

    public Point2D getCoordinate() {
        return coordinate;
    }


    public String getInstanceName() {
        return instanceName;
    }

    private void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getClassName() {
        return className;
    }

    private void setClassName(String className) {
        this.className = className;
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
}
