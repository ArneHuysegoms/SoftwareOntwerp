package window.elements.radiobutton;

import command.Command;
import command.changeType.PartyCommand.ChangeToActorCommand;
import command.changeType.PartyCommand.ChangeToObjectCommand;
import command.changeType.PartyCommand.PartyCommand;
import diagram.party.Party;
import exception.UIException;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;

import java.awt.geom.Point2D;

public class ToActorRadioButton extends PartyRadioButton {

    public static String DESCRIPTION = "Actor";

    /**
     * constructs a new radiobutton with the given parametesr
     *
     * @param command     the command for this radiobutton
     * @param coordinate  the coordinate of this radiobutton
     * @param description the description for this radiobutton
     * @throws UIException throws an uiexception if the command is invalid
     */
    public ToActorRadioButton(Command command, Point2D coordinate, String description) throws UIException {
        super(command, coordinate, DESCRIPTION);
    }
    /**
     * get static description
     * @return description
     */
    @Override
    public String getStaticDescription(){
        return DESCRIPTION;
    }
    /**
     * set static description
     */
    @Override
    public void setStaticDescription(String s){
        DESCRIPTION = s;

    }

    /**
     * update parties
     * @param subwindow
     * @param party
     */
    @Override
    public void update(DiagramSubwindow subwindow, Party party) {
        this.setCommand(new ChangeToActorCommand(subwindow,party));
    }

    /**
     * clones object
     * @return clone
     */
    @Override
    public DialogboxElement clone() {
        try {
            return new ToActorRadioButton(getCommand(), getCoordinate(), DESCRIPTION);
        } catch (UIException e) {
            e.printStackTrace();
        }
        return null;
    }
}
