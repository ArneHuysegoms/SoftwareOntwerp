package canvascomponents.diagram;

import canvascomponents.Clickable;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommunicationsDiagram extends Diagram {

    public CommunicationsDiagram(){
        this(null, null);
    }

    public CommunicationsDiagram(List<Party> parties, Message firstMessage){
        this(parties, firstMessage, null);
    }

    public CommunicationsDiagram(List<Party> parties, Message firstMessage, Clickable selectedElement){
        this(parties, firstMessage, selectedElement, "");
    }

    public CommunicationsDiagram(List<Party> parties, Message firstMessage, Clickable selectedElement, String labelContainer){
        this(parties, firstMessage, selectedElement, labelContainer, false, false, false);
    }

    public CommunicationsDiagram(List<Party> parties, Message firstMessage, Clickable selectedElement,
                           String labelContainer, boolean labelMode, boolean validLabel, boolean messageMode){
        super(parties, firstMessage, selectedElement, labelContainer, labelMode, validLabel, messageMode);
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
