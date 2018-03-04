package canvascomponents.diagram;

import canvascomponents.Clickable;
import exceptions.DomainException;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Superclass of diagrams, contains most of the business logic in changing the diagram
 */
public abstract class Diagram{

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  variables
    ////////////////////////////////////

    private boolean messageMode;

    private List<Party> parties;

    private Clickable selectedElement;

    private Message firstMessage;

    private boolean labelMode;
    private boolean validLabel;
    private String labelContainer = "";
    private Label editableLable;

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  constructors
    ////////////////////////////////////

    /**
     * creates an empty diagram
     */
    public Diagram() {
        this(null, null);
    }

    /**
     * creates a new diagram with the given parties and the first message of the call stack
     *
     * @param parties
     * @param firstMessage
     */
    public Diagram(List<Party> parties, Message firstMessage){
        this(parties, firstMessage, null);
    }

    /**
     * creates a new diagram with the given parties, the first message of the call stack and the currently selected element
     *
     * @param parties
     * @param firstMessage
     * @param selectedElement
     */
    public Diagram(List<Party> parties, Message firstMessage, Clickable selectedElement){
        this(parties, firstMessage, selectedElement, "");
    }

    /**
     * creates a new diagram with the given parties, the first message of the call stack, the currently selected element and the text for the label in edit
     *
     * @param parties
     * @param firstMessage
     * @param selectedElement
     * @param labelContainer
     */
    public Diagram(List<Party> parties, Message firstMessage, Clickable selectedElement, String labelContainer){
        this(parties, firstMessage, selectedElement, labelContainer, false, false, false);
    }

    /**
     * creates a new diagram with the given parties, the first message of the call stack, the currently selected element, the text for the label in edit and the
     * state for the flags
     *
     * @param parties
     * @param firstMessage
     * @param selectedElement
     * @param labelContainer
     * @param labelMode
     * @param validLabel
     * @param messageMode
     */
    public Diagram(List<Party> parties, Message firstMessage, Clickable selectedElement, String labelContainer, boolean labelMode, boolean validLabel, boolean messageMode){
        this.setParties(parties);
        this.labelContainer = labelContainer;
        this.setSelectedElement(selectedElement);
        this.setFirstMessage(firstMessage);
        this.setLabelMode(labelMode);
        this.setMessageMode(messageMode);
        this.setValidLabel(validLabel);
        }

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  setters and getters
    ////////////////////////////////////

    /**
     * sets the selected element of the diagram
     * @param selectedElement
     */
    private void setSelectedElement(Clickable selectedElement){
        this.selectedElement = selectedElement;
    }

    /**
     *
     * @return the currently selected element
     */
    public Clickable getSelectedElement() {
        return selectedElement;
    }

    /**
     *
     * @return the labelcontainer of this diagram
     */
    public String getLabelContainer(){
        return this.labelContainer;
    }

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

    /**
     * sets the new state for the labelMode flag
     * @param labelMode
     */
    private void setLabelMode(boolean labelMode){
        this.labelMode = labelMode;
    }

    /**
     *
     * @return the flag for labelMode
     */
    public boolean isLabelMode(){
        return this.labelMode;
    }

    /**
     * set the state of the validlabel flag
     *
     * @param validLabel the new state for the flag
     */
    private void setValidLabel(boolean validLabel){
        this.validLabel = validLabel;
    }

    /**
     *
     * @return whether the label is valid
     */
    public boolean isValidLabel(){
        return this.validLabel;
    }

    /**
     * sets messageMode to the provided state
     *
     * @param messageMode
     */
    private void setMessageMode(boolean messageMode){
        this.messageMode = messageMode;
    }

    /**
     * inspector for messageMode
     *
     * @return true if the diagram is in messageMode, false otherwise
     */
    public boolean isMessageMode(){
        return this.messageMode;
    }

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  main part of business logic
    ////////////////////////////////////

