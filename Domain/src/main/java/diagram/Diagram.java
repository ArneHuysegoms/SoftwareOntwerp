package diagram;

import diagram.label.*;
import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.message.ResultMessage;
import diagram.party.Actor;
import diagram.party.Object;
import diagram.party.Party;
import exceptions.DomainException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class describing a diagram, consists of parties and messages
 */
public class Diagram implements Serializable {

    private List<Party> parties;

    private Message firstMessage;

    /**
     * create a new blank diagram
     */
    public Diagram() {
        this(new ArrayList<>(), null);
    }

    /**
     * creates a new diagram with the given parties, the first message of the call stack
     *
     * @param parties the parties for this diagram
     * @param firstMessage the firstmessage of the new diagram
     */
    public Diagram(List<Party> parties, Message firstMessage) {
        this.setParties(parties);
        this.setFirstMessage(firstMessage);
        this.setMessageNumbers();
    }

    /**
     * sets the first message of the message stack
     *
     * @param message the new firstMessage
     */
    private void setFirstMessage(Message message) {
        this.firstMessage = message;
    }

    /**
     * @return the first message of the message stack
     */
    public Message getFirstMessage() {
        return firstMessage;
    }

    /**
     * @return all the parties in this diagram
     */
    public List<Party> getParties() {
        return parties;
    }

    /**
     * sets the parties for this diagram
     *
     * @param parties the parties for this diagram
     */
    private void setParties(List<Party> parties) {
        if (parties != null) {
            this.parties = parties;
        } else {
            this.parties = new ArrayList<>();
        }
    }

    /**
     * adds a new party to the diagram
     *
     * @param party the party to be added
     */
    private void addParty(Party party) {
        this.getParties().add(party);
    }

    /**
     * removes a party from the diagram
     *
     * @param party the party to be removed
     */
    private void removeParty(Party party) {
        this.getParties().remove(party);
    }

