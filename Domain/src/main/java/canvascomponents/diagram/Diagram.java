package canvascomponents.diagram;

import canvascomponents.Clickable;
import exceptions.DomainException;

import java.awt.geom.Point2D;
import java.util.List;

public class Diagram{

    private List<Party> parties;

    public Diagram(){
        super();
    }

    public Diagram(List<Party> parties){
        super();
        this.setParties(parties);
    }

    public Diagram(List<Party> parties, Point2D point2D){
        this.setParties(parties);
    }

    public List<Party> getParties() {
        return parties;
    }

    private void setParties(List<Party> parties) {
        this.parties = parties;
    }

    public void addParty(Party party) throws DomainException{
        if((party == null) || (this.getParties().contains(party))){
            throw new DomainException("Can't add party to diagram");
        }
        this.parties.add(party);
    }

    public void removeParty(Party party){
        this.getParties().remove(party);
    }
}
