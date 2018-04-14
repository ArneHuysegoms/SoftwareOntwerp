package repo.diagram;

import diagram.DiagramElement;
import diagram.label.Label;
import diagram.message.Message;
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
        this.setMessageRepo(messageRepo);
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
        Set<DiagramElement> clickedElements =  this.getPartyRepo().getClickedParties(clickedLocation);
        clickedElements.addAll(this.getLabelRepo().getClickedLabels(clickedLocation));
        if(clickedElements.size() == 1){
            return clickedElements.stream().findFirst().orElseThrow(DomainException::new);
        }
        else if(clickedElements.size() > 1){
            return findMostLikelyElement(clickedElements, clickedLocation);
        }
        else{
            for(Party party : getPartyRepo().getAllParties()){
                if(isLifeLine(clickedLocation, getPartyRepo().getXLocationOfLifeline(party))){
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
                if(getLabelRepo().getLocationOfLabel(l).distance(clickedLocation) < dist){
                    selected = l;
                    dist = getLabelRepo().getLocationOfLabel(l).distance(clickedLocation);
                }
            }
            else if(element instanceof Party){
                Party p = (Party) element;
                if(getPartyRepo().getLocationOfParty(p).distance(clickedLocation) < dist){
                    selected = p;
                    dist = getPartyRepo().getLocationOfParty(p).distance(clickedLocation);
                }
            }
        }
        return selected;
    }

    public void addNewPartyToRepos(Party newParty, Point2D location){
        if(isValidPartyLocation(location)) {
            Point2D correctPartyLocation = getValidPartyLocation(location);
            if(newParty != null){
                getPartyRepo().addPartyWithLocation(newParty, location);
                getLabelRepo().addLabelWithLocation(newParty.getLabel(),
                        new Point2D.Double(correctPartyLocation.getX() + 10,
                                correctPartyLocation.getY() + 20));
            }
        }
    }

    public void changePartyTypeInRepos(Party oldParty, Party newParty) throws DomainException{
        Point2D location = getPartyRepo().getLocationOfParty(oldParty);
        Point2D labelLocation = getLabelRepo().getLocationOfLabel(oldParty.getLabel());

        getPartyRepo().removeParty(oldParty);
        getLabelRepo().removeLabelByPosition(labelLocation);

        getPartyRepo().addPartyWithLocation(newParty, location);
        Point2D labelPosition = getPartyRepo().getCorrectLabelPosition(newParty);
        getLabelRepo().addLabelWithLocation(newParty.getLabel(), labelPosition);
    }

    public void deleteMessageInRepos(Message message, Message firstMessage){
        getMessageRepo().removeMessage(message);
        getLabelRepo().removeLabel(message.getLabel());
        getMessageRepo().resetMessagePositions(firstMessage, getPartyRepo(), getLabelRepo());
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
