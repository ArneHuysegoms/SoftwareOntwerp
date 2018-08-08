package view.diagram;

import view.label.LabelView;
import view.message.CommunicationMessageView;
import view.party.PartyView;

import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * subclass of DiagramView for the state/description of a communicationdiagram
 */
public class CommunicationView extends DiagramView implements Serializable {

    /**
     * constructs a new empty communicationview
     */
    public CommunicationView() {
        this(new LabelView(), new PartyView(), new CommunicationMessageView());
    }

    /**
     * constructs a new communicationview of which the state is equal to the state of the provided views
     *
     * @param labelView the labelview containing the state of the labels
     * @param partyView the partyviews containing the state of the parties
     * @param messageRepo the messageview containing the state of the messages
     */
    public CommunicationView(LabelView labelView, PartyView partyView, CommunicationMessageView messageRepo){
        super(labelView, partyView, messageRepo);
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
