package repo.diagram;

import diagram.DiagramElement;
import diagram.message.Message;
import diagram.party.Party;
import exceptions.DomainException;
import repo.label.LabelRepo;
import repo.message.MessageRepo;
import repo.party.PartyRepo;

import java.awt.geom.Point2D;
import java.util.Set;

public class SequenceRepo extends DiagramRepo {

    private static final int MINY = 50;
    private static final int MAXY = 100;

    private MessageRepo messageRepo;

    public SequenceRepo(){
        this(new LabelRepo(), new PartyRepo(), new MessageRepo());
    }

    public SequenceRepo(LabelRepo labelRepo, PartyRepo partyRepo, MessageRepo messageRepo){
        super(labelRepo, partyRepo);
        this.setMessageRepo(messageRepo);
    }

    public MessageRepo getMessageRepo() {
        return messageRepo;
    }

    private void setMessageRepo(MessageRepo messageRepo) throws IllegalArgumentException{
        if(messageRepo == null){
            throw new IllegalArgumentException("messageRepo may not be null");
        }
        this.messageRepo = messageRepo;
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
        return (location.getY() > MAXY) &&
                (location.getX() >= xCoordinateOfLifeline - 20
                        && location.getX() <= xCoordinateOfLifeline + 20);
    }
}
