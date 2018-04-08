package diagram;

import diagram.label.Label;
import diagram.label.MessageLabel;
import diagram.label.PartyLabel;
import diagram.message.ResultMessage;
import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.party.Actor;
import diagram.party.Object;
import diagram.party.Party;
import exceptions.DomainException;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Superclass of diagrams, contains most of the business logic in changing the diagram
 */
public class Diagram{

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  variables
    ////////////////////////////////////

    private List<Party> parties;

    private Message firstMessage;

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  constructors
    ////////////////////////////////////

    /**
     * create a new blank diagram
     */
    public Diagram(){
        this(new ArrayList<Party>(), null);
    }

    /**
     * creates a new diagram with the given parties, the first message of the call stack
     *
     * @param parties
     * @param firstMessage
     */
    public Diagram(List<Party> parties, Message firstMessage){
        this.setParties(parties);
        this.setFirstMessage(firstMessage);
        this.setMessageNumbers();
    }

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  setters and getters
    ////////////////////////////////////

    /**
     * sets the first message of the message stack
     *
     * @param message the new firstMessage
     */
    private void setFirstMessage(Message message){
        this.firstMessage = message;
    }

    /**
     *
     * @return the first message of the message stack
     */
    public Message getFirstMessage() {
        return firstMessage;
    }

    /**
     *
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
        if(parties != null ) {
            this.parties = parties;
        }
        else{
            this.parties = new ArrayList<>();
        }
    }

    /**
     * adds a new party to the diagram
     *
     * @param party the party to be added
     */
    public void addParty(Party party){
        this.getParties().add(party);
    }

    /**
     * removes a party from the diagram
     *
     * @param party the party to be removed
     */
    public void removeParty(Party party){
        this.getParties().remove(party);
    }

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  main part of business logic
    ////////////////////////////////////

