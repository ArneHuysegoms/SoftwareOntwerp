package diagram;

import java.awt.geom.Point2D;

public class DomainFacade {

    private Diagram activeDiagram;

    private Diagram previousDiagram;

    public DomainFacade(){
        setActiveDiagram(new SequenceDiagram());
    }

    public Diagram getActiveDiagram(){ return activeDiagram;}

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

    public Clickable getSelectedElement(){ return this.getActiveDiagram().getSelectedElement();}

    public Clickable findSelectedElement(Point2D location){
        return this.getActiveDiagram().findSelectedElement(location);
    }

    public boolean selectedElementIsLabel(){
        return this.getActiveDiagram().selectedElementIsLabel();
    }

    public void editLabel(){
        this.getActiveDiagram().editLabel();
    }

    public Clickable wouldBeSelectedElement(Point2D location){
        return this.getActiveDiagram().wouldBeSelectedElement(location);
    }

    public void setSelectedElement(Clickable clickable){
        this.getActiveDiagram().setSelectedElement(clickable);
    }

    public boolean isLabel(Clickable clickable){
        return this.getActiveDiagram().isLabel(clickable);
    }

    public void stopEditingLabel(){
        this.getActiveDiagram().stopEditingLabel();
    }

    public void deleteElement(){
        this.getActiveDiagram().deleteElement();
    }

    public void addCharToLabel(char newChar){
        this.getActiveDiagram().addCharToLabel(newChar);
    }

    public void removeLastCharFromLabel(){
        this.getActiveDiagram().removeLastCharFromLabel();
    }

    public boolean selectedElementIsParty(){
        return this.getActiveDiagram().selectedElementIsParty();
    }

    public void changePartyPosition(Point2D location){
        this.getActiveDiagram().changePartyPosition(location);
    }

    public boolean selectedElementIsMessageStart(){
        return this.getActiveDiagram().selectedElementIsMessageStart();
    }

    public void addNewMessage(Point2D location){
        this.getActiveDiagram().addNewMessage(location);
    }

    public void changePartyType(Point2D location){
        this.getActiveDiagram().changePartyType(location);
    }

    public void addNewParty(Point2D location){
        this.getActiveDiagram().addNewParty(location);
    }

}
