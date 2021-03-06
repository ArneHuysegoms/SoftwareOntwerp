package diagram.message;

import diagram.DiagramElement;
import diagram.label.Label;
import diagram.party.Party;
import exceptions.DomainException;

import java.io.Serializable;

/**
 * abstract superclass for messages of a diagram
 */
public abstract class Message extends DiagramElement  implements Serializable {

    private Message nextMessage;
    private Label label;
    private Party receiver;
    private Party sender;

    /**
     * required default constructor
     */
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
     * @throws DomainException
     *        The sender cannot be null
     */
    public Message(Message nextMessage, Label label, Party receiver, Party sender) throws DomainException{
        this.setNextMessage(nextMessage);
        this.setLabel(label);
        this.setReceiver(receiver);
        this.setSender(sender);
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
     */
    public void setNextMessage(Message nextMessage) {
        this.nextMessage = nextMessage;
    }

    /**
     * @return returns the MessageLabel belonging to this message
     */
    public Label getLabel() {
        return label;
    }

    /**
     * @param label
     *        The label belonging to this message
     */
    private void setLabel(Label label) {
        this.label = label;
    }

    /**
     * @return returns the receiving party
     */
    public Party getReceiver() {
        return receiver;
    }

    /**
     * @param receiver
     *        The receiving party
     * @throws DomainException if the receiver is invalid
     */
    public void setReceiver(Party receiver) throws DomainException {
        if(receiver == null){
            throw new DomainException("Receiver of message can't be null");
        }
        this.receiver = receiver;
    }

    /**
     * @return returns the sender of this party
     *
     */
    public Party getSender() {
        return sender;
    }

    /**
     * @param sender
     *        The sending party
     * @throws DomainException
     *         The sender has to be a valid sender
     */
    public void setSender(Party sender) throws DomainException{
        if(sender == null){
            throw new DomainException("Sender of message can't be null");
        }
        this.sender = sender;
    }

    /**
     *
     * @return a string description of this message
     */
    @Override
    public String toString(){
        return this.label.toString();
    }

    /**
     *
     * @param o other Object
     * @return whether or not the given Object is the same as this
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Message){
            Message m = (Message) o;
            return m.getLabel().equals(this.getLabel()) && m.getReceiver().equals(this.getReceiver())
                    && m.getSender().equals(this.getSender());
        }
        return false;
    }

    /**
     *
     * @return a hashcode of this message, divided modulo 17
     */
    @Override
    public int hashCode(){
        return (this.getLabel().hashCode() + this.getSender().hashCode() + this.getReceiver().hashCode()) % 17;
    }
}
