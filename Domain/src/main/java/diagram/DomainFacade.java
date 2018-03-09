package diagram;

import java.awt.geom.Point2D;

/**
 * this class servers as the entrypoint for all of the domain functionality
 */
public class DomainFacade {

    private Diagram activeDiagram;

    private Diagram previousDiagram;

    /**
     * Default constructor, starts with a sequencediagram as active diagram
     */
    public DomainFacade(){
        setActiveDiagram(new SequenceDiagram());
    }

    /**
     *
     * @return the currently active diagram
     */
    public Diagram getActiveDiagram(){ return activeDiagram;}

    /**
     * sets the active diagram to the provided active diagram
     *
     * @param activeDiagram the new active diagram
     */
    private void setActiveDiagram(Diagram activeDiagram){ this.activeDiagram = activeDiagram;}

    /**
     * Change the active diagram to a diagram of the other type
     */
    public void changeActiveDiagram(){
        if(this.getActiveDiagram() instanceof SequenceDiagram){
            Diagram communication = new CommunicationsDiagram(activeDiagram.getParties(), activeDiagram.getFirstMessage(), activeDiagram.getSelectedElement(),
                    activeDiagram.getLabelContainer(), activeDiagram.isLabelMode(), activeDiagram.isValidLabel(), activeDiagram.isMessageMode());
            if(previousDiagram != null ) {
                communication.resetPartyPositions(previousDiagram.getParties());
            }
            this.previousDiagram = activeDiagram;
            activeDiagram = communication;
        } else{
            Diagram sequence =  new SequenceDiagram(activeDiagram.getParties(), activeDiagram.getFirstMessage(), activeDiagram.getSelectedElement(),
                    activeDiagram.getLabelContainer(), activeDiagram.isLabelMode(), activeDiagram.isValidLabel(), activeDiagram.isMessageMode());
            sequence.resetToSequencePositions();
            this.previousDiagram = activeDiagram;
            activeDiagram = sequence;
        }
    }

    /**
     *
     * @return true if the label in edit is valid, false otherwise
     */
    public boolean checkIfValidLable(){
        return this.getActiveDiagram().isValidLabel();
    }

    /**
     *
     * @return the currently selected element of the active diagram
     */
    public Clickable getSelectedElement(){ return this.getActiveDiagram().getSelectedElement();}

    /**
     * find the element on the given location
     *
     * this also sets the the selected element to the founded element in the active diagram
     *
     * @param location the location to find an element on
     * @return the element on the provided location, null if no such element exist
     */
    public Clickable findSelectedElement(Point2D location) {
        return this.getActiveDiagram().findSelectedElement(location);
    }

    /**
     *
     * @return wether the currently selected element is an label
     */
    public boolean selectedElementIsLabel(){
        return this.getActiveDiagram().selectedElementIsLabel();
    }

    /**
     * start editing the currently selected label
     */
    public void editLabel(){
        this.getActiveDiagram().editLabel();
    }

    /**
     * returns the element that would be selected element on that location
     *
     * @param location the location to inspect
     * @return the clickable that would be selected on that position
     */
    public Clickable wouldBeSelectedElement(Point2D location){
        return this.getActiveDiagram().wouldBeSelectedElement(location);
    }

    /**
     * sets the provided element as the selected element in the diagram
     *
     * @param clickable the element to set as the selected element
     */
    public void setSelectedElement(Clickable clickable){
        this.getActiveDiagram().setSelectedElement(clickable);
    }

    /**
     * checks wether or not the clickable element is a label
     *
     * @param clickable the clickable element to inspect
     * @return wether or not the clickable element is a label
     */
    public boolean isLabel(Clickable clickable){
        return this.getActiveDiagram().isLabel(clickable);
    }

    /**
     * stops editing the currently selected label
     */
    public void stopEditingLabel(){
        this.getActiveDiagram().stopEditingLabel();
    }

    /**
     * deletes the currently selected element
     */
    public void deleteElement(){
        this.getActiveDiagram().deleteElement();
    }

    /**
     * adds the given char to the currently selected label
     *
     * @param newChar the char to add
     */
    public void addCharToLabel(char newChar){
        this.getActiveDiagram().addCharToLabel(newChar);
    }

    /**
     * removes the last char from the currently selected label
     */
    public void removeLastCharFromLabel(){
        this.getActiveDiagram().removeLastCharFromLabel();
    }

    /**
     * returns whether or not the currently selected element is a party
     *
     * @return whether or not the currently selected element is a party
     */
    public boolean selectedElementIsParty(){
        return this.getActiveDiagram().selectedElementIsParty();
    }

    /**
     * change the location of the currently selected party to the provided location
     *
     * @param location the new location
     */
    public void changePartyPosition(Point2D location){
        this.getActiveDiagram().changePartyPosition(location);
    }

    /**
     *
     * @return whether or not the currently selected element is a messageStart
     */
    public boolean selectedElementIsMessageStart(){
        return this.getActiveDiagram().selectedElementIsMessageStart();
    }

    /**
     * adds a new message on the given location
     *
     * @param location the location to add a message on
     */
    public void addNewMessage(Point2D location){
        this.getActiveDiagram().addNewMessage(location);
    }

    /**
     * Changes the type of the party on the given location to the type of the opposite type
     *
     * @param location the location of the party to change the type of
     */
    public void changePartyType(Point2D location){
        this.getActiveDiagram().changePartyType(location);
    }

    /**
     * adds a new party on the given location of the type Object
     *
     * @param location the location of the new Party
     */
    public void addNewParty(Point2D location){
        this.getActiveDiagram().addNewParty(location);
    }

}
