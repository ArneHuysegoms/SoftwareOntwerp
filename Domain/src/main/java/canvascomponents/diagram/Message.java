package canvascomponents.diagram;

import canvascomponents.Clickable;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public class Message implements Clickable{

    private Message nextMessage;
    private MessageLabel label;
    private Actor receiver;
    private Actor sender;

    public static final int HEIGHT = 16;


    public Message(){

    }

    public Message(Message nextMessage, MessageLabel label, Actor receiver, Actor sender) throws DomainException{
        this.setNextMessage(nextMessage);
        this.setLabel(label);
        this.setReceiver(receiver);
        this.setSender(sender);
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

    @Override
    public boolean isClicked(Point2D point2D) {
        Point2D senderLocation = Diagram.getActorLocation(sender);
        Point2D receiverLocation = Diagram.getReceiverLocation(receiver);
        Point2D messageLocation = Diagram.getMessageLocation(this);


        double clickX = point2D.getX();
        double clickY = point2D.getY();
        double startX = senderLocation.getX();
        double startY = messageLocation.getY() - HEIGHT/2;
        double endX = receiverLocation.getX();
        double endY = messageLocation.getY() + HEIGHT/2;
        return (clickX >= startX && clickX <= endX) && (clickY >= startY && clickY <= endY);
    }
}