    /**
     * Adds a new party in the form of an object
     *
     * @return the newly added party
     */
    public Party addNewParty() {
        PartyLabel label;
        Party object = null;
        try {
            label = new PartyLabel("");
            object = new Object(label);
            this.addParty(object);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return object;
    }

    /**
     * changes the party type of the Party on the provided location
     *
     * @param oldParty the party to change the type off
     * @return the newly created party
     */
    public Party changePartyType(Party oldParty) {
        Party newParty;
        if (oldParty instanceof Object) {
            Object o = (Object) oldParty;
            newParty = new Actor(o.getLabel());
            this.updateMessagesForChangedParty(o, newParty);
            this.removeParty(o);
            this.addParty(newParty);
        } else {
            Actor a = (Actor) oldParty;
            newParty = new Object(a.getLabel());
            this.updateMessagesForChangedParty(a, newParty);
            this.removeParty(a);
            this.addParty(newParty);
        }
        return newParty;
    }

    /**
     * deletes the element whom the given label belongs too
     *
     * @param label the label of the element to delete
     * @return a Set of diagramelements to remove the positions of
     */
    public Set<DiagramElement> deleteElementByLabel(Label label) {
        Set<DiagramElement> deletedElements = new HashSet<>();
        DiagramElement element = findParentElement(label);
        if (element instanceof Party) {
            Party p = (Party) element;
            deletedElements.addAll(deleteParty(p));
        } else if (element instanceof Message) {
            Message m = (Message) element;
            deletedElements.addAll(deleteMessage(m));
        }
        return deletedElements;
    }

    /**
     * finds the element corresponding which contains the given label
     *
     * @param label the label of the diagramelement to find
     * @return the diagramelement that has the provided label
     */
    public DiagramElement findParentElement(Label label) {
        for (Party p : parties) {
            if (p.getLabel().equals(label)) {
                return p;
            }
        }
        if (this.getFirstMessage() != null) {
            Message message = this.getFirstMessage();
            while (message != null) {
                if (message.getLabel().equals(label)) {
                    return message;
                }
                message = message.getNextMessage();
            }
        }
        return null;
    }

    /**
     * Rearranges the message tree upon deletion of the provided party
     *
     * @param party the party that will be deleted
     * @return a set of messages that have been deleted
     */
    private Set<DiagramElement> rearrangeMessageTreeByParty(Party party) {
        Set<DiagramElement> deletedElements = new HashSet<>();
        deletedElements.add(party);
        Message previous;
        Message next = null;
        Message fine = this.getFirstMessage();
        boolean moved;
        if(this.getFirstMessage() != null){
            previous = this.getFirstMessage();
            while(previous != null) {
                moved = false;
                if (previous.getSender().equals(party) || previous.getReceiver().equals(party)) {
                    if(! deletedElements.contains(previous)) {
                        deletedElements.add(previous);
                    }
                    List<Message> dependentMessages = skipOverDependentMessages(previous);
                    next = dependentMessages.get(dependentMessages.size() - 1);
                    deletedElements.addAll(dependentMessages.subList(0,dependentMessages.size()-1));
                    if(previous.equals(this.getFirstMessage())){
                        this.setFirstMessage(next);
                    }
                    fine.setNextMessage(next);
                    moved = true;
                }
                fine = previous;
                if(moved) {
                    previous = next;
                }
                else{
                    previous = previous.getNextMessage();
                }
            }
        }
        this.setMessageNumbers();
        return deletedElements;
    }

    /**
     * Finds the first message that doesn't directly or indirectly depend on the provided Message
     *
     * @param message the message of which the next not depending descendant message must be found
     * @return the message that are between the provided message and the next not dependent message, with t he next
     * not dependent message included at the last position
     */
    private List<Message> skipOverDependentMessages(Message message) {
        List<Message> dependentMessages = new ArrayList<>();
        int stack = -1;
        while (stack < 0) {
            message = message.getNextMessage();
            if (message != null) {
                if (message instanceof InvocationMessage) {
                    if (stack < 0) {
                        stack--;
                        dependentMessages.add(message);
                    }
                } else {
                    if (stack < 0) {
                        stack++;
                        dependentMessages.add(message);
                    }
                }
            }
            else {
                dependentMessages.add(null);
            }
        }
        dependentMessages.add(message.getNextMessage());
        return dependentMessages;
    }

    /**
     * loops over the callStack to give each invocation their appropriate messageNumber
     */
    private void setMessageNumbers() {
        if (this.getFirstMessage() != null) {
            boolean active = false;
            int stack = 0;
            Message message = this.getFirstMessage();
            int N = 1;
            int e = 1;
            while (message != null) {
                if (message instanceof InvocationMessage) {
                    stack++;
                    InvocationMessage m = (InvocationMessage) message;
                    if (!active) {
                        m.setMessageNumber("" + N);
                        N++;
                        active = true;
                    } else {
                        N--;
                        m.setMessageNumber("" + N + "." + e);
                        e++;
                        N++;
                    }
                } else {
                    stack--;
                }
                if (stack == 0) {
                    active = false;
                    e = 1;
                }
                message = message.getNextMessage();
            }
        }
    }

    /**
     * Deletes a party from the diagram.
     *
     * @param party the party that will be deleted
     * @return a set of diagramelements that need to be deleted
     */
    private Set<DiagramElement> deleteParty(Party party) {
        Set<DiagramElement> deletedElements = new HashSet<>();
        deletedElements.addAll(rearrangeMessageTreeByParty(party));
        this.removeParty(party);
        return deletedElements;
    }

    /**
     * deletes a message by adding the next independent message to the message preceding it, cutting out the dependent part of the message stack
     *
     * @param message the message to be deleted
     * @return a set of diagremelements that are deleted
     */
    private Set<DiagramElement> deleteMessage(Message message) {
        Set<DiagramElement> deletedElements = new HashSet<>();
        if(message instanceof InvocationMessage){
            deletedElements.add(message);
            Message previous = this.findPreviousMessage(message);
            if(previous == null){
                List<Message> dependents = skipOverDependentMessages(message);
                deletedElements.addAll(dependents.subList(0, dependents.size() -1));
                this.setFirstMessage(dependents.get(dependents.size() -1));
            }
            else{
                List<Message> dependents = skipOverDependentMessages(message);
                deletedElements.addAll(dependents.subList(0, dependents.size() -1));
                previous.setNextMessage(dependents.get(dependents.size() -1));
            }
        }
        setMessageNumbers();
        return deletedElements;
    }

    /**
     * finds the message that precedes the given message in the callstack
     *
     * @param message the message we want to find the preceding message of
     * @return the message preceding the given message
     */
    private Message findPreviousMessage(Message message){
        if(message.equals(this.getFirstMessage())){
            return null;
        }
        Message iter = this.getFirstMessage();
        while(iter != null){
            if(iter.getNextMessage().equals(message)){
                return iter;
            }
            iter = iter.getNextMessage();
        }
        return null;
    }

    /**
     * Determines if the call stack is still in order
     *
     * @param sender the party to check the call stack of
     * @return true if the party is on top of the call stack, false otherwise
     */
    private boolean checkCallStack(Message previous, Party sender) {
        if (previous == null && (getFirstMessage() == null || this.getFirstMessage().getSender().equals(sender))) {
            return true;
        }
        if (previous.getReceiver().equals(sender)) {
            return true;
        } else if (this.getFirstMessage() != null) {
            return this.getFirstMessage().getSender().equals(sender) && !(previous instanceof InvocationMessage);
        }
        return false;
    }

    /**
     * updates all the messasges to relink them to the appropriate party
     *
     * @param old      the old party of the message
     * @param newParty the new party of the message
     */
    private void updateMessagesForChangedParty(Party old, Party newParty) {
        Message message = firstMessage;
        while (message != null) {
            if (message.getReceiver().equals(old)) {
                try {
                    message.setReceiver(newParty);
                } catch (DomainException exc) {
                    System.out.println(exc.getMessage());
                }
            }
            if (message.getSender().equals(old)) {
                try {
                    message.setSender(newParty);
                } catch (DomainException exc) {
                    System.out.println(exc.getMessage());
                }
            }
            message = message.getNextMessage();
        }
    }

    /**
     * Adds a new message to the messagestack, with the receiver being the owner of the lifeline containing the provided location
     *
     * @param sender          sender of the new message
     * @param receiver        receiver of the new message
     * @param previousMessage the message  preceding the new message
     * @return a List containing all newly created messages, on order of the call stack
     * @throws IllegalStateException if the location doesn't respond to a receiving party
     */
    public List<Message> addNewMessage(Party sender, Party receiver, Message previousMessage) throws IllegalStateException {
        ArrayList<Message> newMessages = new ArrayList<>();
        if (!receiver.equals(sender)) {
            if (checkCallStack(previousMessage, sender)) {
                try {
                    Message next;
                    if (previousMessage == null) {
                        next = this.getFirstMessage();
                    } else {
                        next = previousMessage.getNextMessage();
                    }
                    Message resultMessage = new ResultMessage(next, new ResultMessageLabel("cM"), sender, receiver);
                    MessageLabel messageLabel = new InvocationMessageLabel("cM", new ArrayList<>());
                    Message invocation = new InvocationMessage(resultMessage, messageLabel, receiver, sender);
                    if (previousMessage != null) {
                        previousMessage.setNextMessage(invocation);
                    } else {
                        this.setFirstMessage(invocation);
                    }
                    newMessages.add(invocation);
                    newMessages.add(resultMessage);
                } catch (DomainException exc) {
                    System.out.println(exc.getMessage());
                }
            }
        }
        setMessageNumbers();
        return newMessages;
    }
}
