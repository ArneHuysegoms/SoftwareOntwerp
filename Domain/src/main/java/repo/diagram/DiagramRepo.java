package repo.diagram;

import diagram.Diagram;
import diagram.DiagramElement;
import diagram.label.Label;
import diagram.message.Message;
import diagram.party.Party;
import exceptions.DomainException;
import repo.label.LabelRepo;
import repo.message.CommunicationMessageRepo;
import repo.message.MessageRepo;
import repo.message.SequenceMessageRepo;
import repo.party.PartyRepo;

import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * abstract superclass for all types of diagramrepos, contains all logic for maintaining the state of the diagram
 */
public abstract class DiagramRepo implements Serializable {

    private LabelRepo labelRepo;
    private PartyRepo partyRepo;
    private MessageRepo messageRepo;

    /**
     * constructs a new DiagramRepo
     * @param labelRepo the labelrepo for this diagramrepo
     * @param partyRepo the partyrepo for this diagramrepo
     * @param messageRepo the messagerepo for this diagramrepo
     */
    public DiagramRepo(LabelRepo labelRepo, PartyRepo partyRepo, MessageRepo messageRepo) {
        this.setLabelRepo(labelRepo);
        this.setPartyRepo(partyRepo);
        this.setMessageRepo(messageRepo);
    }

    /**
     * @return the labelrepo of this diagramrepo
     */
    public LabelRepo getLabelRepo() {
        return labelRepo;
    }

    /**
     * sets the labelrepo for this diagramrepo to the given labelrepo
     * @param labelRepo the labelrepo for this diagramrepo
     * @throws IllegalArgumentException if the provided labelrepo is null
     */
    private void setLabelRepo(LabelRepo labelRepo) throws IllegalArgumentException {
        if (labelRepo == null) {
            throw new IllegalArgumentException("labelRepo may not be null");
        }
        this.labelRepo = labelRepo;
    }

    /**
     * @return the partyrepo of this diagramrepo
     */
    public PartyRepo getPartyRepo() {
        return partyRepo;
    }

    /**
     * sets the partyrepo for this diagramrepo to the given diagramrepo
     * @param partyRepo the partyrepo for this diagramrepo
     * @throws IllegalArgumentException if the provided partyrepo is null
     */
    private void setPartyRepo(PartyRepo partyRepo) throws IllegalArgumentException {
        if (partyRepo == null) {
            throw new IllegalArgumentException("partyRepo may not be null");
        }
        this.partyRepo = partyRepo;
    }

    /**
     * @return the messagerepo of this diagram
     */
    public MessageRepo getMessageRepo() {
        return messageRepo;
    }

