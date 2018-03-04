package canvascomponents.diagram;

import exceptions.DomainException;

import java.awt.geom.Point2D;

public class ResultMessage extends Message {

     public ResultMessage(){

     }

    /**
     * @param message
     *        The invcation message where this message belongs to
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
     * @post  The new message of this message is equal to the given message
     *        | new.getMessage == Message;
     * @post  The new label of this message is equal to the given label
     *        | new.getLabel == label
     * @post  The new receiver of this message is equal to the given receiver
     *        | new.getReceiver == receiver
     * @post  The new sender of this message is equal to the given sender
     *        | new.getsender == sender
     * @post  The new yLocation of this message is equal to the given yLocation
     *        | new.getyLocation == yLocation
     */
     public ResultMessage(Message message, Label label, Party receiver, Party sender, int yLocation) throws DomainException {
         super(message, label, receiver, sender, yLocation);
     }

    /**
     * @param point2D The coordinates of the mouse where the user clicked
     * @return
     *        True if the clicked coordinates are within the coordinates of the image of this message
     */
    @Override
    public boolean isClicked(Point2D point2D) {
        return false;
    }
}
