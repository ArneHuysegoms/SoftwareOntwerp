package command.changeType;

import action.Action;
import action.EmptyAction;
import action.UpdatePartyTypeAction;
import command.Command;
import diagram.party.Object;
import diagram.party.Party;
import window.diagram.DiagramSubwindow;

/**
 * command for changing parties to actors
 */
public class ChangeToActorCommand extends Command {

    private DiagramSubwindow subwindow;
    private Party party;

    /**
     * creates a new changeToActorCommand for switching the given party in the given subwindow
     *
     * @param subwindow the subwindow to change in
     * @param party     the party to change
     */
    public ChangeToActorCommand(DiagramSubwindow subwindow, Party party) {
        this.setSubwindow(subwindow);
        this.setParty(party);
    }

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

    /**
     * performs the action of this command
     *
     * @return an action detailing the change by handling the action
     */
    @Override
    public Action performAction() {
        if (party instanceof Object) {
            Party newParty = subwindow.getFacade().changePartyType(party);
            return new UpdatePartyTypeAction(party, newParty);
        }
        return new EmptyAction();
    }
}
