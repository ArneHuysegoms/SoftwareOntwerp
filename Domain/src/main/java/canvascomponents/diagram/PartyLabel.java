package canvascomponents.diagram;

import java.awt.geom.Point2D;

public class PartyLabel extends Label{

    public PartyLabel(){
        super();
    }

    public PartyLabel(String label, Point2D coordinate){
        super(label, coordinate);
    }

    public boolean isValidPartyLabel(String label){
        return label.contains(":.*[A-Z].*");
    }
}
