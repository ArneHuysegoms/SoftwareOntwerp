package window.elements;

import command.Command;
import exception.UIException;
import window.Clickable;

import java.awt.geom.Point2D;

public class ListBox extends DialogboxElement implements Clickable {

    public ListBox(Command command, Point2D coordinate) throws UIException {
        super(command, coordinate);
    }

    @Override
    public boolean isClicked(Point2D location) {
        //TODO
        return false;
    }
}