    /**
     * Adds a new party on the given location, per use case will be in the form of an object
     *
     * @param point2D the location on which to add a new party
     */
    public void addNewParty(Point2D point2D){
        int posSeq = findNextPositionInSequenceDiagram(this.getParties());
        Point2D finalPosition = null;
        PartyLabel label;
        if(isValidPartyLocation(point2D)){
            finalPosition = getValidPartyLocation(point2D);
            try {
                label = new PartyLabel("I", new Point2D.Double(point2D.getX() + 10, point2D.getY() + 20));
                Object object = new Object(posSeq, finalPosition, label);
                startEditingLable(label);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * changes the party type of the Party on the provided location
     *
     * @param point2D the location of the party to change the type of
     */
    public void changePartyType(Point2D point2D){
        this.setSelectedElement(selectClickableElement(point2D));
        if(this.getSelectedElement() instanceof Party){
            if(this.getSelectedElement() instanceof Object){
                Object o = (Object) this.getSelectedElement();
                try {
                    Party newActor = new Actor(o.getInstanceName(), o.getClassName(), o.getPositionInSequenceDiagram(), o.getCoordinate(), o.getLabel());
                    this.updateMessagesForChangedParty(newActor);
                    this.removeParty(o);
                    this.addParty(newActor);
                }
                catch (DomainException exception){
                    System.out.println(exception.getMessage());
                }
            }
            else{
                Actor a = (Actor) this.getSelectedElement();
                try {
                    Party newObject = new Object(a.getInstanceName(), a.getClassName(), a.getPositionInSequenceDiagram(), a.getCoordinate(), a.getLabel());
                    this.updateMessagesForChangedParty(newObject);
                    this.removeParty(a);
                    this.addParty(newObject);
                }
                catch (DomainException exception){
                    System.out.println(exception.getMessage());
                }
            }
        }
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
            throw new IllegalStateException("Other lifeline not found");
        }
        else {
            MessageStart MessageStart = (MessageStart) this.getSelectedElement();
            Party sender = MessageStart.getParty();
            Point2D startLocation = MessageStart.getStartloction();
            if(checkCallStack(sender)) {
                try {
                    Message previous = findPreviousMessage(new Double(startLocation.getY()).intValue());
                    Message next;
                    if(previous == null) {
                        next = this.getFirstMessage();
                    }
                    else {
                        next = previous.getNextMessage();
                    }
                    Message resultMessage = new ResultMessage(next, new MessageLabel(), receiver, sender,  new Double(startLocation.getY() - 7).intValue() );
                    MessageLabel messageLabel = new MessageLabel();
                    Message invocation = new InvocationMessage(resultMessage, messageLabel, sender, receiver, new Double(startLocation.getY() + 7).intValue());
                    previous.setNextMessage(invocation);
                    startEditingLable(messageLabel);
                }
                catch (DomainException exc){
                    System.out.println(exc.getMessage());
                }
            }
        }
    }

    /**
     * changes the position of the selected element
     *
     * @param newPosition the new position for the selected element
     */
    public void changePartyPosition(Point2D newPosition){
        if(this.getSelectedElement() instanceof Actor){
            Actor a = (Actor) this.getSelectedElement();
            a.setCoordinate(newPosition);
        }
    }

    /**
     * finds the element that is selected based on the click on the provided location
     *
     * @param point2D the location that was clicked on
     * @return the element that was clicked on
     */
    public Clickable findSelectedElement(Point2D point2D){
        if(selectedElement instanceof Label){
            stopEditingLabel();
            this.setLabelMode(false);
            labelContainer = "";
            editableLable = null;
        }
        Clickable selected = selectClickableElement(point2D);
        this.setSelectedElement(selected);
        return selected;
    }

    /**
     * adds the given char to the Label in edit
     *
     * @param newChar
     */
    public void addCharToLabel(char newChar){
        appendCharToLabel(newChar);
    }

    /**
     * removes the last Char from the Label in edit
     */
    public void removeLastCharFromLabel(){
        removeCharFromContainer();
    }

    /**
     * Start editing the currently selected element, that is a label
     */
    public void editLabel(){
        startEditingLable((Label) this.getSelectedElement());
    }

    /**
     * Stop editing the label that was selected
     *
     * @throws DomainException if the final label isn't valid
     */
    public void stopEditingLabel(){
        try {
            this.editableLable.setLabel(labelContainer);
        }
        catch (DomainException exc){
            System.out.println(exc.getMessage());
        }
    }

    /**
     * deletes the element that is currently selected
     */
    public void deleteElement(){
        if(this.selectedElement instanceof Party){
            deleteParty((Party) this.selectedElement);
        }
        else if(this.selectedElement instanceof Message){
            deleteMessage((Message) this.selectedElement);
        }
    }

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  utilities
    ////////////////////////////////////

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
        changeSequenceNumbers(party.getPositionInSequenceDiagram());
        this.removeParty(party);
    }

    /**
     * changes the position of the parties in the sequence diagram based on the provided removed position
     *
     * @param deletedPosition the position in the sequence diagram that was deleted
     *
     * @throws DomainException if no valid new location can be given to the remaining parties
     */
    private void changeSequenceNumbers(int deletedPosition){
        for(Party p : this.getParties()){
            if(p.getPositionInSequenceDiagram() > deletedPosition){
                try {
                    p.setPositionInSequenceDiagram(p.getPositionInSequenceDiagram() - 1);
                }
                catch (DomainException exception){
                    System.out.println(exception.getMessage());
                }
            }
        }
    }

    /**
     * deletes a message by adding the next independent message to the message preceding it, cutting out the dependent part of the message stack
     *
     * @param message the message to be deleted
     */
    private void deleteMessage(Message message){
        Message iter = this.getFirstMessage();
        Message previous;
        while(! iter.getNextMessage().equals(message)){
            iter = iter.getNextMessage();
        }
        previous = iter;
        Message next = skipOverDependentMessages(message, -1);
        previous.setNextMessage(next);
    }

    /**
     * Rearranges the message tree upon deletion of the provided party
     * @param party the party that will be deleted
     */
    private void rearrangeMessageTreeByParty(Party party){
        Message message = getFirstMessage();
        Message nextMessage;
        while(message != null){
            if(message.getSender().equals(party) || message.getReceiver().equals(party)){
                nextMessage = skipOverDependentMessages(message, -1);
                message.setNextMessage(nextMessage);
                message = nextMessage;
            }
        }
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
        if(stack < 0 ){
            message = message.getNextMessage();
            if(message != null) {
                if (message instanceof InvocationMessage) {
                    skipOverDependentMessages(message, stack--);
                } else if (message instanceof ResultMessage) {
                    skipOverDependentMessages(message, stack++);
                }
            }
            else{
                return null;
            }
        }
        else{
            return message.getNextMessage();
        }
        return null;
    }

    /**
     * Determines if the call stack is still in order
     *
     * @param party the party to check the call stack of
     * @return true if the party is on top of the call stack, false otherwise
     */
    private boolean checkCallStack(Party party){
        Message message = getFirstMessage();
        return party.equals(message.getSender());
    }

    /**
     * Starts the proces of editing the label, sets the label as the selected element and initiates appropriate flags and variables
     *
     * @param label the label to edit
     */
    private void startEditingLable(Label label){
        this.setSelectedElement(label);
        this.setLabelMode(true);
        this.setValidLabel(false);
        this.editableLable = label;
    }


    /**
     * Appends the char of a keyStroke to the label that's being edited
     *
     * @param newChar the newChar to append
     */
    private void appendCharToLabel(char newChar){
        if(this.labelContainer.isEmpty()){
            this.labelContainer = "";
        }
        this.labelContainer += newChar;
        String label = labelContainer + "|";
        this.setNewLabel(label);
    }

    /**
     * removes the last char from the label that is being edited
     */
    private void removeCharFromContainer(){
        if(this.labelContainer.length() > 0){
            String label = this.labelContainer.substring(0, labelContainer.length() - 2);
            labelContainer = label;
            this.setNewLabel(labelContainer + "|");
        }
    }

    /**
     * sets the label of the Label in edit to the given label
     *
     * @param label
     */
    private void setNewLabel(String label){
        try {
            boolean valid = editableLable.isValidLabel(label);
            editableLable.setLabel(label);
            if (valid) {
                this.setValidLabel(true);
                this.setLabelMode(false);
            }
        }
        catch (DomainException exc){
            System.out.println(exc.getMessage());
        }
    }

    /**
     * Finds the element that has been clicked by the location of the MouseEvent
     *
     * @param point2D the location of the MouseEvent
     * @return the element which has been clicked on
     */
    private Clickable selectClickableElement(Point2D point2D){
        List<Clickable> possibleElements = new ArrayList<>();
        for(Party party : this.getParties()){
            if(party.isClicked(point2D)){
                possibleElements.add(party);
            }
            if(party.getLabel().isClicked(point2D)){
                possibleElements.add(party.getLabel());
            }
            if(isLifeLine(point2D, party)){
                return new MessageStart(party, point2D);
            }
        }
        Message message = this.getFirstMessage();
        while(message != null) {
            if (message.isClicked(point2D)) {
                possibleElements.add(message);
            }
            message = message.getNextMessage();
        }
        if(possibleElements.size() == 1){
           return possibleElements.get(0);
        }
        else if(possibleElements.size() == 0){
            return null;
        }
        else{
            return findMostLikelyElement(possibleElements, point2D);
        }
    }

    /**
     * if multiple elements overlap on the location of a mouseEvent, this function will determine which element was clicked
     *
     * @param possibleElements all overlapping elements
     *
     * @param point2D the location of the MouseClick
     * @return the element that has been clicked on, based on distance to the MouseEvent
     */
    private Clickable findMostLikelyElement(List<Clickable> possibleElements, Point2D point2D){
        Clickable selected = possibleElements.get(0);
        double dist = possibleElements.get(0).getDistance(point2D);
        for(Clickable c : possibleElements){
            if(c.getDistance(point2D) < dist){
                dist = c.getDistance(point2D);
                selected = c;
            }
        }
        return selected;
    }

    /**
     * Finds the next position in the sequenceDiagram
     *
     * @param parties the list of parties currently in the diagram
     *
     * @return an integer denoting the next position
     */
    private int findNextPositionInSequenceDiagram(List<Party> parties){
        int pos = 0;
        for(Party party : parties){
            if(party.getPositionInSequenceDiagram() > pos){
                pos = party.getPositionInSequenceDiagram();
            }
        }
        return pos++;
    }

    /**
     *
     * @param party
     */
    private void updateMessagesForChangedParty(Party party){
        Message message = firstMessage;
        while(message != null){
            if(message.getReceiver().equals(party)){
                message.setReceiver(party);
            }
            if(message.getSender().equals(party)){
                try {
                    message.setSender(party);
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
     * @param yLocation
     * @return
     */

    private Message findPreviousMessage(int yLocation){
        Message message = this.getFirstMessage();
        if(message.getyLocation() > yLocation){
            return null;
        }
        Message next;
        while(message.getyLocation() < yLocation){
            next = message.getNextMessage();
            if(next != null){
                if(next.getyLocation() > yLocation){
                    return next;
                }
            }
        }
        return null;
    }

    /**
     * resets the position of the parties to the old positions
     *
     * @param oldParties a list of the old parties
     */
    public void resetPartyPositions(List<Party> oldParties){
        for(Party old : oldParties){
            for(Party np : this.getParties()){
                if(old.equals(np)){
                    np.setCoordinate(old.getCoordinate());
                }
            }
        }
    }

    /**
     * resets the positions of the parties in this diagram to positions valid for this diagram
     */
    public void resetToSequencePositions(){
        for(Party p : this.getParties()){
            p.setCoordinate(this.getValidPartyLocation(p.getCoordinate()));
        }
    }

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  abstract methods
    ////////////////////////////////////

    /**
     * Checks whether the location of the UIEvent is a valid location to trigger a new Party instantiation
     *
     * Has to be implemented in subclass
     *
     * @param point2D the position of the UIEvent
     * @return true if the location will trigger the addition of a new party to the diagram, false otherwise
     */
    public abstract boolean isValidPartyLocation(Point2D point2D);

    /**
     * Returns a valid location for the position of a party based on the provided location of the UIEvent
     *
     * Has to implemented in subclasses
     *
     * @param point2D the original position of the UIEvent
     * @return Point2D, the appropriate location for a new Party
     */
    public abstract Point2D getValidPartyLocation(Point2D point2D);

    /**
     * Determines if the location belongs to the lifeline of the given Party
     *
     * @param location the location of the ClickEvent
     * @param party a party of the diagram
     * @return true if the location belongs to the lifeline of the party, false otherwise
     */
    abstract boolean isLifeLine(Point2D location, Party party);

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  Helper class
    ////////////////////////////////////

    /**
     * Class to help adding messages, stocks the Startlocation and Sender of a new message
     */
    public class MessageStart implements Clickable{

        Party party;
        Point2D startloction;

        private MessageStart(Party party, Point2D startLocation){
            this.party = party;
            this.startloction = startLocation;
        }

        private Party getParty(){
            return this.party;
        }

        private Point2D getStartloction(){
            return this.startloction;
        }

        @Override
        public boolean isClicked(Point2D point2D) {
            return false;
        }

        @Override
        public double getDistance(Point2D point2D) {
            return 0;
        }
    }

}
