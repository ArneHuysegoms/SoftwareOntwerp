package repo.diagram;

import diagram.label.Label;
import diagram.party.Party;
import exceptions.DomainException;
import repo.label.LabelRepo;
import repo.party.PartyRepo;

import java.awt.geom.Point2D;
import java.util.Set;

public abstract class DiagramRepo {

    private LabelRepo labelRepo;
    private PartyRepo partyRepo;

    public DiagramRepo(){
        this(new LabelRepo(), new PartyRepo());
    }

    public DiagramRepo(LabelRepo labelRepo, PartyRepo partyRepo){
        this.setLabelRepo(labelRepo);
        this.setPartyRepo(partyRepo);
    }

    public LabelRepo getLabelRepo() {
        return labelRepo;
    }

    private void setLabelRepo(LabelRepo labelRepo) throws IllegalArgumentException{
        if(labelRepo == null){
            throw new IllegalArgumentException("labelRepo may not be null");
        }
        this.labelRepo = labelRepo;
    }

    public PartyRepo getPartyRepo() {
        return partyRepo;
    }

    private void setPartyRepo(PartyRepo partyRepo) throws IllegalArgumentException{
        if(partyRepo == null){
            throw new IllegalArgumentException("partyRepo may not be null");
        }
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

    /**
     * Checks whether the location of the UIEvent is a valid location to trigger a new Party instantiation
     *
     * Has to be implemented in subclass
     *
     * @param point2D the position of the UIEvent
     * @return true if the location will trigger the addition of a new party to the diagram, false otherwise
     */
    public abstract boolean isValidPartyLocation(Point2D point2D);

    /**
     * Returns a valid location for the position of a party based on the provided location of the UIEvent
     *
     * Has to implemented in subclasses
     *
     * @param point2D the original position of the UIEvent
     * @return Point2D, the appropriate location for a new Party
     */
    public abstract Point2D getValidPartyLocation(Point2D point2D);

    /**
     * Determines if the location belongs to the lifeline of the given Party
     *
     * @param location the location of the ClickEvent
     * @param xCoordinateOfLifeline location of the parties lifeline
     * @return true if the location belongs to the lifeline of the party, false otherwise
     */
    public abstract boolean isLifeLine(Point2D location, double xCoordinateOfLifeline);
}
