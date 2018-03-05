package canvascomponents.diagram;

import canvascomponents.Clickable;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * subclass of diagram, depicting a sequence diagam
 */
public class SequenceDiagram extends Diagram {

    private static final int MINY = 50;
    private static final int MAXY = 100;

    /**
     * see superclass
     */
    public SequenceDiagram(){
        this(null, null);
    }

    /**
     * see superclass
     *
     * @param parties
     * @param firstMessage
     */
    public SequenceDiagram(List<Party> parties, Message firstMessage){
        this(parties, firstMessage, null);
    }

    /**
     * see superclass
     *
     * @param parties
     * @param firstMessage
     * @param selectedElement
     */
    public SequenceDiagram(List<Party> parties, Message firstMessage, Clickable selectedElement){
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
    public SequenceDiagram(List<Party> parties, Message firstMessage, Clickable selectedElement, String labelContainer){
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
    public SequenceDiagram(List<Party> parties, Message firstMessage, Clickable selectedElement,
                                 String labelContainer, boolean labelMode, boolean validLabel, boolean messageMode){
        super(parties, firstMessage, selectedElement, labelContainer, labelMode, validLabel, messageMode);
    }

    /**
     *
     * @param point2D the position of the UIEvent
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
     * @return
     */
    @Override
    public Point2D getValidPartyLocation(Point2D point2D) {
        return new Point2D.Double(point2D.getX(), MINY);
    }

    /**
     * determines whether the location belongs to the lifeline of the given party
     *
     * @param location the location of the ClickEvent
     * @param party a party of the diagram
     * @return true if the location belongs to the lifeline of the given party, false otherwise
     */
    @Override
    boolean isLifeLine(Point2D location, Party party) {
        return (location.getY() > MAXY) && (location.getX() >= party.getCoordinate().getX() - 20 && location.getX() <= party.getCoordinate().getX() + 20);
    }


}
