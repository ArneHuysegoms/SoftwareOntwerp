package canvascomponents.diagram;

import exceptions.DomainException;

import java.awt.geom.Point2D;

public class InvocationMessage extends Message {

    public InvocationMessage(){

    }

    public InvocationMessage(Message message, String label, Actor receiver, Actor sender, Point2D coordinate) throws DomainException{
        super(message, label, receiver, sender, coordinate);
    }
}
