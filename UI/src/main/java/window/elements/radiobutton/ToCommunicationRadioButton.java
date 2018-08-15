package window.elements.radiobutton;

import command.Command;
import command.changeType.DiagramCommand.ChangeToCommunicationCommand;
import exception.UIException;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;

import java.awt.geom.Point2D;

public class ToCommunicationRadioButton extends DiagramRadioButton {

    public static String DESCRIPTION = "Communication";

    /**
     * constructs a new radiobutton with the given parametesr
     *
     * @param command     the command for this radiobutton
     * @param coordinate  the coordinate of this radiobutton
     * @param description the description for this radiobutton
     * @throws UIException throws an uiexception if the command is invalid
     */
    public ToCommunicationRadioButton(Command command, Point2D coordinate, String description) throws UIException {
        super(command, coordinate, description);
    }

    @Override
    public void update(DiagramSubwindow sub) {
        this.setCommand(new ChangeToCommunicationCommand(sub));

    }

    @Override
    public void addCharToDescription(char c){
        DESCRIPTION += c;
        setDescription(DESCRIPTION);
    }

    @Override
    public void deleteCharFromDescription(){
        if(DESCRIPTION.length() > 0){
            DESCRIPTION = DESCRIPTION.substring(0,DESCRIPTION.length()-1);
            setDescription(DESCRIPTION);
        }
    }

    @Override
    public DialogboxElement clone() {
        try {
            return new ToCommunicationRadioButton(getCommand(), getCoordinate(), DESCRIPTION);
        } catch (UIException e) {
            e.printStackTrace();
        }
        return null;
    }
}
