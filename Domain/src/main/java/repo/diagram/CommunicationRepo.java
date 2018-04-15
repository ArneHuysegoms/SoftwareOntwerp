package repo.diagram;

import repo.label.LabelRepo;
import repo.message.CommunicationMessageRepo;
import repo.party.PartyRepo;

import java.awt.geom.Point2D;

/**
 * subclass of DiagramRepo for the state/description of a communicationdiagram
 */
public class CommunicationRepo extends DiagramRepo {

    /**
     * constructs a new empty communicationrepo
     */
    public CommunicationRepo() {
        this(new LabelRepo(), new PartyRepo(), new CommunicationMessageRepo());
    }

    /**
     * constructs a new communicationrepo of which the state is equal to the state of the provided repos
     *
     * @param labelRepo the labelrepo containing the state of the labels
     * @param partyRepo the partyrepos containing the state of the parties
     * @param messageRepo the messagerepo containing the state of the messages
     */
    public CommunicationRepo(LabelRepo labelRepo, PartyRepo partyRepo, CommunicationMessageRepo messageRepo){
        super(labelRepo, partyRepo, messageRepo);
    }

    /**
     * checks if the given location is a valid party location for this diagram
     *
     * @param point2D the position of the UIEvent
     * @return true if the location is valid, false otherwise
     */
    @Override
    public boolean isValidPartyLocation(Point2D point2D) {
        return true;
    }

    /**
     * makes a valid partylocation based on the given location
     *
     * @param point2D the original position of the UIEvent
     * @return a valid partyLocation based on the given location
     */
    @Override
    public Point2D getValidPartyLocation(Point2D point2D) {
        return point2D;
    }

    /**
     * checks if the location is the location of a party lifeline based on the given x-coordinate of a lifeline
     *
     * @param location              the location of the ClickEvent
     * @param xCoordinateOfLifeline location of the parties lifeline
     * @return true if the location corresponds to the location of a lifelie, false otherwise
     */
    @Override
    public boolean isLifeLine(Point2D location, double xCoordinateOfLifeline) {
        return false;
    }
}
