package canvascomponents.diagram;

import canvascomponents.Clickable;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class CommunicationsDiagram extends Diagram {

    public CommunicationsDiagram(){
        this(new HashMap<>(), new HashMap<>());
    }

    public CommunicationsDiagram(Map<Party, Point2D> partiesWithCoordinates, Map<Message, Point2D> messagesWithCoordinates){
        super(partiesWithCoordinates, messagesWithCoordinates);
    }

    public CommunicationsDiagram(Map<Party, Point2D> partiesWithCoordinates, Map<Message, Point2D> messagesWithCoordinates, Message firstMessage){
        this(partiesWithCoordinates, messagesWithCoordinates, firstMessage, null);
    }

    public CommunicationsDiagram(Map<Party, Point2D> partiesWithCoordinates, Map<Message, Point2D> messagesWithCoordinates, Message firstMessage, Clickable selectedElement){
        this(partiesWithCoordinates, messagesWithCoordinates, firstMessage, selectedElement, "");
    }

    public CommunicationsDiagram(Map<Party, Point2D> partiesWithCoordinates, Map<Message, Point2D> messagesWithCoordinates, Message firstMessage, Clickable selectedElement, String labelContainer){
        this(partiesWithCoordinates, messagesWithCoordinates, firstMessage, selectedElement, labelContainer, false, false, false);
    }

    public CommunicationsDiagram(Map<Party, Point2D> partiesWithCoordinates, Map<Message, Point2D> messagesWithCoordinates, Message firstMessage, Clickable selectedElement,
                           String labelContainer, boolean labelMode, boolean validLabel, boolean messageMode){
        super(partiesWithCoordinates, messagesWithCoordinates, firstMessage, selectedElement, labelContainer, labelMode, validLabel, messageMode);
    }

    @Override
    public boolean isValidPartyLocation(Point2D point2D) {
        return true;
    }

    @Override
    public Point2D getValidLocation(Point2D point2D) {
        return point2D;
    }
}
