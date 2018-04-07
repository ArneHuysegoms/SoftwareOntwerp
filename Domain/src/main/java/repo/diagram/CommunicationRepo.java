package repo.diagram;

import repo.label.LabelRepo;
import repo.party.PartyRepo;

import java.awt.geom.Point2D;

public class CommunicationRepo extends DiagramRepo {

    public CommunicationRepo(){
        super();
    }

    public CommunicationRepo(LabelRepo labelRepo, PartyRepo partyRepo){
        super(labelRepo, partyRepo);
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
