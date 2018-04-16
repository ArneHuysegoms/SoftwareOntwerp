package repo.diagram;

import repo.label.LabelRepo;
import repo.message.SequenceMessageRepo;
import repo.party.PartyRepo;

import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * subclass of DiagramRepo for the state/description of a sequencerepo
 */
public class SequenceRepo extends DiagramRepo implements Serializable {

    private static final int MINY = 50;
    private static final int MAXY = 100;

    /**
     * constructs a new empty sequencerepo
     */
    public SequenceRepo(){
        this(new LabelRepo(), new PartyRepo(), new SequenceMessageRepo());
    }

    /**
     * constructs a new sequencerepo of which the state is equal to the state of the provided repos
     *
     * @param labelRepo the labelrepo containing the state of the labels
     * @param partyRepo the partyrepo containing the state of the parties
     * @param messageRepo the messagerepo containing the state of the messages
     */
    public SequenceRepo(LabelRepo labelRepo, PartyRepo partyRepo, SequenceMessageRepo messageRepo){
        super(labelRepo, partyRepo, messageRepo);
    }

    /**
     * @param point2D the position of the UIEvent
     *
     * @return whether the location is valid location
     */
    @Override
    public boolean isValidPartyLocation(Point2D point2D) {
        return point2D.getY() >= MINY && point2D.getY() <= MAXY;
    }

    /**
     * retusn a valid postion for a new party based on the given location
     *
     * @param point2D the original position of the UIEvent
     *
     * @return Point2D the valid location based on the provided location
     */
    @Override
    public Point2D getValidPartyLocation(Point2D point2D) {
        return new Point2D.Double(point2D.getX(), MINY);
    }

    /**
     * determines whether the location belongs to the lifeline of the given party
     *
     * @param location the location of the ClickEvent
     * @param xCoordinateOfLifeline location of the parties lifeline
     * @return true if the location belongs to the lifeline of the given party, false otherwise
     */
    @Override
    public boolean isLifeLine(Point2D location, double xCoordinateOfLifeline) {
        return (location.getY() > MAXY) && (location.getX() >= xCoordinateOfLifeline - 20 && location.getX() <= xCoordinateOfLifeline + 20);
    }
}
