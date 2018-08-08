package window.elements.radiobutton;

import command.Command;
import command.changeType.DiagramCommand.DiagramCommand;
import diagram.party.Party;
import exception.UIException;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;

import java.awt.geom.Point2D;

public class DiagramRadioButton extends RadioButton{
    /**
     * constructs a new radiobutton with the given parametesr
     *
     * @param command     the command for this radiobutton
     * @param coordinate  the coordinate of this radiobutton
     * @param description the description for this radiobutton
     * @throws UIException throws an uiexception if the command is invalid
     */
    public DiagramRadioButton(Command command, Point2D coordinate, String description) throws UIException {
        super(command, coordinate, description);
    }

    @Override
    public void update(DiagramSubwindow sub) {
        ((DiagramCommand)getCommand()).setDiagramSubwindow(sub);

    }

    @Override
    public DialogboxElement clone() {
        try {
            return new DiagramRadioButton(getCommand(), getCoordinate(), getDescription());
        } catch (UIException e) {
            e.printStackTrace();
        }
        return null;
    }
}
