package command;

import diagram.party.Actor;
import diagram.party.Party;
import window.diagram.DiagramSubwindow;

public class ChangeToObjectCommand extends Command {

    private DiagramSubwindow subwindow;
    private Party party;

    public ChangeToObjectCommand(DiagramSubwindow subwindow, Party party){
        this.setSubwindow(subwindow);
        this.setParty(party);
    }

    public DiagramSubwindow getSubwindow() {
        return subwindow;
    }

    public void setSubwindow(DiagramSubwindow subwindow) {
        this.subwindow = subwindow;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    @Override
    public void performAction() {
        if(party instanceof Actor) {
            Party newParty = subwindow.getFacade().changePartyType(party);
            //TODO change at other positions
        }
    }
}
