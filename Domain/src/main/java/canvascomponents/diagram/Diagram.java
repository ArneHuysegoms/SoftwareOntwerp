package canvascomponents.diagram;

import canvascomponents.CanvasComponent;
import excpetions.DomainException;

import java.util.List;

public class Diagram extends CanvasComponent{

    private List<Party> parties;

    public Diagram(){

    }

    public Diagram(List<Party> parties){
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
