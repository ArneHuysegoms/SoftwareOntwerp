package action;

import diagram.party.Party;
import window.diagram.DiagramSubwindow;

/**
 * action that changes partytypes
 */
public class UpdatePartyTypeAction extends Action {

    private Party oldParty;
    private Party newParty;

    /**
     * creates a new updatePartyTypeAction to change oldParty to newParty, with switched types
     *
     * @param oldParty the old party
     * @param newParty the new party
     */
    public UpdatePartyTypeAction(Party oldParty, Party newParty) {
        this.oldParty = oldParty;
        this.newParty = newParty;
    }

    /**
     * @return the old party
     */
    public Party getOldParty() {
        return oldParty;
    }

    /**
     * @return the new party
     */
    public Party getNewParty() {
        return newParty;
    }

    /**
     * changes the partytype for the given parties in the given subwindow
     *
     * @param subwindow the subwindow to perform an action on
     */
    @Override
    public void performAction(DiagramSubwindow subwindow) {
        if (subwindow.getFacade().getActiveView().getPartyView().getAllParties().contains(oldParty)) {
            subwindow.getFacade().changePartyTypeInView(oldParty, newParty);
            if (subwindow.getSelected() == oldParty) {
                subwindow.setSelected(newParty);
            }
        }
    }
}
