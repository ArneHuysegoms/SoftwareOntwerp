package repo.diagram;

import diagram.DiagramElement;
import diagram.label.Label;
import diagram.party.Party;
import exceptions.DomainException;
import repo.label.LabelRepo;
import repo.message.MessageRepo;
import repo.party.PartyRepo;

import java.awt.geom.Point2D;
import java.util.Set;

public abstract class DiagramRepo {

    private LabelRepo labelRepo;
    private PartyRepo partyRepo;
    private MessageRepo messageRepo;

    public DiagramRepo(LabelRepo labelRepo, PartyRepo partyRepo, MessageRepo messageRepo){
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

    public MessageRepo getMessageRepo() {
        return messageRepo;
    }

    private void setMessageRepo(MessageRepo messageRepo) {
        if(messageRepo == null){
            throw new IllegalArgumentException("messageRepo may not be null");
        }
        this.messageRepo = messageRepo;
    }

    /**
     * Finds the element that has been clicked by the location of the MouseEvent
     *
     * @param clickedLocation the location of the MouseEvent
     * @return the element which has been clicked on
     */
    public DiagramElement getSelectedDiagramElement(Point2D clickedLocation) throws DomainException{
        Set<DiagramElement> clickedElements =  this.partyRepo.getClickedParties(clickedLocation);
        clickedElements.addAll(this.labelRepo.getClickedLabels(clickedLocation));
        if(clickedElements.size() == 1){
            return clickedElements.stream().findFirst().orElseThrow(DomainException::new);
        }
        else if(clickedElements.size() > 1){
            return findMostLikelyElement(clickedElements, clickedLocation);
        }
        else{
            for(Party party : partyRepo.getAllParties()){
                if(isLifeLine(clickedLocation, partyRepo.getXLocationOfLifeline(party))){
                    return new MessageStart(party, clickedLocation);
                }
            }
        }
        return null;
    }

    /**
     * if multiple elements overlap on the location of a mouseEvent, this function will determine which element was clicked
     *
     * @param clickedElements all overlapping elements
     *
     * @param clickedLocation the location of the MouseClick
     * @return the element that has been clicked on, based on distance to the MouseEvent
     */
    private DiagramElement findMostLikelyElement(Set<DiagramElement> clickedElements, Point2D clickedLocation){
        DiagramElement selected = null;
        double dist = Double.MAX_VALUE;
        for(DiagramElement element : clickedElements){
            if(element instanceof Label){
                Label l = (Label) element;
                if(labelRepo.getLocationOfLabel(l).distance(clickedLocation) < dist){
                    selected = l;
                    dist = labelRepo.getLocationOfLabel(l).distance(clickedLocation);
                }
            }
            else if(element instanceof Party){
                Party p = (Party) element;
                if(partyRepo.getLocationOfParty(p).distance(clickedLocation) < dist){
                    selected = p;
                    dist = partyRepo.getLocationOfParty(p).distance(clickedLocation);
                }
            }
        }
        return selected;
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

    /**
     * Finds the receiver of a message based on the endlocation of the messageDrag
     *
     * @param endlocation the location where the dragging for the message stopped
     * @return the party that corresponds to the location
     */
    public Party findReceiver(Point2D endlocation){
        for(Party party : this.getPartyRepo().getAllParties()){
            if(isLifeLine(endlocation, this.getPartyRepo().getXLocationOfLifeline(party))){
                return party;
            }
        }
        return null;
    }

    /**
     * Class to help adding messages, stocks the Startlocation and Sender of a new message
     */
    public class MessageStart extends DiagramElement {

        Party party;
        Point2D startloction;

        private MessageStart(Party party, Point2D startLocation) {
            this.party = party;
            this.startloction = startLocation;
        }

        public Party getParty() {
            return this.party;
        }

        public Point2D getStartloction() {
            return this.startloction;
        }
    }
}
