package canvascomponents.diagram;

import canvascomponents.Clickable;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public abstract class Diagram{

    private boolean labelMode;
    private boolean validLabel;
    private boolean messageMode;

    private Map<Party, Point2D> partiesWithCoordinates;
    private Map<Message, Point2D> messagesWithCoordinates;

    private String labelContainer = "";

    private Clickable selectedElement;

    private Message firstMessage;

    public Diagram(){
        this(new HashMap<>(), new HashMap<>());
    }

    public Diagram(Map<Party, Point2D> partiesWithCoordinates, Map<Message, Point2D> messagesWithCoordinates){
        this.setPartiesWithCoordinates(partiesWithCoordinates);
        this.setMessagesWithCoordinates(messagesWithCoordinates);
        this.setFirstMessage(this.getFirstMessageFromStack(messagesWithCoordinates));
    }

    public Diagram(Map<Party, Point2D> partiesWithCoordinates, Map<Message, Point2D> messagesWithCoordinates, Message firstMessage){
        this(partiesWithCoordinates, messagesWithCoordinates, firstMessage, null);
    }

    public Diagram(Map<Party, Point2D> partiesWithCoordinates, Map<Message, Point2D> messagesWithCoordinates, Message firstMessage, Clickable selectedElement){
        this(partiesWithCoordinates, messagesWithCoordinates, firstMessage, selectedElement, "");
    }

    public Diagram(Map<Party, Point2D> partiesWithCoordinates, Map<Message, Point2D> messagesWithCoordinates, Message firstMessage, Clickable selectedElement, String labelContainer){
        this(partiesWithCoordinates, messagesWithCoordinates, firstMessage, selectedElement, labelContainer, false, false, false);
    }

    public Diagram(Map<Party, Point2D> partiesWithCoordinates, Map<Message, Point2D> messagesWithCoordinates, Message firstMessage, Clickable selectedElement,
                   String labelContainer, boolean labelMode, boolean validLabel, boolean messageMode){
        this.setMessagesWithCoordinates(messagesWithCoordinates);
        this.setPartiesWithCoordinates(partiesWithCoordinates);
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

    private void setPartiesWithCoordinates(Map<Party, Point2D> partiesWithCoordinates){
        this.partiesWithCoordinates = partiesWithCoordinates;
    }

    public Map<Party, Point2D> getPartiesWithCoordinates(){
        return this.partiesWithCoordinates;
    }

    private void setMessagesWithCoordinates(Map<Message, Point2D> messagesWithCoordinates){
        this.messagesWithCoordinates = messagesWithCoordinates;
    }

    public Map<Message, Point2D> getMessagesWithCoordinates(){
        return this.messagesWithCoordinates;
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

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  utlities
    ////////////////////////////////////

    private Message getFirstMessageFromStack(Map<Message, Point2D> messagesWithCoordinates){
        Message first = null;
        double yLocation = -999999999;
        for(Map.Entry<Message, Point2D> entry : messagesWithCoordinates.entrySet()){
            if(entry.getValue().getY() < yLocation){
                first = entry.getKey();
                yLocation = entry.getValue().getY();
            }
        }
        return first;
    }

    private void appendCharToLabel(char newChar){
        if(this.labelContainer.isEmpty()){
            this.labelContainer = "";
        }
        this.labelContainer += newChar;
    }

    private void selectClickableElement(Point2D point2D){

    }

    /**********************************************************************************************************/

    ////////////////////////////////////
    //  abstract methods
    ////////////////////////////////////

    public abstract boolean isValidPartyLocation(Point2D point2D);

    public abstract Point2D getValidLocation(Point2D point2D);
}
