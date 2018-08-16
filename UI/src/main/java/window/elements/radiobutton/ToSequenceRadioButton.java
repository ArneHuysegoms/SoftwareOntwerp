package window.elements.radiobutton;

import command.Command;
import command.changeType.DiagramCommand.ChangeToCommunicationCommand;
import command.changeType.DiagramCommand.ChangeToSequenceCommand;
import exception.UIException;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;

import java.awt.geom.Point2D;

public class ToSequenceRadioButton extends DiagramRadioButton{

    public static String DESCRIPTION = "Sequence";

    /**
     * constructs a new radiobutton with the given parametesr
     *
     * @param command     the command for this radiobutton
     * @param coordinate  the coordinate of this radiobutton
     * @param description the description for this radiobutton
     * @throws UIException throws an uiexception if the command is invalid
     */
    public ToSequenceRadioButton(Command command, Point2D coordinate, String description) throws UIException {
        super(command, coordinate, description);
    }

    /**
     * update command
     * @param sub
     */
    @Override
    public void update(DiagramSubwindow sub) {
        this.setCommand(new ChangeToSequenceCommand(sub));

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
     * clones object
     * @return clone
     */
    @Override
    public DialogboxElement clone() {
        try {
            return new ToSequenceRadioButton(getCommand(), getCoordinate(), DESCRIPTION);
        } catch (UIException e) {
            e.printStackTrace();
        }
        return null;
    }
}
