package window.elements.button;

import command.Command;
import command.InvocationCommand.MoveDownCommand;
import command.InvocationCommand.MoveUpCommand;
import diagram.label.InvocationMessageLabel;
import exception.UIException;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;
import window.elements.ListBox;
import window.elements.textbox.ArgumentTextBox;

import java.awt.geom.Point2D;

public class MoveDownButton extends DialogBoxButton {
    /**
     * constructs a new DialogBoxButton with the given parametesr
     *
     * @param command     the command for this DialogBoxButton
     * @param coordinate  the coordinate of this DialogBoxButton
     * @param description the description for this DialogBoxButton
     * @throws UIException throws an uiexception if the command is invalid
     */
    public MoveDownButton(Command command, Point2D coordinate, String description) throws UIException {
        super(command, coordinate, description);
    }

    /**
     * clones object
     * @return new object
     */
    @Override
    public DialogboxElement clone() {
        try {
            return new MoveDownButton(getCommand(), getCoordinate(), "Move down");
        } catch (UIException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * get static description
     * @return description
     */
    @Override
    public String getDescription(){
        return null;
    }
    /**
     * set static description
     * @param s
     */
    @Override
    public void setStaticDescription(String s){

    }

    /**
     * updates command
     * @param subwindow
     * @param label
     * @param lb
     * @param atb
     */
    @Override
    public void update(DiagramSubwindow subwindow, InvocationMessageLabel label, ListBox lb, ArgumentTextBox atb) {
        this.setCommand(new MoveDownCommand(lb,label,subwindow));
    }
}
