package canvascomponents.diagram;

import java.awt.geom.Point2D;
import java.util.List;

public class CommunicationsDiagram extends Diagram {

    public CommunicationsDiagram(){
        super();
    }

    public CommunicationsDiagram(List<Party> parties){
        super(parties);
    }

    public CommunicationsDiagram(List<Party> parties, Point2D point2D){
        super(parties, point2D);
    }
}
