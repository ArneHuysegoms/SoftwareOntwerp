package canvascomponents.diagram;

import canvascomponents.Clickable;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SequenceDiagram extends Diagram {

    private static final int MINY = 50;
    private static final int MAXY = 100;

    public SequenceDiagram(){
        this(null, null);
    }

    public SequenceDiagram(List<Party> parties, Message firstMessage){
        this(parties, firstMessage, null);
    }

    public SequenceDiagram(List<Party> parties, Message firstMessage, Clickable selectedElement){
        this(parties, firstMessage, selectedElement, "");
    }

    public SequenceDiagram(List<Party> parties, Message firstMessage, Clickable selectedElement, String labelContainer){
        this(parties, firstMessage, selectedElement, labelContainer, false, false, false);
    }

    public SequenceDiagram(List<Party> parties, Message firstMessage, Clickable selectedElement,
                                 String labelContainer, boolean labelMode, boolean validLabel, boolean messageMode){
        super(parties, firstMessage, selectedElement, labelContainer, labelMode, validLabel, messageMode);
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
