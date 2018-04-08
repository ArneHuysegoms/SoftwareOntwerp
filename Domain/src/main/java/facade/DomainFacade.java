package facade;

import diagram.Diagram;
import diagram.party.Party;
import exceptions.DomainException;
import repo.diagram.CommunicationRepo;
import repo.diagram.DiagramRepo;
import repo.diagram.SequenceRepo;

import java.awt.geom.Point2D;

/**
 * this class servers as the entrypoint for all of the domain functionality
 */
public class DomainFacade {

    private DiagramRepo activeRepo;

    private DiagramRepo sequenceRepo;
    private DiagramRepo communicationRepo;

    private Diagram diagram;

    /**
     * Default constructor, starts with a sequencediagram as active diagram
     */
    public DomainFacade(){
        this(new Diagram(), new SequenceRepo(), new CommunicationRepo());
    }

    public DomainFacade(Diagram diagram, DiagramRepo sequenceRepo, DiagramRepo communicationRepo){
        this.setDiagram(diagram);
        this.setSequenceRepo(sequenceRepo);
        this.setCommunicationRepo(communicationRepo);
        this.setActiveRepo(sequenceRepo);
    }

    public DiagramRepo getActiveRepo() {
        return activeRepo;
    }

    public void setActiveRepo(DiagramRepo activeRepo) {
        this.activeRepo = activeRepo;
    }

    /**
     *
     * @return the currently active diagram
     */
    public Diagram getDiagram(){ return diagram;}

    /**
     * sets the diagram to the provided diagram
     *
     * @param diagram the new active diagram
     */
    private void setDiagram(Diagram diagram){ this.diagram = diagram;}

    public DiagramRepo getSequenceRepo() {
        return sequenceRepo;
    }

    public void setSequenceRepo(DiagramRepo sequenceRepo) {
        this.sequenceRepo = sequenceRepo;
    }

    public DiagramRepo getCommunicationRepo() {
        return communicationRepo;
    }

    public void setCommunicationRepo(DiagramRepo communicationRepo) {
        this.communicationRepo = communicationRepo;
    }

    /**
     * Change the active diagram to a diagram of the other type
     */
    public void changeActiveDiagram(){
        this.activeRepo = getOtherRepo();
    }

    /**
     * adds a new party on the given location of the type Object
     *
     * @param location the location of the new Party
     */
    public void addNewParty(Point2D location){
        Party newParty = this.getDiagram().addNewParty();
        addNewPartyToRepos(activeRepo, newParty, location);
        DiagramRepo other = getOtherRepo();
        addNewPartyToRepos(other, newParty, location);
    }

    /**
     * Changes the type of the party on the given location to the type of the opposite type
     *
     * @param oldParty the party to change the type of
     */
    public void changePartyType(Party oldParty) throws DomainException{
        Party newParty = diagram.changePartyType(oldParty);
        changePartyTypeInRepos(oldParty, newParty, activeRepo);
        DiagramRepo other = getOtherRepo();
        changePartyTypeInRepos(oldParty, newParty, other);
    }

    private DiagramRepo getOtherRepo(){
        if(activeRepo.equals(communicationRepo)){
            return sequenceRepo;
        }
        else {
            return communicationRepo;
        }
    }

    private void addNewPartyToRepos(DiagramRepo repo, Party newParty, Point2D location){
        if(repo.isValidPartyLocation(location)) {
            Point2D correctPartyLocation = repo.getValidPartyLocation(location);
            if(newParty != null){
                repo.getPartyRepo().addPartyWithLocation(newParty, location);
                repo.getLabelRepo().addLabelWithLocation(newParty.getLabel(),
                        new Point2D.Double(correctPartyLocation.getX() + 10,
                                correctPartyLocation.getY() + 20));
            }
        }
    }

    private void changePartyTypeInRepos(Party oldParty, Party newParty, DiagramRepo repo) throws DomainException {
        Point2D location = repo.getPartyRepo().getLocationOfParty(oldParty);
        Point2D labelLocation = repo.getLabelRepo().getLocationOfLabel(oldParty.getLabel());

        repo.getPartyRepo().removeParty(oldParty);
        repo.getLabelRepo().removeLabelByPosition(labelLocation);

        repo.getPartyRepo().addPartyWithLocation(newParty, location);
        Point2D labelPosition = repo.getPartyRepo().getCorrectLabelPosition(newParty);
        repo.getLabelRepo().addLabelWithLocation(newParty.getLabel(), labelPosition);
    }


    /*******************************************************************************************/

   /* *//*
     *
     * @return true if the label in edit is valid, false otherwise
     *//*
    public boolean checkIfValidLable(){
        return this.getActiveDiagram().isValidLabel();
    }*/

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

   /* *//*
     * start editing the currently selected label
     *//*
    public void editLabel(){
        this.getActiveDiagram().editLabel();
    }*/

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

    /*
     * stops editing the currently selected label
     *//*
    public void stopEditingLabel(){
        this.getActiveDiagram().stopEditingLabel();
    }*/

    /**
     * deletes the currently selected element
     */
    public void deleteElement(){
        this.getActiveDiagram().deleteElement();
    }

    /*
     * adds the given char to the currently selected label
     *
     * @param newChar the char to add
     *//*
    public void addCharToLabel(char newChar){
        this.getActiveDiagram().addCharToLabel(newChar);
    }

    *//*
     * removes the last char from the currently selected label
     *//*
    public void removeLastCharFromLabel(){
        this.getActiveDiagram().removeLastCharFromLabel();
    }*/

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
}
