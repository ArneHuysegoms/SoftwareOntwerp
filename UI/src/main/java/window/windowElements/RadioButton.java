package window.windowElements;

import command.Command;
import exception.UIException;

import java.awt.geom.Point2D;

public class RadioButton extends DialogboxElement {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    public RadioButton(Command command, Point2D coordinate) throws UIException {
        super(command, coordinate);
    }
}
