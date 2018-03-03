package canvascomponents.diagram;

import canvascomponents.Clickable;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public class Message implements Clickable{

    private Message nextMessage;
    private MessageLabel label;
    private Actor receiver;
    private Actor sender;

    private int yLocation;

    public static final int HEIGHT = 16;


    public Message(){

    }

    /**
     * @param nextMessage
     *        The next Message on the callstack
     * @param label
     *        The label belonging to this message
     * @param receiver
     *        The party who receives this message
     * @param sender
     *        The party which sends this message
     * @param yLocation
     *        The y coordiante of the location where the message starts
     * @throws DomainException
     *        The sender cannot be null
     * @post  The new nextMessage of this message is equal to the given nextMessage
     *        | new.getnextMessage == nextMessage;
     * @post  The new label of this message is equal to the given label
     *        | new.getLabel == label
     * @post  The new receiver of this message is equal to the given receiver
     *        | new.getReceiver == receiver
     * @post  The new sender of this message is equal to the given sender
     *        | new.getsender == sender
     * @post  The new yLocation of this message is equal to the given yLocation
     *        | new.getyLocation == yLocation
     */
    public Message(Message nextMessage, MessageLabel label, Actor receiver, Actor sender, int yLocation) throws DomainException{
        this.setNextMessage(nextMessage);
        this.setLabel(label);
        this.setReceiver(receiver);
        this.setSender(sender);
        this.setyLocation(yLocation);
    }


    /**
     * @return returns the y coordinate of the start position of this message
     *
     */
    public int getyLocation() {
        return yLocation;
    }

    /**
     * @param yLocation
     *        the y coordinate of the start position of this message
     * @post  The new yLocation of this message is equal to the given yLocation
     *        | new.getyLocation == yLocation
     */
    private void setyLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    /**
     * @return returns the next message of this message
     */
    public Message getNextMessage() {
        return nextMessage;
    }

    /**
     * @param nextMessage
     *        The next Message on the callstack
     * @post  The new nextMessage of this message is equal to the given nextMessage
     *        | new.getnextMessage == nextMessage;
     *
     */
    private void setNextMessage(Message nextMessage) {
        this.nextMessage = nextMessage;
    }

    /**
     * @return returns the MessageLabel belonging to this message
     */
    public MessageLabel getLabel() {
        return label;
    }

    /**
     * @param label
     *        The label belonging to this message
     * @post  The new label of this message is equal to the given label
     *        | new.getLabel == label
     */
    private void setLabel(MessageLabel label) {
        this.label = label;
    }

    /**
     * @return returns the receiving party
     */
    public Actor getReceiver() {
        return receiver;
    }

    /**
     * @param receiver
     *        The receiving party
     * @post  The new receiver of this message is equal to the given receiver
     *        | new.getReceiver == receiver
     *
     */
    private void setReceiver(Actor receiver) {
        this.receiver = receiver;
    }

    /**
     * @return returns the sender of this party
     *
     */
    public Actor getSender() {
        return sender;
    }

    /**
     * @param sender
     *        The sending party
     * @throws DomainException
     *         The sender has to be a valid sender
     * @post  The new sender of this message is equal to the given sender
     *        | new.getsender == sender
     */
    private void setSender(Actor sender) throws DomainException{
        if(sender == null){
            throw new DomainException("Sender of message can't be null");
        }
        this.sender = sender;
    }

    /**
     * @param point2D
     *        The coordinates of the mouse where the user clicked
     * @return
     *        True if the clicked coordinates are within the coordinates of the image of this message
     */
    @Override
    public boolean isClicked(Point2D point2D) {
        Point2D senderLocation = getSender().getCoordinate();
        Point2D receiverLocation = getReceiver().getCoordinate();
        int messageLocation = getyLocation();


        double clickX = point2D.getX();
        double clickY = point2D.getY();
        double startX = senderLocation.getX();
        double startY = messageLocation - HEIGHT/2;
        double endX = receiverLocation.getX();
        double endY = messageLocation + HEIGHT/2;
        return (clickX >= startX && clickX <= endX) && (clickY >= startY && clickY <= endY);
    }
    /**
     * @param point2D
     *        The coordinates of the mouse where the user clicked
     * @return
     *       returns the distance between the coordinate of this message and the given point
     */
    @Override
    public double getDistance(Point2D point2D) {
        return Math.abs(point2D.getY() - yLocation);
    }
}
