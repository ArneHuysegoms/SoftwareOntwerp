package command.changeType.PartyCommand;

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
public class ChangeToObjectCommand extends PartyCommand {

    /**
     * creates a command for the given subwindow and party
     *
     * @param subwindow the subwindow of the party
     * @param party     the party to change
     */
    public ChangeToObjectCommand(DiagramSubwindow subwindow, Party party) {
        super(subwindow,party);
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
