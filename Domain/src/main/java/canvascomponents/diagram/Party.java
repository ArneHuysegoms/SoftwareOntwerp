package canvascomponents.diagram;

import excpetions.DomainException;

import java.awt.geom.Point2D;

public class Party {

    private String instanceName;
    private String className;
    private int positionInSequenceDiagram;
    private Point2D positionInCommunicationDiagram;

    public Party(){

    }

    public Party(String instanceName, String className, int positionInSequenceDiagram, Point2D positionInCommunicationDiagram) throws DomainException{
        this.setInstanceName(instanceName);
        this.setClassName(className);
        this.setPositionInSequenceDiagram(positionInSequenceDiagram);
        this.setPositionInCommunicationDiagram(positionInCommunicationDiagram);
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

    public Point2D getPositionInCommunicationDiagram() {
        return positionInCommunicationDiagram;
    }

    private void setPositionInCommunicationDiagram(Point2D positionInCommunicationDiagram) {
        this.positionInCommunicationDiagram = positionInCommunicationDiagram;
    }

    public String getFullLabel(){
        return this.getInstanceName() + ": " + this.getClassName();
    }
}
