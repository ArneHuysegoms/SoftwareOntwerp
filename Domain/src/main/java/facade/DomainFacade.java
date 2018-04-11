package facade;

import diagram.Diagram;
import diagram.DiagramElement;
import diagram.label.Label;
import diagram.message.Message;
import diagram.party.Party;
import exceptions.DomainException;
import repo.diagram.CommunicationRepo;
import repo.diagram.DiagramRepo;
import repo.diagram.SequenceRepo;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Set;

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

    private void setSequenceRepo(DiagramRepo sequenceRepo) {
        this.sequenceRepo = sequenceRepo;
    }

    public DiagramRepo getCommunicationRepo() {
        return communicationRepo;
    }

    private void setCommunicationRepo(DiagramRepo communicationRepo) {
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

    /**
     * find the element on the given location
     *
     * this also sets the the selected element to the founded element in the active diagram
     *
     * @param location the location to find an element on
     * @return the element on the provided location, null if no such element exist
     */
    public DiagramElement findSelectedElement(Point2D location) throws DomainException{
        return this.getActiveRepo().getSelectedDiagramElement(location);
    }

    /**
     * deletes the element of the diagram whom the given label belongs to
     *
     * @param label the label of the element to delete
     */
    public void deleteElementByLabel(Label label){
        Set<DiagramElement> deletedElements = this.getDiagram().deleteElementByLabel(label);
        for(DiagramElement d : deletedElements){
            if(d instanceof Party){
                Party p = (Party) d;
                deletePartyInRepos(p);
            }
            else if(d instanceof Message){
                Message m = (Message) d;
                deleteMessageInRepos(m);
            }
        }
    }

    private void deleteMessageInRepos(Message message){
            activeRepo.getMessageRepo().removeMessage(message);
            activeRepo.getLabelRepo().removeLabel(message.getLabel());
            activeRepo.getMessageRepo().resetMessagePositions(diagram.getFirstMessage(), activeRepo.getPartyRepo(), activeRepo.getLabelRepo());
            DiagramRepo o = this.getOtherRepo();
            o.getMessageRepo().removeMessage(message);
            o.getLabelRepo().removeLabel(message.getLabel());
            o.getMessageRepo().resetMessagePositions(this.diagram.getFirstMessage(), o.getPartyRepo(), o.getLabelRepo());

    }

    private void deletePartyInRepos(Party party) {
        this.getActiveRepo().getPartyRepo().removeParty(party);
        this.getActiveRepo().getLabelRepo().removeLabel(party.getLabel());

        DiagramRepo other = this.getOtherRepo();

        other.getPartyRepo().removeParty(party);
        other.getLabelRepo().removeLabel(party.getLabel());
    }

    /**
     * change the location of the provided party to the provided location
     *
     * @param newLocation the new location
     * @param party the party to change the location of
     */
    public void changePartyPosition(Point2D newLocation, Party party){
        Point2D validNewLocation = this.getActiveRepo().getValidPartyLocation(newLocation);
        this.getActiveRepo().getPartyRepo().addPartyWithLocation(party, validNewLocation);
        this.getActiveRepo().getMessageRepo().resetLabelPositionsForMovedParty(getActiveRepo().getLabelRepo(), getActiveRepo().getPartyRepo(), party);
    }

    /**
     * adds a new message on the given location
     *
     * @param location the location to add a message on
     * @param messageStart the start of the message
     *
     * @return the label to edit
     */
    public DiagramElement addNewMessage(Point2D location, DiagramRepo.MessageStart messageStart) throws IllegalStateException{
        if(this.getActiveRepo() instanceof SequenceRepo) {
            SequenceRepo sequenceRepo = (SequenceRepo) this.getActiveRepo();
            Party Sender = messageStart.getParty();
            Party receiver = this.getActiveRepo().findReceiver(location);
            if (receiver != null) {
                int yLocation = new Double(messageStart.getStartloction().getY()).intValue();
                Message previous = sequenceRepo.getMessageRepo().findPreviousMessage(yLocation, diagram.getFirstMessage());
                List<Message> addedMessages = diagram.addNewMessage(Sender, receiver, previous);
                getActiveRepo().getMessageRepo().addMessages(addedMessages, diagram.getFirstMessage(), getActiveRepo().getPartyRepo(), getActiveRepo().getLabelRepo());
                this.getOtherRepo().getMessageRepo().addMessages(addedMessages, diagram.getFirstMessage(), getOtherRepo().getPartyRepo(),getOtherRepo().getLabelRepo());
                if(addedMessages.size() == 2){
                    return addedMessages.get(0).getLabel();
                }
                else{
                    throw new IllegalStateException("New messages weren't added");
                }
            }
        }
        return null;
    }

   /* *//*
     * start editing the currently selected label
     *//*
    public void editLabel(){
        this.getActiveDiagram().editLabel();
    }*/

    /*
     * stops editing the currently selected label
     *//*
    public void stopEditingLabel(){
        this.getActiveDiagram().stopEditingLabel();
    }*/

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
}
