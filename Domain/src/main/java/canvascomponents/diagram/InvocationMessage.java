package canvascomponents.diagram;

import excpetions.DomainException;

public class InvocationMessage extends Message {

    public InvocationMessage(){

    }

    public InvocationMessage(Message message, String label, Actor receiver, Actor sender) throws DomainException{
        super(message, label, receiver, sender);
    }
}
