package canvascomponents.diagram;

import canvascomponents.Clickable;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public class Object extends Party implements Clickable {

    public static final int width = 200;
    public static final int height = 140;

    public Object(){
        super();
    }

    public Object(String instanceName, String className, int positionInSequenceDiagram, Point2D positionInCommunicationsDiagram, PartyLabel label) throws DomainException {
        super(instanceName, className, positionInSequenceDiagram, positionInCommunicationsDiagram, label);
    }
    @Override
    public boolean isClicked(Point2D point2D) {
        double clickX = point2D.getX();
        double clickY = point2D.getY();
        double startX = this.getCoordinate().getX();
        double startY = this.getCoordinate().getY();
        double endX = startX + this.width;
        double endY = startY + this.height;
        if ((clickX >= startX && clickX <= endX) && (clickY >= startY && clickY <= endY))
            return true;
        return false;
    }
}
