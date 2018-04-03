package repo;

import diagram.label.Label;
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

    public Party getMessageAtPosition(Point2D location) throws DomainException {
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

    private Set<Label> getClickedLabels(Point2D location){
        return this.labelRepo.getClickedLabels(location);
    }

}
