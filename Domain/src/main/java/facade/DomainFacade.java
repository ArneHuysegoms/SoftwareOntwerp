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

    /**
     * Constructs a domainfacade with the given diagram, sequencerepo and communicationrepo
     *
     * @param diagram the diagram or this domainfacade
     * @param sequenceRepo the sequencerepo for this domainfacade
     * @param communicationRepo the communicationrepo for this domainfacade
     */
    public DomainFacade(Diagram diagram, DiagramRepo sequenceRepo, DiagramRepo communicationRepo){
        this.setDiagram(diagram);
        this.setSequenceRepo(sequenceRepo);
        this.setCommunicationRepo(communicationRepo);
        this.setActiveRepo(sequenceRepo);
    }

    /**
     * gives the repo detailing the current state of the diagram
     * @return a diagramrepo containing the current state of the diagram
     */
    public DiagramRepo getActiveRepo() {
        return activeRepo;
    }

    /**
     * sets the repo that contains the active state of the diagram
     * @param activeRepo the state of the diagram we want to be the current one
     */
    private void setActiveRepo(DiagramRepo activeRepo) {
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
     *
     * @throws IllegalArgumentException if the diagram is null
     */
    private void setDiagram(Diagram diagram) throws IllegalArgumentException{
        if(diagram == null){
            throw new IllegalArgumentException("diagram may not be null");
        }
        this.diagram = diagram;}

    /**
     * @return the sequencerepo of this facade
     */
    public DiagramRepo getSequenceRepo() {
        return sequenceRepo;
    }

    /**
     * sets the sequencerepo for this domainfacade
     * @param sequenceRepo the sequencerepo for the domainfacade
     * @throws IllegalArgumentException if the provided repo is null
     */
    private void setSequenceRepo(DiagramRepo sequenceRepo) throws IllegalArgumentException{
        if(sequenceRepo == null){
            throw new IllegalArgumentException("sequencerepo may not be null");
        }
        this.sequenceRepo = sequenceRepo;
    }

    /**
     * @return the communicationrepo of this domainfacade
     */
    public DiagramRepo getCommunicationRepo() {
        return communicationRepo;
    }

    /**
     * sets the communicationRepo for this domainfacade
     * @param communicationRepo the Communicationrepo for this domainfacade
     * @throws IllegalArgumentException if the communicationrepo is null
     */
    private void setCommunicationRepo(DiagramRepo communicationRepo) throws IllegalArgumentException {
        if(communicationRepo == null){
            throw new IllegalArgumentException("communication repo may not be null");
        }
        this.communicationRepo = communicationRepo;
    }

    /**
     * Change the active diagram to a diagram of the other type
     */
    public void changeActiveDiagram(){
        this.activeRepo = getOtherRepo();
    }

    /**
     * adds a new party of the type Object on the given location
     *
     * @param location the location of the new Party
     * @return party the newly added party
     */
    public Party addNewParty(Point2D location){
        Party newParty = this.getDiagram().addNewParty();
        activeRepo.addNewPartyToRepos(newParty, location);
        getOtherRepo().addNewPartyToRepos(newParty, location);
        return newParty;
    }

    /**
     * puts a party in the repos with the given location without inserting a new one in the diagram
     * @param party the party to add
     * @param location the location of the party
     */
    public void addPartyToRepo(Party party, Point2D location){
        activeRepo.addNewPartyToRepos(party, location);
        getOtherRepo().addNewPartyToRepos(party, location);
    }

    /**
     * Changes the type of the party on the given location to the type of the opposite type
     *
     * @param oldParty the party to change the type of
     * @return the new Party
     */
    public Party changePartyType(Party oldParty) throws DomainException{
        Party newParty = diagram.changePartyType(oldParty);
        activeRepo.changePartyTypeInRepos(oldParty, newParty);
        getOtherRepo().changePartyTypeInRepos(oldParty, newParty);
        return newParty;
    }

    /**
     * returns the repo that is currently not the active repo
     *
     * @return the non-active repository
     */
    public DiagramRepo getOtherRepo(){
        if(activeRepo.equals(communicationRepo)){
            return sequenceRepo;
        }
        else {
            return communicationRepo;
        }
    }

    /**
     * find the element on the given location
     *
     * this also sets the the selected element to the founded element in the active diagram
     *
     * @param location the location to find an element on
     * @return the element on the provided location, null if no such element exist
     */
    public DiagramElement findSelectedElement(Point2D location){
        return this.getActiveRepo().getSelectedDiagramElement(location);
    }

    /**
     * deletes the element of the diagram whom the given label belongs to
     *
     * @param label the label of the element to delete
     * @return a set of elements that was deleted by deleting the diagramelement with the given label in the diagram
     */
    public Set<DiagramElement> deleteElementByLabel(Label label){
        Set<DiagramElement> deletedElements = this.getDiagram().deleteElementByLabel(label);
        deleteElementsInRepos(deletedElements);
        return deletedElements;
    }

    /**
     * deletes the given diagramelements in the repos
     * @param deletedElements the elements to remove
     */
    public void deleteElementsInRepos(Set<DiagramElement> deletedElements){
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

    /**
     * deletes the given message in both repos
     * @param message the message to be deleted
     */
    private void deleteMessageInRepos(Message message){
        Message firstMessage = diagram.getFirstMessage();
        activeRepo.deleteMessageInRepos(message, firstMessage);
        getOtherRepo().deleteMessageInRepos(message, firstMessage);
    }

    /**
     * deletes the given party in both repos, with cascading effect
     * @param party the party to be deleted
     */
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
        this.getActiveRepo().getLabelRepo().updateLabelPosition(getActiveRepo().getPartyRepo().getCorrectLabelPosition(party), party.getLabel());
        this.getActiveRepo().getMessageRepo().resetLabelPositionsForMovedParty(getActiveRepo().getLabelRepo(), getActiveRepo().getPartyRepo(), party);
    }

    /**
     * adds a new message on the given location
     *
     * @param location the location to add a message on
     * @param messageStart the start of the message
     *
     * @return a list containing the newly added messages
     */
    public List<Message> addNewMessage(Point2D location, DiagramRepo.MessageStart messageStart) throws IllegalStateException{
        if(this.getActiveRepo() instanceof SequenceRepo) {
            SequenceRepo sequenceRepo = (SequenceRepo) this.getActiveRepo();
            Party Sender = messageStart.getParty();
            Party receiver = this.getActiveRepo().findReceiver(location);
            if (receiver != null) {
                int yLocation = new Double(messageStart.getStartloction().getY()).intValue();
                Message previous = sequenceRepo.getMessageRepo().findPreviousMessage(yLocation, diagram.getFirstMessage());
                List<Message> addedMessages = diagram.addNewMessage(Sender, receiver, previous);
                addMessagesToRepos(addedMessages);
                if(addedMessages.size() == 2){
                    return addedMessages;
                }
                else{
                    throw new IllegalStateException("New messages weren't added");
                }
            }
        }
        return null;
    }

    public void addMessagesToRepos(List<Message> messages){
        getActiveRepo().getMessageRepo().addMessages(messages, diagram.getFirstMessage(), getActiveRepo().getPartyRepo(), getActiveRepo().getLabelRepo());
        this.getOtherRepo().getMessageRepo().addMessages(messages, diagram.getFirstMessage(), getOtherRepo().getPartyRepo(),getOtherRepo().getLabelRepo());
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
