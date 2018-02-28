package canvascomponents.diagram;

import exceptions.DomainException;

import java.awt.geom.Point2D;

public class ResultMessage extends Message {

     public ResultMessage(){

     }

     public ResultMessage(Message message, String label, Actor receiver, Actor sender, Point2D coordinate) throws DomainException {
         super(message, label, receiver, sender, coordinate);
     }
}
