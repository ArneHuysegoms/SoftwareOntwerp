package canvascomponents.diagram;

import exceptions.DomainException;

import java.awt.geom.Point2D;

public class ResultMessage extends Message {

     public ResultMessage(){

     }

     public ResultMessage(Message message, MessageLabel label, Actor receiver, Actor sender, int yLocation) throws DomainException {
         super(message, label, receiver, sender, yLocation);
     }

    @Override
    public boolean isClicked(Point2D point2D) {
        return false;
    }
}
