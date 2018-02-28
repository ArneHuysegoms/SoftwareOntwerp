package canvascomponents.diagram;

import exceptions.DomainException;

import java.awt.geom.Point2D;

public class Object extends Party {

    public Object(){
        super();
    }

    public Object(String instanceName, String className, int positionInSequenceDiagram, Point2D positionInCommunicationsDiagram) throws DomainException {
        super(instanceName, className, positionInSequenceDiagram, positionInCommunicationsDiagram);
    }
}
