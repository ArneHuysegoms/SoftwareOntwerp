package diagram;

import diagram.message.Message;
import diagram.party.Party;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * Subclass of Diagram, details a communicationDiagram
 */
public class CommunicationsDiagram extends Diagram {

    /**
     * see superclass
     */
    public CommunicationsDiagram(){
        this(null, null);
    }

    /**
     * see superclass
     *
     * @param parties
     * @param firstMessage
     */
    public CommunicationsDiagram(List<Party> parties, Message firstMessage){
        this(parties, firstMessage, null);
    }

    /**
     * see superclass
     *
     * @param parties
     * @param firstMessage
     * @param selectedElement
     */
    public CommunicationsDiagram(List<Party> parties, Message firstMessage, Clickable selectedElement){
        this(parties, firstMessage, selectedElement, "");
    }

    /**
     * see superclass
     *
     * @param parties
     * @param firstMessage
     * @param selectedElement
     * @param labelContainer
     */
    public CommunicationsDiagram(List<Party> parties, Message firstMessage, Clickable selectedElement, String labelContainer){
        this(parties, firstMessage, selectedElement, labelContainer, false, true, false);
    }

    /**
     * see superclass
     *
     * @param parties
     * @param firstMessage
     * @param selectedElement
     * @param labelContainer
     * @param labelMode
     * @param validLabel
     * @param messageMode
     */
    public CommunicationsDiagram(List<Party> parties, Message firstMessage, Clickable selectedElement,
                           String labelContainer, boolean labelMode, boolean validLabel, boolean messageMode){
        super(parties, firstMessage, selectedElement, labelContainer, labelMode, validLabel, messageMode);
    }

    /**
     *
     * @param point2D the position of the UIEvent
     * @return true if the
     */
    @Override
    public boolean isValidPartyLocation(Point2D point2D) {
        return true;
    }

    /**
     *
     * @param point2D the original position of the UIEvent
     * @return a valid location for a new party based on the given location
     */
    @Override
    public Point2D getValidPartyLocation(Point2D point2D) {
        return point2D;
    }

    @Override
    public boolean isLifeLine(Point2D location, Party party) {
        return false;
    }
}
