package command.changeType;

import action.Action;
import action.EmptyAction;
import action.UpdatePartyTypeAction;
import command.Command;
import diagram.party.Actor;
import diagram.party.Party;
import window.diagram.DiagramSubwindow;

/**
 * commmand for changing an actor to an object
 */
public class ChangeToObjectCommand extends Command {

    private DiagramSubwindow subwindow;
    private Party party;

    /**
     * creates a command for the given subwindow and party
     *
     * @param subwindow the subwindow of the party
     * @param party     the party to change
     */
    public ChangeToObjectCommand(DiagramSubwindow subwindow, Party party) {
        this.setSubwindow(subwindow);
        this.setParty(party);
    }

    /**
     * @return the subwindow the change happens in
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
        if (party instanceof Actor) {
            Party newParty = subwindow.getFacade().changePartyType(party);
            return new UpdatePartyTypeAction(party, newParty);
        }
        return new EmptyAction();
    }
}
