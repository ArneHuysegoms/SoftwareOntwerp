package canvascomponents.diagram;

import excpetions.DomainException;

public class Message {

    private Message nextMessage;
    private String label;
    private Actor receiver;
    private Actor sender;

    public Message(){

    }

    public Message(Message nextMessage, String label, Actor reciever, Actor sender) throws DomainException{
        this.setNextMessage(nextMessage);
        this.setLabel(label);
        this.setReceiver(reciever);
        this.setSender(sender);
    }

    public Message getNextMessage() {
        return nextMessage;
    }

    private void setNextMessage(Message nextMessage) {
        this.nextMessage = nextMessage;
    }

    public String getLabel() {
        return label;
    }

    private void setLabel(String label) {
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
}
