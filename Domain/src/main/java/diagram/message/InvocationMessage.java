package diagram.message;

import diagram.party.Party;
import diagram.label.Label;
import exceptions.DomainException;

public class InvocationMessage extends Message{

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
    public InvocationMessage(Message message, Label label, Party receiver, Party sender) throws DomainException{
        this(message, label, receiver, sender, "");
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
    public InvocationMessage(Message message, Label label, Party receiver, Party sender, String messageNumber) throws DomainException{
        super(message, label, receiver, sender);
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

    @Override
    public boolean equals(Object o){
        if(o instanceof InvocationMessage){
            InvocationMessage m = (InvocationMessage) o;
            return super.equals(m) && m.getMessageNumber().equals(this.getMessageNumber());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return (super.hashCode() + this.getMessageNumber().hashCode()) % 17;
    }
}
