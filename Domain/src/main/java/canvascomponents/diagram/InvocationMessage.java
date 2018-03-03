package canvascomponents.diagram;

import canvascomponents.Clickable;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public class InvocationMessage extends Message implements Clickable {

    public InvocationMessage(){

    }

    public InvocationMessage(Message message, MessageLabel label, Actor receiver, Actor sender, Point2D coordinate, int width) throws DomainException{
        super(message, label, receiver, sender, coordinate, width);
    }

    @Override
    public boolean isClicked(Point2D point2D) {
        double clickX = point2D.getX();
        double clickY = point2D.getY();
        double startX = this.getCoordinate().getX();
        double startY = this.getCoordinate().getY();
        double endX = startX + this.getWidth();
        double endY = startY + height;
        return (clickX >= startX && clickX <= endX) && (clickY >= startY && clickY <= endY);
    }
}
