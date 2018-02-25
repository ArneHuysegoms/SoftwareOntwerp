package canvascomponents.diagram;

import excpetions.DomainException;

public class ResultMessage extends Message {

     public ResultMessage(){

     }

     public ResultMessage(Message message, String label, Actor receiver, Actor sender) throws DomainException {
         super(message, label, receiver, sender);
     }
}
