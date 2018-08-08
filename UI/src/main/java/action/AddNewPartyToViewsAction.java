package action;

import diagram.party.Party;
import window.diagram.DiagramSubwindow;

import java.awt.geom.Point2D;

/**
 * action class to add a new party with a location to the views
 */
public class AddNewPartyToViewsAction extends Action {

    private Party party;
    private Point2D location;

    /**
     * creates a new AddNewPartyToViewsAction for the given party and location
     *
     * @param party    the party to add
     * @param location the location of the party
     */
    public AddNewPartyToViewsAction(Party party, Point2D location) {
        this.party = party;
        this.location = location;
    }

    /**
     * add the party to the views of the given subwindow
     *
     * @param subwindow the subwindow to perform an action on
     */
    @Override
    public void performAction(DiagramSubwindow subwindow) {
        subwindow.getFacade().addPartyToView(party, location);
    }
}
