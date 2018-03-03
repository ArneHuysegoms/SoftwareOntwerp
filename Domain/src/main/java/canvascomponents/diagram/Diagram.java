package canvascomponents.diagram;

import canvascomponents.Clickable;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Label label;
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
                Party newActor = new Actor(o.getInstanceName(), o.getClassName(), o.getPositionInSequenceDiagram(), o.getCoordinate(),o.getLabel());
                this.updateMessagesForChangedParty(newActor);
                this.removeParty(o);
                this.addParty(newActor);
            }
            else{
                Actor a = (Actor) this.getSelectedElement();
                Party newObject = new Object(a.getInstanceName(), a.getClassName(), a.getPositionInSequenceDiagram(),a.getCoordinate(), a.getLabel());
                this.updateMessagesForChangedParty(newObject);
                this.removeParty(a);
                this.addParty(newObject);
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
            this.setLabelMode(false);
            labelContainer = "";
            editableLable = null;
        }
        Clickable selected = selectClickableElement(point2D);
        this.setSelectedElement(selected);
        return selected;
    }

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  utlities
    ////////////////////////////////////

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
        boolean valid = editableLable.setLabel(label);
        if(valid){
            this.setValidLabel(true);
            this.setLabelMode(false);
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
                message.setSender(party);
            }
            message = message.getNextMessage();
        }
    }

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  abstract methods
    ////////////////////////////////////

    public abstract boolean isValidPartyLocation(Point2D point2D);

    public abstract Point2D getValidLocation(Point2D point2D);
}
