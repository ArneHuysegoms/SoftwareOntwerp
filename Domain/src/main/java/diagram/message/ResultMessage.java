package diagram.message;

import diagram.party.Party;
import diagram.label.Label;
import exceptions.DomainException;

import java.io.Serializable;

public class ResultMessage extends Message implements Serializable {

    /**
     * @param message
     *        The invcation message where this message belongs to
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
     */
     public ResultMessage(Message message, Label label, Party receiver, Party sender) throws DomainException {
         super(message, label, receiver, sender);
     }

    /**
     * @return the content of this messages' label
     */
    @Override
    public String toString() {
        return this.getLabel().getLabel();
    }
}
