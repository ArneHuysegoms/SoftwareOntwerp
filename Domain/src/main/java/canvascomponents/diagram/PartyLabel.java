package canvascomponents.diagram;

import exceptions.DomainException;

import java.awt.geom.Point2D;

public class PartyLabel extends Label{
    private String label;

    public PartyLabel(){
        super();
    }

    public PartyLabel(String label, Point2D coordinate) throws DomainException {
        super(coordinate);
        if (!isValidPartyLabel(label)) {
            throw new DomainException("a party label consists of instanceName:classname");
        }
        this.setLabel(label);
    }
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isValidPartyLabel(String label){
        return label.contains(":.*[A-Z].*");
    }
}
