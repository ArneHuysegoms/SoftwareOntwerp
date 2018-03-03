package canvascomponents.diagram;

import canvascomponents.Clickable;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public class InvocationMessage extends Message implements Clickable {

    public InvocationMessage(){

    }

    public InvocationMessage(Message message, MessageLabel label, Actor receiver, Actor sender, int yLocation) throws DomainException{
        super(message, label, receiver, sender, yLocation);
    }
}
