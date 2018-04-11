package diagram.message;

import diagram.DiagramElement;
import diagram.party.Party;
import diagram.label.Label;
import exceptions.DomainException;

public abstract class Message extends DiagramElement {

    private Message nextMessage;
    private Label label;
    private Party receiver;
    private Party sender;

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
     * @post  The new nextMessage of this message is equal to the given nextMessage
     *        | new.getnextMessage == nextMessage;
     *
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
     * @post  The new label of this message is equal to the given label
     *        | new.getLabel == label
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
     * @post  The new receiver of this message is equal to the given receiver
     *        | new.getReceiver == receiver
     *
     */
    public void setReceiver(Party receiver) {
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
     * @post  The new sender of this message is equal to the given sender
     *        | new.getsender == sender
     */
    public void setSender(Party sender) throws DomainException{
        if(sender == null){
            throw new DomainException("Sender of message can't be null");
        }
        this.sender = sender;
    }

    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object o){
        if(o instanceof Message){
            Message m = (Message) o;
            return m.getLabel().equals(this.getLabel()) && m.getReceiver().equals(this.getReceiver())
                    && m.getSender().equals(this.getSender());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return (this.getLabel().hashCode() + this.getSender().hashCode() + this.getReceiver().hashCode()) % 17;
    }
}
