package canvascomponents.diagram;

import excpetions.DomainException;

import java.awt.geom.Point2D;

public class Actor extends Party {

    public Actor(){
        super();
    }

    public Actor(String instanceName, String className, int positionInSequenceDiagram, Point2D positionInCommunicationsDiagram) throws DomainException{
        super(instanceName, className, positionInSequenceDiagram, positionInCommunicationsDiagram);
    }
}
