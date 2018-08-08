package command.changeType.PartyCommand;

import action.Action;
import command.Command;
import diagram.party.Party;
import window.diagram.DiagramSubwindow;

public abstract class PartyCommand extends Command {
    protected DiagramSubwindow subwindow;
    protected Party party;

    /**
     * @return the diagramsubwindow to change in
     */
    public DiagramSubwindow getSubwindow() {
        return subwindow;
    }

    /**
     * sets the subwindow to the given subwindow
     *
     * @param subwindow the subwindow to set
     */
    public void setSubwindow(DiagramSubwindow subwindow) {
        this.subwindow = subwindow;
    }

    /**
     * @return the party to change
     */
    public Party getParty() {
        return party;
    }

    /**
     * sets the party to change
     *
     * @param party the party to st too
     */
    public void setParty(Party party) {
        this.party = party;
    }

}