    /**
     * sets the message of this diagramrepo to the provided messagerepo
     *
     * @param messageRepo the messagerepo for this diagramrepo
     * @throws IllegalArgumentException if the provided messagerepo is null
     */
    private void setMessageRepo(MessageRepo messageRepo) throws IllegalArgumentException{
        if (messageRepo == null) {
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
    public DiagramElement getSelectedDiagramElement(Point2D clickedLocation){
        Set<DiagramElement> clickedElements = this.getPartyRepo().getClickedParties(clickedLocation);
        clickedElements.addAll(this.getLabelRepo().getClickedLabels(clickedLocation));
        if (clickedElements.size() == 1) {
            return clickedElements.stream().findFirst().orElse(null);
        } else if (clickedElements.size() > 1) {
            return findMostLikelyElement(clickedElements, clickedLocation);
        } else {
            for (Party party : getPartyRepo().getAllParties()) {
                if (isLifeLine(clickedLocation, getPartyRepo().getXLocationOfLifeline(party))) {
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
     * @param clickedLocation the location of the MouseClick
     * @return the element that has been clicked on, based on distance to the MouseEvent
     */
    private DiagramElement findMostLikelyElement(Set<DiagramElement> clickedElements, Point2D clickedLocation) {
        DiagramElement selected = null;
        double dist = Double.MAX_VALUE;
        for (DiagramElement element : clickedElements) {
            if (element instanceof Label) {
                Label l = (Label) element;
                if (getLabelRepo().getLocationOfLabel(l).distance(clickedLocation) < dist) {
                    selected = l;
                    dist = getLabelRepo().getLocationOfLabel(l).distance(clickedLocation);
                }
            } else if (element instanceof Party) {
                Party p = (Party) element;
                if (getPartyRepo().getLocationOfParty(p).distance(clickedLocation) < dist) {
                    selected = p;
                    dist = getPartyRepo().getLocationOfParty(p).distance(clickedLocation);
                }
            }
        }
        return selected;
    }

    /**
     * adds the given party to the repos, with cascading effet
     * @param newParty the new Party to add
     * @param location the location of the new party
     */
    public void addNewPartyToRepos(Party newParty, Point2D location) {
        Point2D correctPartyLocation = getValidPartyLocation(location);
        if (newParty != null) {
            getPartyRepo().addPartyWithLocation(newParty, correctPartyLocation);
            getLabelRepo().addLabelWithLocation(newParty.getLabel(),
                    new Point2D.Double(correctPartyLocation.getX() + 10,
                            correctPartyLocation.getY() + 20));
        }
    }

    /**
     * changes the type of the oldParty to that of the newParty in all repos, with cascading effet
     *
     * @param oldParty the old party
     * @param newParty the new party
     * @throws DomainException if the old party can`t be found or removed
     */
    public void changePartyTypeInRepos(Party oldParty, Party newParty) throws DomainException {
        Point2D location = getPartyRepo().getLocationOfParty(oldParty);
        Point2D labelLocation = getLabelRepo().getLocationOfLabel(oldParty.getLabel());

        getPartyRepo().removeParty(oldParty);
        getLabelRepo().removeLabelByPosition(labelLocation);

        getPartyRepo().addPartyWithLocation(newParty, location);
        Point2D labelPosition = getPartyRepo().getCorrectLabelPosition(newParty);
        getLabelRepo().addLabelWithLocation(newParty.getLabel(), labelPosition);
    }

    /**
     * deletes the given message from the repos with cascading effect and restores the location of the other messages
     * based on the provided firstmessage
     *
     * @param message the message to remove from the repos
     * @param firstMessage the first message of the diagram
     */
    public void deleteMessageInRepos(Message message, Message firstMessage) {
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
     * @param location              the location of the ClickEvent
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
    public Party findReceiver(Point2D endlocation) {
        for (Party party : this.getPartyRepo().getAllParties()) {
            if (isLifeLine(endlocation, this.getPartyRepo().getXLocationOfLifeline(party))) {
                return party;
            }
        }
        return null;
    }

    /**
     * returns a deep copy of the provided original diagramRepo
     * @param orig the original diagram repo
     * @return the deep copy of the provided original
     */
    public static DiagramRepo copy(DiagramRepo orig) {
        PartyRepo partyRepo = new PartyRepo(new HashMap<>(orig.getPartyRepo().getMap()));
        LabelRepo labelRepo = new LabelRepo(new HashMap<>(orig.getLabelRepo().getMap()));
        MessageRepo messageRepo;
        if(orig.getMessageRepo() instanceof SequenceMessageRepo){
            SequenceMessageRepo smrepo = (SequenceMessageRepo) orig.getMessageRepo();
            SequenceMessageRepo newSmr = new SequenceMessageRepo(new HashMap<>(smrepo.getMap()));
            return new SequenceRepo(labelRepo, partyRepo, newSmr);
        }
        else{
            CommunicationMessageRepo comrepo = (CommunicationMessageRepo) orig.getMessageRepo();
            CommunicationMessageRepo newCom = new CommunicationMessageRepo(new ArrayList<>(comrepo.getMap()));
            return new CommunicationRepo(labelRepo, partyRepo, newCom);
        }
        /*Object obj = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(orig);
            out.flush();
            out.close();
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            obj = in.readObject();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return (DiagramRepo) obj;*/
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
