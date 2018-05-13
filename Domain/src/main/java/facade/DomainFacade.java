package facade;

import diagram.Diagram;
import diagram.DiagramElement;
import diagram.label.Label;
import diagram.message.Message;
import diagram.party.Party;
import view.diagram.CommunicationView;
import view.diagram.DiagramView;
import view.diagram.SequenceView;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Set;

/**
 * this class servers as the entrypoint for all of the domain functionality
 */
public class DomainFacade {

    private DiagramView activeRepo;

    private DiagramView sequenceRepo;
    private DiagramView communicationRepo;

    private Diagram diagram;

    /**
     * Default constructor, starts with a sequencediagram as active diagram
     */
    public DomainFacade(){
        this(new Diagram(), new SequenceView(), new CommunicationView());
    }

    /**
     * Constructs a domainfacade with the given diagram, sequencerepo and communicationrepo
     *
     * @param diagram the diagram or this domainfacade
     * @param sequenceRepo the sequencerepo for this domainfacade
     * @param communicationRepo the communicationrepo for this domainfacade
     */
    public DomainFacade(Diagram diagram, DiagramView sequenceRepo, DiagramView communicationRepo){
        this.setDiagram(diagram);
        this.setSequenceRepo(sequenceRepo);
        this.setCommunicationRepo(communicationRepo);
        this.setActiveRepo(sequenceRepo);
    }

    /**
     * gives the view detailing the current state of the diagram
     * @return a diagramrepo containing the current state of the diagram
     */
    public DiagramView getActiveRepo() {
        return activeRepo;
    }

    /**
     * sets the view that contains the active state of the diagram
     * @param activeRepo the state of the diagram we want to be the current one
     */
    public void setActiveRepo(DiagramView activeRepo) {
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
    public DiagramView getSequenceRepo() {
        return sequenceRepo;
    }

    /**
     * sets the sequencerepo for this domainfacade
     * @param sequenceRepo the sequencerepo for the domainfacade
     * @throws IllegalArgumentException if the provided view is null
     */
    private void setSequenceRepo(DiagramView sequenceRepo) throws IllegalArgumentException{
        if(sequenceRepo == null){
            throw new IllegalArgumentException("sequencerepo may not be null");
        }
        this.sequenceRepo = sequenceRepo;
    }

    /**
     * @return the communicationrepo of this domainfacade
     */
    public DiagramView getCommunicationRepo() {
        return communicationRepo;
    }

    /**
     * sets the communicationRepo for this domainfacade
     * @param communicationRepo the Communicationrepo for this domainfacade
     * @throws IllegalArgumentException if the communicationrepo is null
     */
    private void setCommunicationRepo(DiagramView communicationRepo) throws IllegalArgumentException {
        if(communicationRepo == null){
            throw new IllegalArgumentException("communication view may not be null");
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
    public Party changePartyType(Party oldParty){
        Party newParty = diagram.changePartyType(oldParty);
        changePartyTypeInRepo(oldParty, newParty);
        return newParty;
    }

    /**
     * change the partyType of the old party to that of the new Party
     * @param oldParty oldParty the old type
     * @param newParty newParty the new type
     */
    public void changePartyTypeInRepo(Party oldParty, Party newParty){
        activeRepo.changePartyTypeInRepos(oldParty, newParty);
        getOtherRepo().changePartyTypeInRepos(oldParty, newParty);
        activeRepo.getMessageView().resetMessagePositions(diagram.getFirstMessage(), activeRepo.getPartyView(), activeRepo.getLabelView());
        getOtherRepo().getMessageView().resetMessagePositions(diagram.getFirstMessage(), getOtherRepo().getPartyView(), getOtherRepo().getLabelView());
    }

    /**
     * returns the view that is currently not the active view
     *
     * @return the non-active repository
     */
    public DiagramView getOtherRepo(){
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
        this.getActiveRepo().getPartyView().removeParty(party);
        this.getActiveRepo().getLabelView().removeLabel(party.getLabel());

        DiagramView other = this.getOtherRepo();

        other.getPartyView().removeParty(party);
        other.getLabelView().removeLabel(party.getLabel());
    }

    /**
     * change the location of the provided party to the provided location
     *
     * @param newLocation the new location
     * @param party the party to change the location of
     */
    public void changePartyPosition(Point2D newLocation, Party party){
        Point2D validNewLocation = this.getActiveRepo().getValidPartyLocation(newLocation);
        this.getActiveRepo().getPartyView().addPartyWithLocation(party, validNewLocation);
        this.getActiveRepo().getLabelView().updateLabelPosition(getActiveRepo().getPartyView().getCorrectLabelPosition(party), party.getLabel());
        this.getActiveRepo().getMessageView().resetLabelPositionsForMovedParty(getActiveRepo().getLabelView(), getActiveRepo().getPartyView(), party);
    }

    /**
     * adds a new message on the given location
     *
     * @param location the location to add a message on
     * @param messageStart the start of the message
     *
     * @return a list containing the newly added messages
     */
    public List<Message> addNewMessage(Point2D location, DiagramView.MessageStart messageStart) throws IllegalStateException{
        if(this.getActiveRepo() instanceof SequenceView) {
            SequenceView sequenceRepo = (SequenceView) this.getActiveRepo();
            Party Sender = messageStart.getParty();
            Party receiver = this.getActiveRepo().findReceiver(location);
            if (receiver != null) {
                int yLocation = new Double(messageStart.getStartloction().getY()).intValue();
                Message previous = sequenceRepo.getMessageView().findPreviousMessage(yLocation, diagram.getFirstMessage());
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

    /**
     * adds the given messages to all repos of this facade
     * @param messages the messages to add
     */
    public void addMessagesToRepos(List<Message> messages){
        getActiveRepo().getMessageView().addMessages(messages, diagram.getFirstMessage(), getActiveRepo().getPartyView(), getActiveRepo().getLabelView());
        this.getOtherRepo().getMessageView().addMessages(messages, diagram.getFirstMessage(), getOtherRepo().getPartyView(),getOtherRepo().getLabelView());
    }

    public boolean activeDiagramIsSequence() {
        return this.getActiveRepo() instanceof SequenceView;
    }

    public boolean activeDiagramIsCommunication() {
        return this.getActiveRepo() instanceof CommunicationView;
    }
}
