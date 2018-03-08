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

//    /**
//     * creates an empty diagram
//     */
//
//    public Diagram() {
//        this(null, null);
//    }
//
//    /**
//     * creates a new diagram with the given parties and the first message of the call stack
//     *
//     * @param parties
//     * @param firstMessage
//     */
//
//    public Diagram(List<Party> parties, Message firstMessage){
//        this(parties, firstMessage, null);
//    }
//
//    /**
//     * creates a new diagram with the given parties, the first message of the call stack and the currently selected element
//     *
//     * @param parties
//     * @param firstMessage
//     * @param selectedElement
//     */
//
//    public Diagram(List<Party> parties, Message firstMessage, Clickable selectedElement){
//        this(parties, firstMessage, selectedElement, "");
//    }
//
//    /**
//     * creates a new diagram with the given parties, the first message of the call stack, the currently selected element and the text for the label in edit
//     *
//     * @param parties
//     * @param firstMessage
//     * @param selectedElement
//     * @param labelContainer
//     */
//
//    public Diagram(List<Party> parties, Message firstMessage, Clickable selectedElement, String labelContainer){
//        this(parties, firstMessage, selectedElement, labelContainer, false, true, false);
//    }


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
        this.setMessageNumbers();
        }

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  setters and getters
    ////////////////////////////////////

    /**
     * sets the editable label to the given label
     *
     * @param label
     */
    private void setEditableLable(Label label){
        this.editableLable = label;
    }

    /**
     * sets the selected element of the diagram
     * @param selectedElement
     */
    public void setSelectedElement(Clickable selectedElement){
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

    /**
     * checks if the currently selected element is a label
     *
     * @return true if the the currently selected element is a label, false otherwise
     */
    public boolean selectedElementIsLabel(){
        return this.getSelectedElement() instanceof Label;
    }

    /**
     * checks if the currently selected element is a Party
     *
     * @return true if the the currently selected element is a Party, false otherwise
     */
    public boolean selectedElementIsParty(){
        return this.getSelectedElement() instanceof Party;
    }
    /**
     * checks if the currently selected element is an Actor
     *
     * @return true if the the currently selected element is a Party, false otherwise
     */
    public boolean selectedElementIsActor(){
        return this.getSelectedElement() instanceof Actor;
    }
    /**
     * checks if the currently selected element is an Object
     *
     * @return true if the the currently selected element is a Party, false otherwise
     */
    public boolean selectedElementIsObject(){
        return this.getSelectedElement() instanceof Object;
    }

    /**
     * checks if the currently selected element is a MessageStart
     *
     * @return true if the the currently selected element is a MessageStart, false otherwise
     */
    public boolean selectedElementIsMessageStart(){
        return this.getSelectedElement() instanceof MessageStart;
    }

    /**
     * checks if the currently selected element is a message
     *
     * @return true if the the currently selected element is a message, false otherwise
     */
    public boolean selectedElementIsMessage() {
        return this.selectedElement instanceof Message;
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
        Point2D finalPosition = null;
        PartyLabel label;
        if(isValidPartyLocation(point2D)){
            finalPosition = getValidPartyLocation(point2D);
            try {
                label = new PartyLabel("|", new Point2D.Double(finalPosition.getX() + 10, finalPosition.getY() + 20));
                Object object = new Object(0, finalPosition, label);
                this.addParty(object);
                startEditingLable(label);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        setSequenceNumbers();
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
                    newActor.updateLabelCoordinate(newActor.getCorrectLabelPosition());
                    this.updateMessagesForChangedParty(o, newActor);
                    this.removeParty(o);
                    this.addParty(newActor);
                    selectedElement = newActor;
                }
                catch (DomainException exception){
                    System.out.println(exception.getMessage());
                }
            }
            else{
                Actor a = (Actor) this.getSelectedElement();
                try {
                    Party newObject = new Object(a.getInstanceName(), a.getClassName(), a.getPositionInSequenceDiagram(), a.getCoordinate(), a.getLabel());
                    newObject.updateLabelCoordinate(newObject.getCorrectLabelPosition());
                    this.updateMessagesForChangedParty(a, newObject);
                    this.removeParty(a);
                    this.addParty(newObject);
                    selectedElement = newObject;
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
     * finds the element that is selected based on the click on the provided location
     *
     * @param point2D the location that was clicked on
     * @return the element that was clicked on
     */
    public Clickable findSelectedElement(Point2D point2D){
        if(! (selectClickableElement(point2D) instanceof Label)){
            stopEditingLabel();
            this.setLabelMode(false);
            labelContainer = "";
            editableLable = null;
        }
        Clickable selected = selectClickableElement(point2D);
        this.setSelectedElement(selected);
        if(selected instanceof Label){
            this.setEditableLable((Label) selected);
        }
        return selected;
    }

    /**
     * finds the selected element without setting it as the selected element
     *
     * @param location the location of the ui event
     * @return the element that would be selected
     */
    public Clickable wouldBeSelectedElement(Point2D location){
        Clickable selected = selectClickableElement(location);
        if(! (selected instanceof Label)){
            stopEditingLabel();
            this.setLabelMode(false);
            labelContainer = "";
            editableLable = null;
        }
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
        Label label = (Label) this.getSelectedElement();
        labelContainer = label.getLabel();
        startEditingLable(label);
    }

    /**
     * Stop editing the label that was selected
     *
     * @throws DomainException if the final label isn't valid
     */
    public void stopEditingLabel(){
        try {
            if(editableLable != null ) {
                this.editableLable.setLabel(labelContainer);
            }
        }
        catch (DomainException exc){
            System.out.println(exc.getMessage());
        }
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
     * updates the sequence Numbers of all the parties currently in the diagram based on their current xCoordinate on the diagram
     */
    private void setSequenceNumbers(){
        int pos = 1;
        List<Party> sorted = new ArrayList<>();
        List<Party> notSorted = new ArrayList<>(this.getParties());
        for(int i = 0; i < this.getParties().size(); i++){
            Party next = notSorted.get(0);
            for(int j = i; j < notSorted.size(); j++){
                if(notSorted.get(j).getCoordinate().getX() < next.getCoordinate().getX()){
                    next = notSorted.get(j);
                }
            }
            try {
                next.setPositionInSequenceDiagram(pos);
                pos++;
                sorted.add(next);
                notSorted.remove(next);
            }
            catch (DomainException exception){
                System.out.println(exception.getMessage());
            }
        }
    }

    /**
     * loops over the callStack to give each invocation their appropriate messageNumber
     *
     * this method serves as entrypoint to the real method, and gives the appropriate input
     */
    private void setMessageNumbers(){
        if(this.getFirstMessage() != null) {
            setMessageNumbers(1, 1, false,0);
        }
    }

    /**
     * loops over the callStack to give each invocation their appropriate messageNumber
     *
     * this method should not be called directly, instead use the setMessageNumbers() method. that method will call this one with
     * the correct input.
     *
     * @param N the N part of the messageNumber as specified by the assignment
     * @param e the eta part of the messageNumber as specified by the assignment
     */
    private void setMessageNumbers(int N, int e, boolean active, int stack) {
        Message message = this.getFirstMessage();
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

    private int setStackNumber(Message m, int stack){
        return 0;
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
        if( ! message.equals(this.getFirstMessage())){
            Message iter = this.getFirstMessage();
            Message previous;
            while(iter.getNextMessage() != null && ! iter.getNextMessage().equals(message)){
                iter = iter.getNextMessage();
            }
            previous = iter;
            Message next = skipOverDependentMessages(message, -1);
            previous.setNextMessage(next);
        }
        else{
            this.setFirstMessage(skipOverDependentMessages(message, -1));
        }
    }

    /**
     * deletes a message or a party if the provided label is its label
     *
     * @param label the label of the element to delete
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
        if(previous == null || (previous instanceof InvocationMessage && previous.getReceiver().equals(sender))){
            return true;
        }
        else if(this.getFirstMessage() != null){
            return this.getFirstMessage().getSender().equals(sender) && ! (previous instanceof InvocationMessage);
        }
        return false;
    }

    /**
     * return wethers or not this clickable is a Label object
     *
     * @param clickable the clickable to inspect
     * @return true if the clickable is an instance of Label
     */
    public boolean isLabel(Clickable clickable){
        return clickable instanceof Label;
    }

//    /**
//     * Finds the first message send by the provided party
//     *
//     * @param party the party to find the first send message of
//     * @return the first message send by the party, null if the party has never send a message
//     */
//    private Message findFirstMessageSendByParty(Party party){
//        if(this.getFirstMessage() != null){
//            if(this.getFirstMessage().getSender().equals(party)){
//                return this.getFirstMessage();
//            }
//            else {
//                Message looper = this.getFirstMessage();
//                boolean found = false;
//                while (!found && looper != null) {
//                    if (looper.getSender().equals(party)) {
//                        found = true;
//                        return looper;
//                    } else {
//                        looper = looper.getNextMessage();
//                    }
//                }
//            }
//        }
//         return null;
//    }

//    //TODO
//
//    /**
//     * checks the stack to see if message can be at the stack
//     *
//     * @param message the message to add to the stack
//     * @param stack an int value to keep track of the depth of the call tree by the parties
//     *
//     * @return if the message can be added to the message tree
//     */
//    private boolean checkStack(Message message, int stack){
//        if(message == null){
//            return false;
//        }
//        if(stack < 0 ){
//            message = message.getNextMessage();
//            if(message != null) {
//                if (message instanceof InvocationMessage) {
//                    return checkStack(message, --stack);
//                } else if (message instanceof ResultMessage) {
//                    return checkStack(message, ++stack);
//                }
//            }
//            else{
//                return false;
//            }
//        }
//        else{
//            return true;
//        }
//        return false;
//    }

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
        if(Label.isCorrectCharForLabel(newChar)) {
            this.labelContainer += newChar;
            String label = labelContainer + "|";
            this.setNewLabel(label);
        }
    }

    /**
     * removes the last char from the label that is being edited
     */
    private void removeCharFromContainer(){
        if(this.labelContainer.length() > 0){
            String label = this.labelContainer.substring(0, labelContainer.length() - 1);
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
           this.setValidLabel(valid);
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
        Message message = this.getFirstMessage();
        while(message != null) {
            if (message.isClicked(point2D)) {
                possibleElements.add(message);
            }
            if(message.getLabel().isClicked(point2D)){
                possibleElements.add(message.getLabel());
            }
            message = message.getNextMessage();
        }
        for(Party party : this.getParties()){
            if(party.isClicked(point2D)){
                possibleElements.add(party);
            }
            if(party.getLabel().isClicked(point2D)){
                possibleElements.add(party.getLabel());
            }
            if(isLifeLine(point2D, party)){
                if(possibleElements.size() == 0 ) {
                    return new MessageStart(party, point2D);
                }
            }
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
    public abstract boolean isLifeLine(Point2D location, Party party);

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
