package window.elements;

import command.Command;
import exception.UIException;
import window.Clickable;

import java.awt.geom.Point2D;

public class RadioButton extends DialogboxElement implements Clickable,ICommandable {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    private Command command;

    public RadioButton(Command command, Point2D coordinate) throws UIException {
        super(coordinate);
        this.setCommand(command);
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public void performAction() {
        getCommand().performAction();
    }

    @Override
    public boolean isClicked(Point2D location) {
        double startX = getCoordinate().getX();
        double endX = getCoordinate().getX() + WIDTH;
        double startY = getCoordinate().getY() ;
        double endY = getCoordinate().getY() + HEIGHT;
        return (startX <= location.getX() && endX >= location.getX()) && (startY <= location.getY() && endY >= location.getY());
    }


}
