package repo;

import diagram.label.Label;
import diagram.message.Message;
import diagram.party.Party;
import exceptions.DomainException;

import java.awt.geom.Point2D;
import java.util.Set;

public abstract class DiagramRepo {

    private LabelRepo labelRepo;
    private MessageRepo messageRepo;
    private PartyRepo partyRepo;

    public DiagramRepo(){
        this(new LabelRepo(), new MessageRepo(), new PartyRepo());
    }

    public DiagramRepo(LabelRepo labelRepo, MessageRepo messageRepo, PartyRepo partyRepo){
        this.labelRepo = labelRepo;
        this.messageRepo = messageRepo;
        this.partyRepo = partyRepo;
    }

    public Party getPartyAtPosition(Point2D location) throws DomainException {
        return this.partyRepo.getPartyAtPosition(location);
    }

    public Point2D getLocationOfParty(Party party){
        return this.partyRepo.getLocationOfParty(party);
    }

    public void addPartyWithLocation(Party party, Point2D location){
        this.partyRepo.addPartyWithLocation(party, location);
    }

    public void removeParty(Party party){
        this.partyRepo.removeParty(party);
    }

    public void removePartyByPosition(Point2D location) throws DomainException {
        this.partyRepo.removePartyByPosition(location);
    }

    public void updatePartyPosition(Point2D newPosition, Party party){
        this.partyRepo.updatePartyPosition(newPosition, party);
    }

    private Set<Party> getClickedParties(Point2D clickedLocation){
        return this.partyRepo.getClickedParties(clickedLocation);
    }

    public Label getLabelAtPosition(Point2D location) throws DomainException {
        return this.labelRepo.getLabelAtPosition(location);
    }

    public Point2D getLocationOfLabel(Label label){
        return this.labelRepo.getLocationOfLabel(label);
    }

    public void addLabelwithLocation(Label label, Point2D location){
        this.labelRepo.addLabelWithLocation(label, location);
    }

    public void removeLabel(Label label){
        this.labelRepo.removeLabel(label);
    }

    public void removeLabelByPosition(Point2D location) throws DomainException{
        this.labelRepo.removeLabelByPosition(location);
    }

    public void updateLabelPosition(Point2D newPosition, Label label){
        this.labelRepo.updateLabelPosition(newPosition, label);
    }

    private Set<Label> getClickedLabels(Point2D location){
        return this.labelRepo.getClickedLabels(location);
    }

    public Message getMessageAtPosition(int yLocation) throws DomainException {
        return this.messageRepo.getMessageAtPosition(yLocation);
    }

    public int getLocationOfMessage(Message message){
        return this.messageRepo.getLocationOfMessage(message);
    }

    public void addMessageWithLocation(Message message, int yLocation){
        this.messageRepo.addMessageWithLocation(message, yLocation);
    }

    public void removeMessage(Message message){
        this.messageRepo.removeMessage(message);
    }

    public void removeMessageByPosition(int yLocation) throws DomainException{
        this.messageRepo.removeMessageByPosition(yLocation);
    }

    private Set<Message> getClickedMessages(Point2D clickedLocation){
        return this.messageRepo.getClickedMessages(clickedLocation, this.partyRepo);
    }

    public void updateMessageLocation(int yLocation, Message message){
        this.messageRepo.updateMessageLocation(yLocation, message);
    }
}
