package canvascomponents.diagram;

import canvascomponents.Clickable;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public class Message implements Clickable{

    private Message nextMessage;
    private MessageLabel label;
    private Actor receiver;
    private Actor sender;
    private Point2D coordinate;
    private int width;
    public static final int height = 50;

    public Point2D getCoordinate() {
        return coordinate;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Message(){

    }

    public Message(Message nextMessage, MessageLabel label, Actor receiver, Actor sender, Point2D coordinate, int width) throws DomainException{
        this.setNextMessage(nextMessage);
        this.setLabel(label);
        this.setReceiver(receiver);
        this.setSender(sender);
        this.setCoordinate(coordinate);
        this.setWidth(width);
    }

    public Message getNextMessage() {
        return nextMessage;
    }

    private void setNextMessage(Message nextMessage) {
        this.nextMessage = nextMessage;
    }

    public MessageLabel getLabel() {
        return label;
    }

    private void setLabel(MessageLabel label) {
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
