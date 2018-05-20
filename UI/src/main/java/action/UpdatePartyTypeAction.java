package action;

import diagram.party.Party;
import window.diagram.DiagramSubwindow;

public class UpdatePartyTypeAction extends Action{

    private Party oldParty;
    private Party newParty;

    public UpdatePartyTypeAction(Party oldParty, Party newParty){
        this.oldParty = oldParty;
        this.newParty = newParty;
    }

    public Party getOldParty() {
        return oldParty;
    }

    public Party getNewParty() {
        return newParty;
    }

    @Override
    public void performAction(DiagramSubwindow subwindow) {
        subwindow.getFacade().changePartyTypeInRepo(oldParty, newParty);
        subwindow.setSelected(newParty);

    }
}
