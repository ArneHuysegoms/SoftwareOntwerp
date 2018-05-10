package window.windowElements;

import command.Command;
import exception.UIException;

import java.awt.geom.Point2D;

public class DialogboxElement implements ICommandable{

    private Point2D coordinate;

    private Command command;

    protected DialogboxElement(){

    }

    public DialogboxElement(Command command, Point2D coordinate) throws UIException{
        this.setCommand(command);
        this.setCoordinate(coordinate);
    }

    @Override
    public void performAction() {
        command.performAction();
    }

    public Command getCommand() {
        return command;
    }

    private void setCommand(Command command) throws UIException{
        if(command == null){
            throw new UIException("Command for dialogboxElements may not be null");
        }
        this.command = command;
    }

    public Point2D getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point2D coordinate) throws UIException{
        if(coordinate == null){
            throw new UIException("Coordinate may not be null for dialogboxElements");
        }
        this.coordinate = coordinate;
    }
}
