package diagram.message;

import diagram.label.Label;
import diagram.party.Party;
import exceptions.DomainException;

import java.io.Serializable;

/**
 * Message subclass for invocation messages
 */
public class InvocationMessage extends Message implements Serializable {

    private String messageNumber;

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
     *          the sender or receiver can't be null
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
     *        The sender or receiver cannot be null
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
     * @param messageNumber the new messagenumbe for this invocationMessage
     */
    public void setMessageNumber(String messageNumber) {
        this.messageNumber = messageNumber;
    }

    /**
     * @return the messageNumber followed by the content of the label
     */
    @Override
    public String toString() {
        return this.getMessageNumber() + " " + this.getLabel().toString();
    }

    /**
     *
     * @param o other Object
     * @return wether or not the given object is equal to this
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof InvocationMessage){
            InvocationMessage m = (InvocationMessage) o;
            return super.equals(m) && m.getMessageNumber().equals(this.getMessageNumber());
        }
        return false;
    }

    /**
     *
     * @return a hashcode of this message, modulo 17
     */
    @Override
    public int hashCode(){
        return (super.hashCode() + this.getMessageNumber().hashCode()) % 17;
    }
}
