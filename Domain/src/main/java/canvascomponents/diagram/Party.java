package canvascomponents.diagram;

import canvascomponents.Clickable;
import canvascomponents.Coordinable;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public class Party implements Coordinable, Clickable{

    private String instanceName;
    private String className;
    private int positionInSequenceDiagram;
    private Point2D coordinate;

    public Party(){

    }

    public Party(String instanceName, String className, int positionInSequenceDiagram, Point2D coordinate) throws DomainException{
        this.setInstanceName(instanceName);
        this.setClassName(className);
        this.setPositionInSequenceDiagram(positionInSequenceDiagram);
        this.setCoordinate(coordinate);
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

    @Override
    public Point2D getCoordinate() {
        return this.coordinate;
    }

    @Override
    public boolean isClicked(Point2D point2D) {
        return false;
    }
}
