package diagram.message;

import diagram.Clickable;
import diagram.party.Party;
import diagram.label.Label;
import exceptions.DomainException;

public class InvocationMessage extends Message implements Clickable {

    private String messageNumber;

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
        this(message, label, receiver, sender, yLocation, "");
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
     * @param messageNumber
     *        The messageNumber used in the communication Diagram
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
    public InvocationMessage(Message message, Label label, Party receiver, Party sender, int yLocation, String messageNumber) throws DomainException{
        super(message, label, receiver, sender, yLocation);
        this.setMessageNumber(messageNumber);
    }

    /**
     *
     * @return this message's messageNumber
     */
    public String getMessageNumber() {
        return messageNumber;
    }

    /**
     * sets the messageNumber to the provided messageNumber
     *
     * @param messageNumber
     */
    public void setMessageNumber(String messageNumber) {
        this.messageNumber = messageNumber;
    }

    /**
     * @return the messageNumber followed by the content of the label
     */
    @Override
    public String toString() {
        return this.getMessageNumber() + " " + this.getLabel().getLabel();
    }
}
