package canvascomponents.diagram;

import canvascomponents.Coordinable;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public class Message implements Coordinable{

    private Message nextMessage;
    private String label;
    private Actor receiver;
    private Actor sender;
    private Point2D coordinate;

    public Message(){

    }

    public Message(Message nextMessage, String label, Actor receiver, Actor sender, Point2D coordinate) throws DomainException{
        this.setNextMessage(nextMessage);
        this.setLabel(label);
        this.setReceiver(receiver);
        this.setSender(sender);
        this.setCoordinate(coordinate);
    }

    public Message getNextMessage() {
        return nextMessage;
    }

    private void setNextMessage(Message nextMessage) {
        this.nextMessage = nextMessage;
    }

    public String getLabel() {
        return label;
    }

    private void setLabel(String label) {
        this.label = label;
    }

    public Actor getReceiver() {
        return receiver;
    }

    private void setReceiver(Actor receiver) {
        this.receiver = receiver;
    }

    public Actor getSender() {
        return sender;
    }

    private void setSender(Actor sender) throws DomainException{
        if(sender == null){
            throw new DomainException("Sender of message can't be null");
        }
        this.sender = sender;
    }

    private void setCoordinate(Point2D coordinate){
        this.coordinate = coordinate;
    }


    @Override
    public Point2D getCoordinate() {
        return this.coordinate;
    }
}
