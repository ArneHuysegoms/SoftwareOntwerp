package window.elements.radiobutton;

import command.Command;
import command.changeType.PartyCommand.ChangeToObjectCommand;
import command.changeType.PartyCommand.PartyCommand;
import diagram.party.Party;
import exception.UIException;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;

import java.awt.geom.Point2D;

public class ToObjectRadioButton extends PartyRadioButton {

    public static String DESCRIPTION = "Object";

    /**
     * constructs a new radiobutton with the given parametesr
     *
     * @param command     the command for this radiobutton
     * @param coordinate  the coordinate of this radiobutton
     * @param description the description for this radiobutton
     * @throws UIException throws an uiexception if the command is invalid
     */
    public ToObjectRadioButton(Command command, Point2D coordinate, String description) throws UIException {
        super(command, coordinate, description);
    }

    @Override
    public void update(DiagramSubwindow subwindow, Party party) {
        //((PartyCommand)getCommand()).setSubwindow(subwindow);
        //((PartyCommand)getCommand()).setParty(party);
        this.setCommand(new ChangeToObjectCommand(subwindow,party));
    }

    /**
     * add character from description
     */
    @Override
    public void addCharToDescription(char c){
        DESCRIPTION += c;
        setDescription(DESCRIPTION);
    }

    /**
     * delete character from description
     */
    @Override
    public void deleteCharFromDescription(){
        if(DESCRIPTION.length() > 0){
            DESCRIPTION = DESCRIPTION.substring(0,DESCRIPTION.length()-1);
            setDescription(DESCRIPTION);
        }
    }

    /**
     * clones object
     * @return clone
     */
    @Override
    public DialogboxElement clone() {
        try {
            return new ToObjectRadioButton(getCommand(), getCoordinate(), DESCRIPTION);
        } catch (UIException e) {
            e.printStackTrace();
        }
        return null;
    }
}
