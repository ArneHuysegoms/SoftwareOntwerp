package canvascomponents.diagram;

import java.util.List;

public class SequenceDiagram extends Diagram {

    public SequenceDiagram(){
        super();
    }

    public SequenceDiagram(List<Party> parties){
        super(parties);
    }
}
