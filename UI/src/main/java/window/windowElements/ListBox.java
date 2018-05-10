package window.windowElements;

import command.Command;
import exception.UIException;

import java.awt.geom.Point2D;

public class ListBox extends DialogboxElement {

    public ListBox(Command command, Point2D coordinate) throws UIException {
        super(command, coordinate);
    }
}
