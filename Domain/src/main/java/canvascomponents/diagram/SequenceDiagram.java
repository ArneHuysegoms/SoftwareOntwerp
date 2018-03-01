package canvascomponents.diagram;

import java.awt.geom.Point2D;
import java.util.List;

public class SequenceDiagram extends Diagram {

    public SequenceDiagram(){
        super();
    }

    public SequenceDiagram(List<Party> parties){
        super(parties);
    }

    public SequenceDiagram(List<Party> parties, Point2D point2D){
        super(parties, point2D);
    }
}
