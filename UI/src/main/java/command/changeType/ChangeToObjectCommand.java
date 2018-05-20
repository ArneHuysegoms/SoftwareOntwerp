package command.changeType;

import action.Action;
import action.EmptyAction;
import action.UpdateLabelContainersAction;
import action.UpdatePartyTypeAction;
import command.Command;
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
    public Action performAction() {
        if(party instanceof Actor) {
            Party newParty = subwindow.getFacade().changePartyType(party);
            return new UpdatePartyTypeAction(party, newParty);
        }
        return new EmptyAction();
    }
}
