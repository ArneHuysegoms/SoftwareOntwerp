package canvascomponents.diagram;

import canvascomponents.Clickable;
import exceptions.DomainException;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

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

    public Diagram() {
        this(null, null);
    }

    public Diagram(List<Party> parties, Message firstMessage){
        this(parties, firstMessage, null);
    }

    public Diagram(List<Party> parties, Message firstMessage, Clickable selectedElement){
        this(parties, firstMessage, selectedElement, "");
    }

    public Diagram(List<Party> parties, Message firstMessage, Clickable selectedElement, String labelContainer){
        this(parties, firstMessage, selectedElement, labelContainer, false, false, false);
    }

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

    private void setSelectedElement(Clickable selectedElement){
        this.selectedElement = selectedElement;
    }

    public Clickable getSelectedElement() {
        return selectedElement;
    }

    private void setFirstMessage(Message message){
        this.firstMessage = message;
    }

    public Message getFirstMessage() {
        return firstMessage;
    }

    public List<Party> getParties() {
        return parties;
    }

    private void setParties(List<Party> parties) {
        if(parties != null ) {
            this.parties = parties;
        }
        else{
            this.parties = new ArrayList<>();
        }
    }

    public void addParty(Party party){
        this.getParties().add(party);
    }

    public void removeParty(Party party){
        this.getParties().remove(party);
    }

    private void setLabelMode(boolean labelMode){
        this.labelMode = labelMode;
    }

    public boolean isLabelMode(){
        return this.labelMode;
    }

    private void setValidLabel(boolean validLabel){
        this.validLabel = validLabel;
    }

    public boolean isValidLabel(){
        return this.validLabel;
    }

    private void setMessageMode(boolean messageMode){
        this.messageMode = messageMode;
    }

    public boolean isMessageMode(){
        return this.isMessageMode();
    }

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  main part of business logic
    ////////////////////////////////////

    public void addNewParty(Point2D point2D){
        int posSeq = findNextPositionInSequenceDiagram(this.getParties());
        Point2D finalPosition = null;
        PartyLabel label;
        if(isValidPartyLocation(point2D)){
            finalPosition = getValidLocation(point2D);
            try {
                label = new PartyLabel("I", new Point2D.Double(point2D.getX() -80, point2D.getY() + 51));
                Object object = new Object(posSeq, finalPosition, label);
                startEditingLable(label);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

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

    public void addNewMessage(Point2D endlocation) throws IllegalStateException{
        Party receiver = findReceiver(endlocation);
        if(receiver == null){
            throw new IllegalStateException("Other lifeline not found");
        }
        else {
            Lifeline startLifeline = (Lifeline) this.getSelectedElement();
            Party sender = startLifeline.getParty();
            Point2D startLocation = startLifeline.getStartloction();
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

    public void changePartyPosition(Point2D newPosition){
        if(this.getSelectedElement() instanceof Actor){
            Actor a = (Actor) this.getSelectedElement();
            a.setCoordinate(newPosition);
        }
    }

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

    public void addCharToLabel(char newChar){
        appendCharToLabel(newChar);
    }

    public void editLable(){
        startEditingLable((Label) this.getSelectedElement());
    }

    public void stopEditingLabel(){
        try {
            this.editableLable.setLabel(labelContainer);
        }
        catch (DomainException exc){
            System.out.println(exc.getMessage());
        }
    }

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

    private Party findReceiver(Point2D endlocation){
        for(Party party : parties){
            if(isLifeLine(endlocation, party)){
                return party;
            }
        }
        return null;
    }

    private void deleteParty(Party party){
        rearrangeMessageTreeByParty(party);
        this.removeParty(party);
    }

    private void deleteMessage(Message message){
        Message iter = this.getFirstMessage();
        Message previous;
        while(! iter.getNextMessage().equals(message)){
            iter = iter.getNextMessage();
        }
        previous = iter;
        Message next = skipOverDependentMessages(previous, -1);
        previous.setNextMessage(next);
    }

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
            return message;
        }
        return null;
    }

    private boolean checkCallStack(Party party){
        Message message = getFirstMessage();
        return party.equals(message.getSender());
    }

    private void startEditingLable(Label label){
        this.setSelectedElement(label);
        this.setLabelMode(true);
        this.setValidLabel(false);
        this.editableLable = label;
    }


    private void appendCharToLabel(char newChar){
        if(this.labelContainer.isEmpty()){
            this.labelContainer = "";
        }
        this.labelContainer += newChar;
        String label = labelContainer + "|";
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
                return new Lifeline(party, point2D);
            }
        }
        Message message = this.getFirstMessage();
        while(message != null) {
            if (message.isClicked(point2D)) {
                possibleElements.add(message);
                message = message.getNextMessage();
            }
        }
        if(possibleElements.size() == 1){
           return possibleElements.get(0);
        }
        else{
            return findMostLikelyElement(possibleElements, point2D);
        }
    }

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

    private int findNextPositionInSequenceDiagram(List<Party> parties){
        int pos = 0;
        for(Party party : parties){
            if(party.getPositionInSequenceDiagram() > pos){
                pos = party.getPositionInSequenceDiagram();
            }
        }
        return pos++;
    }

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

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  abstract methods
    ////////////////////////////////////

    public abstract boolean isValidPartyLocation(Point2D point2D);

    public abstract Point2D getValidLocation(Point2D point2D);

    abstract boolean isLifeLine(Point2D location, Party party);

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  anonymous class
    ////////////////////////////////////

    class Lifeline implements Clickable{

        Party party;
        Point2D startloction;

        private Lifeline(Party party, Point2D startLocation){
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
