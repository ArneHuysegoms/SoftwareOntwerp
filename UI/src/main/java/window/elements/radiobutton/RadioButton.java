package window.elements.radiobutton;

import action.Action;
import command.Command;
import diagram.party.Party;
import exception.UIException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import window.Clickable;
import window.Subwindow;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;
import window.elements.ICommandable;

import java.awt.geom.Point2D;

/**
 * class detailing a radiobutton
 */
public abstract class RadioButton extends DialogboxElement implements Clickable {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    private Command command;

    /**
     * constructs a new radiobutton with the given parametesr
     *
     * @param command     the command for this radiobutton
     * @param coordinate  the coordinate of this radiobutton
     * @param description the description for this radiobutton
     * @throws UIException throws an uiexception if the command is invalid
     */
    public RadioButton(Command command, Point2D coordinate, String description) throws UIException {
        super(coordinate, description);
        this.setCommand(command);
    }

    /**
     * @return the command of this radiobutton
     */
    public Command getCommand() {
        return command;
    }

    /**
     * @param command the command to set the command of this radiobutton too
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * perform the action of this radiobutton
     *
     * @return an action detailing the change by performing this action
     */
    @Override
    public Action performAction() {
        return getCommand().performAction();
    }

    /**
     * @param location the location of the click
     * @return true if this radiobutton is clicked
     */
    @Override
    public boolean isClicked(Point2D location) {
        double startX = getCoordinate().getX();
        double endX = getCoordinate().getX() + WIDTH;
        double startY = getCoordinate().getY();
        double endY = getCoordinate().getY() + HEIGHT;
        return (startX <= location.getX() && endX >= location.getX()) && (startY <= location.getY() && endY >= location.getY());
    }
}
