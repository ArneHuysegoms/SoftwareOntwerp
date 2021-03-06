package window.elements.button;

import action.Action;
import command.Command;
import command.InvocationCommand.InvocationCommand;
import exception.UIException;
import window.elements.DialogboxElement;

import java.awt.geom.Point2D;

public abstract class DialogBoxButton extends DialogboxElement {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;

    private int width;
    private int height;

    private InvocationCommand command;

    /**
     * constructs a new DialogBoxButton with the given parametesr
     *
     * @param command     the command for this DialogBoxButton
     * @param coordinate  the coordinate of this DialogBoxButton
     * @param description the description for this DialogBoxButton
     * @throws UIException throws an uiexception if the command is invalid
     */
    public DialogBoxButton(Command command, Point2D coordinate, String description) throws UIException {
        super(coordinate, description);
        this.height =HEIGHT;
        this.width=WIDTH;
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
     *
     * @return command
     */
    public Command getCommand() {
        return command;
    }

    /**
     * set command
     * @param c
     */
    protected void setCommand(InvocationCommand c) {
        this.command = c;
    }

    /**
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * check if the button is clicked
     * @param coordinate the location of the click
     * @return
     */
    @Override
    public boolean isClicked(Point2D coordinate) {
        double startX = getCoordinate().getX();
        double endX = getCoordinate().getX() + width;
        double startY = getCoordinate().getY();
        double endY = getCoordinate().getY() + height;
        return (startX <= coordinate.getX() && endX >= coordinate.getX()) && (startY <= coordinate.getY() && endY >= coordinate.getY());
    }

    /**
     * add character from description, override do nothing since dialogboxbuttosn don't have description
     */
    @Override
    public void addCharToDescription(char c){

    }
    /**
     * delete character from description, override do nothing since dialogboxbuttons don't have description
     */
    @Override
    public void deleteCharFromDescription(){

    }

    /**
     * checks if valid description
     * our dialogboxbuttons don't have a description, so will always return true
     * @return true
     */
    @Override
    public boolean isValidDescription(){
        return true;
    }
}
