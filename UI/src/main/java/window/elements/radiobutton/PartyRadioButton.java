package window.elements.radiobutton;

import command.Command;
import command.changeType.PartyCommand.PartyCommand;
import diagram.party.Party;
import exception.UIException;
import window.Subwindow;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;

import java.awt.geom.Point2D;

public class PartyRadioButton extends RadioButton{

    /**
     * constructs a new radiobutton with the given parametesr
     *
     * @param command     the command for this radiobutton
     * @param coordinate  the coordinate of this radiobutton
     * @param description the description for this radiobutton
     * @throws UIException throws an uiexception if the command is invalid
     */
    public PartyRadioButton(Command command, Point2D coordinate, String description) throws UIException {
        super(command, coordinate, description);
    }

    @Override
    public void update(DiagramSubwindow subwindow, Party party) {
        ((PartyCommand)getCommand()).setSubwindow(subwindow);
        ((PartyCommand)getCommand()).setParty(party);
    }

    @Override
    public DialogboxElement clone() {
        try {
            return new PartyRadioButton(getCommand(), getCoordinate(), getDescription());
        } catch (UIException e) {
            e.printStackTrace();
        }
        return null;
    }
}
