package canvascomponents.diagram;

import canvascomponents.Clickable;
import diagram.label.Label;
import diagram.message.Message;
import exceptions.DomainException;

public class InvocationMessage extends Message implements Clickable {

    public InvocationMessage(){

    }
    /**
     * @param message
     *        The next message on the callstack
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
    public InvocationMessage(Message message, Label label, Party receiver, Party sender, int yLocation) throws DomainException{
        super(message, label, receiver, sender, yLocation);
    }
}