    /**
     * Adds a new party in the form of an object
     *
     * @return the newly added party
     */
    public Party addNewParty(){
        PartyLabel label;
        Party object  = null;
        try {
            label = new PartyLabel("|");
            object = new Object(label);
            this.addParty(object);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return object;
    }

    /**
     * changes the party type of the Party on the provided location
     *
     * @param oldParty the party to change the type off
     *
     * @return the newly created party
     */
    public Party changePartyType(Party oldParty){
        Party newParty = null;
            if(oldParty instanceof Object){
                Object o = (Object) oldParty;
                try {
                    newParty = new Actor(o.getInstanceName(), o.getClassName(), o.getLabel());
                    this.updateMessagesForChangedParty(o, newParty);
                    this.removeParty(o);
                    this.addParty(newParty);
                }
                catch (DomainException exception){
                    System.out.println(exception.getMessage());
                }
            }
            else{
                Actor a = (Actor) oldParty;
                try {
                    newParty = new Object(a.getInstanceName(), a.getClassName(), a.getLabel());
                    this.updateMessagesForChangedParty(a, newParty);
                    this.removeParty(a);
                    this.addParty(newParty);
                }
                catch (DomainException exception){
                    System.out.println(exception.getMessage());
                }
            }
        return newParty;
    }

    /**
     * Adds a new message to the messagestack, with the receiver being the owner of the lifeline containing the provided location
     *
     * @param endlocation location on which the dragging was stopped
     * @throws IllegalStateException if the location doesn't respond to a receiving party
     */
    public void addNewMessage(Point2D endlocation) throws IllegalStateException{
        Party receiver = findReceiver(endlocation);
        if(receiver == null){
            System.out.println("Other lifeline not found");
        }
        else {
            MessageStart MessageStart = (MessageStart) this.getSelectedElement();
            Party sender = MessageStart.getParty();
            if(! receiver.equals(sender)) {
                Point2D startLocation = MessageStart.getStartloction();
                Message previous = findPreviousMessage(new Double(startLocation.getY()).intValue());
                if (checkCallStack(previous, sender)) {
                    try {
                        Message next;
                        if (previous == null) {
                            next = this.getFirstMessage();
                        } else {
                            next = previous.getNextMessage();
                        }
                        Message resultMessage = new ResultMessage(next, new MessageLabel("", new Point2D.Double(getNewLabelXPosition(sender, receiver), startLocation.getY() + 12)), sender, receiver, new Double(startLocation.getY() + 12).intValue());
                        MessageLabel messageLabel = new MessageLabel("|", new Point2D.Double(getNewLabelXPosition(receiver, sender), startLocation.getY() - 12));
                        Message invocation = new InvocationMessage(resultMessage, messageLabel, receiver, sender, new Double(startLocation.getY() - 12).intValue());
                        if (previous != null) {
                            previous.setNextMessage(invocation);
                        } else {
                            this.setFirstMessage(invocation);
                        }
                        startEditingLable(messageLabel);
                    } catch (DomainException exc) {
                        System.out.println(exc.getMessage());
                    }
                }
            }
        }
        setProperMessagePositions();
        setMessageNumbers();
    }

    /**
     * changes the position of the selected element
     *
     * if the party is dragged outside of the canvas the party is stuck on (0,0).
     *
     * @param newPosition the new position for the selected element
     */
    public void changePartyPosition(Point2D newPosition){
        if(newPosition.getY() < 0){
            newPosition.setLocation(newPosition.getX(), 0);
        }
        if(newPosition.getX() < 0){
            newPosition.setLocation(0, newPosition.getY());
        }
        if(this.getSelectedElement() instanceof Party){
            Party p = (Party) this.getSelectedElement();
            if(! isValidPartyLocation( newPosition)){
                newPosition = getValidPartyLocation(newPosition);
            }
            p.setCoordinate(newPosition);
            p.updateLabelCoordinate(p.getCorrectLabelPosition());
        }
        this.setSequenceNumbers();
    }
    /**
     * deletes the element that is currently selected
     */
    public void deleteElement(){
        if(selectedElementIsParty()){
            deleteParty((Party) this.selectedElement);
        }
        else if(selectedElementIsMessage()){
            deleteMessage((Message) this.selectedElement);
        }
        else if(selectedElementIsLabel()){
            deleteLabel((Label) this.selectedElement);
        }
    }

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  utilities
    ////////////////////////////////////

    /**
     * loops over the callStack to give each invocation their appropriate messageNumber
     */
    private void setMessageNumbers(){
        if(this.getFirstMessage() != null) {
            boolean active = false;
            int stack = 0;
            Message message = this.getFirstMessage();
            int N = 1;
            int e = 1;
            while(message != null ){
                if(message instanceof InvocationMessage) {
                    stack++;
                    InvocationMessage m = (InvocationMessage) message;
                    if (!active) {
                        m.setMessageNumber("" + N);
                        N++;
                        active = true;
                    }
                    else {
                        N--;
                        m.setMessageNumber("" + N + "." + e);
                        e++;
                        N++;
                    }
                }
                else {
                    stack--;
                }
                if(stack == 0){
                    active = false;
                    e = 1;
                }
                message = message.getNextMessage();
            }
        }
    }

    /**
     * sets the yLocation of all messages in the tree to an appropriate number
     */
    private void setProperMessagePositions(){
        Message message = this.getFirstMessage();
        int yLocation = 120;
        while(message != null){
            message.setyLocation(yLocation);
            yLocation += 35;
            Point2D labelCoordinate = new Point2D.Double(getNewLabelXPosition(message.getSender(), message.getReceiver()), message.getyLocation() - 15);
            message.getLabel().setCoordinate(labelCoordinate);
            message = message.getNextMessage();
        }
    }

    /**
     * returns a x-position for a new label, based on the location of the sender and receiver
     *
     * @param p1 the first party
     * @param p2 the second party
     *
     * @return a new Point2D containing the location for the new message
     */
    private Double getNewLabelXPosition(Party p1, Party p2){
        return (p1.getCoordinate().getX() + p2.getCoordinate().getX())/2;
    }

    /**
     * Finds the receiver of a message based on the endlocation of the messageDrag
     *
     * @param endlocation the location where the dragging for the message stopped
     * @return the party that corresponds to the location
     */
    private Party findReceiver(Point2D endlocation){
        for(Party party : parties){
            if(isLifeLine(endlocation, party)){
                return party;
            }
        }
        return null;
    }

    /**
     * Deletes a party from the diagram.
     *
     * @param party the party that will be deleted
     */
    private void deleteParty(Party party){
        rearrangeMessageTreeByParty(party);
        this.removeParty(party);
    }

    /**
     * deletes a message by adding the next independent message to the message preceding it, cutting out the dependent part of the message stack
     *
     * @param message the message to be deleted
     */
    private void deleteMessage(Message message){
        if(message instanceof InvocationMessage) {
            if (!message.equals(this.getFirstMessage())) {
                Message iter = this.getFirstMessage();
                Message previous;
                while (iter.getNextMessage() != null && !iter.getNextMessage().equals(message)) {
                    iter = iter.getNextMessage();
                }
                previous = iter;
                Message next = skipOverDependentMessages(message, -1);
                previous.setNextMessage(next);
            } else {
                this.setFirstMessage(skipOverDependentMessages(message, -1));
            }
        }
    }

    /**
     * deletes a message or a party if the provided label is its label
     *
     * @param label the label of the element to delete
     *
     *  TODO check this function
     */
    private void deleteLabel(Label label){
        boolean done = false;
        for(Party party : this.getParties()){
            if(! done && party.getLabel().equals(label)){
                deleteParty(party);
            }
        }
        if(! done){
            if(this.getFirstMessage() != null){
                Message message = this.getFirstMessage();
                while(! (message == null) && ! message.getLabel().equals(label) ){
                    message = message.getNextMessage();
                }
                if(message != null){
                    deleteMessage(message);
                }
            }
        }
    }

    /**
     * Rearranges the message tree upon deletion of the provided party
     *
     * TODO give back skipped messages for deletion
     *
     * @param party the party that will be deleted
     */
    private void rearrangeMessageTreeByParty(Party party){
        if(this.getFirstMessage() != null) {
            Message previous = getFirstMessage();
            if (getFirstMessage().getSender().equals(party) || getFirstMessage().getReceiver().equals(party)) {
                firstMessage = null;
            }
            else {
                Message message = previous.getNextMessage();
                while (message != null) {
                    if (message.getSender().equals(party) || message.getReceiver().equals(party)) {
                        message = skipOverDependentMessages(message, -1);
                        if (previous != null) {
                            previous.setNextMessage(message);
                            previous = message.getNextMessage();
                        }
                    }
                    message = message.getNextMessage();
                }
            }
        }
        this.setMessageNumbers();
    }

    /**
     * Finds the first message that doesn't directly or indirectly depend on the provided Message
     *
     * works recursively
     *
     * @param message the message of which the next not depending descendant message must be found
     * @param stack integer counting the stack of the messages
     * @return the first message that doesn't depend on the provided message
     */
    private Message skipOverDependentMessages(Message message, int stack){
        if(message == null){
            return null;
        }
        while(stack < 0 ){
            message = message.getNextMessage();
            if(message != null) {
                if (message instanceof InvocationMessage) {
                    stack--;
                }
                else {
                    stack++;
                }
            }
            else{
                return null;
            }
        }
        return message.getNextMessage();
    }

    /**
     * Determines if the call stack is still in order
     *
     * @param sender the party to check the call stack of
     * @return true if the party is on top of the call stack, false otherwise
     */
    private boolean checkCallStack(Message previous, Party sender){
        /*if(previous == null || (previous instanceof InvocationMessage && previous.getReceiver().equals(sender))){
            return true;
        }*/
        if(previous == null){
            return true;
        }
        if(previous.getReceiver().equals(sender)){
            return true;
        }
        else if(this.getFirstMessage() != null){
            return this.getFirstMessage().getSender().equals(sender) && ! (previous instanceof InvocationMessage);
        }
        return false;
    }

    /**
     * updates all the messasges to relink them to the appropriate party
     *
     * @param old the old party of the message
     * @param newParty the new party of the message
     */
    private void updateMessagesForChangedParty(Party old, Party newParty){
        Message message = firstMessage;
        while(message != null){
            if(message.getReceiver().equals(old)){
                message.setReceiver(newParty);
            }
            if(message.getSender().equals(old)){
                try {
                    message.setSender(newParty);
                }
                catch (DomainException exc){
                    System.out.println(exc.getMessage());
                }
            }
            message = message.getNextMessage();
        }
    }

    /**
     * Finds the message proceeding the message on the provided yLocation
     *
     * @param yLocation the ylocation of the next event to add
     *
     * @return the message that will preceed the one the given yLocation, null if none was found or didn't exist
     */
    private Message findPreviousMessage(int yLocation){
        Message message = this.getFirstMessage();
        if(message == null){
            return null;
        }
        if(message.getyLocation() > yLocation){
            return null;
        }
        Message previous = message;
        Message next;
        boolean found = false;
        while(! found){
            next = previous.getNextMessage();
            if(next == null){
                return previous;
            }
            if(next.getyLocation() > yLocation){
                return previous;
            }
            previous = next;
        }
        return null;
    }
}
