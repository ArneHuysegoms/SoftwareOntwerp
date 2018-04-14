package repo.diagram;

import repo.label.LabelRepo;
import repo.message.CommunicationMessageRepo;
import repo.party.PartyRepo;

import java.awt.geom.Point2D;

public class CommunicationRepo extends DiagramRepo {

    public CommunicationRepo() {
        this(new LabelRepo(), new PartyRepo(), new CommunicationMessageRepo());
    }

    public CommunicationRepo(LabelRepo labelRepo, PartyRepo partyRepo, CommunicationMessageRepo messageRepo){
        super(labelRepo, partyRepo, messageRepo);
    }

    @Override
    public boolean isValidPartyLocation(Point2D point2D) {
        return true;
    }

    @Override
    public Point2D getValidPartyLocation(Point2D point2D) {
        return point2D;
    }

    @Override
    public boolean isLifeLine(Point2D location, double xCoordinateOfLifeline) {
        return false;
    }
}
