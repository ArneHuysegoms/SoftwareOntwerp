package window.windowElements;

import command.Command;
import exception.UIException;

import java.awt.geom.Point2D;

public class TextBox extends DialogboxElement{

    private String contents;

    public TextBox(Command command, Point2D coordinate) throws UIException {
        super(command, coordinate);
    }


}
