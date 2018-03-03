package canvascomponents.diagram;

import canvascomponents.Clickable;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Diagram{

    private boolean labelMode;
    private boolean validLabel;
    private boolean messageMode;

    private List<Party> parties;

    private String labelContainer = "";

    private Clickable selectedElement;

    private Message firstMessage;

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

    }

    public void changePartyType(Point2D point2D){
        
    }

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  utlities
    ////////////////////////////////////


    private void appendCharToLabel(char newChar){
        if(this.labelContainer.isEmpty()){
            this.labelContainer = "";
        }
        this.labelContainer += newChar;
    }

    private void selectClickableElement(Point2D point2D){
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
            this.setSelectedElement(possibleElements.get(0));
        }
        else{
            findMostLikelyElement(possibleElements, point2D);
        }
    }

    private void findMostLikelyElement(List<Clickable> possibleElements, Point2D point2D){
        double distance = 99999999;

    }

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  abstract methods
    ////////////////////////////////////

    public abstract boolean isValidPartyLocation(Point2D point2D);

    public abstract Point2D getValidLocation(Point2D point2D);
}
