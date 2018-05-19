package action;

import diagram.party.Party;
import window.diagram.DiagramSubwindow;

import java.awt.geom.Point2D;

public class AddNewPartyToReposAction extends Action{

    private Party party;
    private Point2D location;

    public AddNewPartyToReposAction(Party party, Point2D location){
        this.party = party;
        this.location = location;
    }

    @Override
    public void performAction(DiagramSubwindow subwindow) {
        subwindow.getFacade().addPartyToRepo(party, location);
    }
}
