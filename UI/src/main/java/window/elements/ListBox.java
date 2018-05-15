package window.elements;

import command.Command;
import exception.UIException;
import window.Clickable;

import java.awt.geom.Point2D;

public class ListBox extends DialogboxElement implements Clickable {

    public ListBox(Point2D coordinate, String description) throws UIException {
        super(coordinate, description);
    }

    @Override
    public boolean isClicked(Point2D location) {
        //TODO
        return false;
    }
}
