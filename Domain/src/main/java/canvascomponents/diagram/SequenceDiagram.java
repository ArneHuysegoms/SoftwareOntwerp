package canvascomponents.diagram;

import canvascomponents.Clickable;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class SequenceDiagram extends Diagram {

    private static final int MINY = 50;
    private static final int MAXY = 100;

    public SequenceDiagram(){
        this(new HashMap<>(), new HashMap<>());
    }

    public SequenceDiagram(Map<Party, Point2D> partiesWithCoordinates, Map<Message, Point2D> messagesWithCoordinates){
        super(partiesWithCoordinates, messagesWithCoordinates);
    }

    public SequenceDiagram(Map<Party, Point2D> partiesWithCoordinates, Map<Message, Point2D> messagesWithCoordinates, Message firstMessage){
        this(partiesWithCoordinates, messagesWithCoordinates, firstMessage, null);
    }

    public SequenceDiagram(Map<Party, Point2D> partiesWithCoordinates, Map<Message, Point2D> messagesWithCoordinates, Message firstMessage, Clickable selectedElement){
        this(partiesWithCoordinates, messagesWithCoordinates, firstMessage, selectedElement, "");
    }

    public SequenceDiagram(Map<Party, Point2D> partiesWithCoordinates, Map<Message, Point2D> messagesWithCoordinates, Message firstMessage, Clickable selectedElement, String labelContainer){
        this(partiesWithCoordinates, messagesWithCoordinates, firstMessage, selectedElement, labelContainer, false, false, false);
    }

    public SequenceDiagram(Map<Party, Point2D> partiesWithCoordinates, Map<Message, Point2D> messagesWithCoordinates, Message firstMessage, Clickable selectedElement,
                   String labelContainer, boolean labelMode, boolean validLabel, boolean messageMode){
        super(partiesWithCoordinates, messagesWithCoordinates, firstMessage, selectedElement, labelContainer, labelMode, validLabel, messageMode);
    }

    @Override
    public boolean isValidPartyLocation(Point2D point2D) {
        return point2D.getY() >= MINY && point2D.getY() <= MAXY;
    }

    @Override
    public Point2D getValidLocation(Point2D point2D) {
        return new Point2D.Double(point2D.getX(), MINY);
    }
}
