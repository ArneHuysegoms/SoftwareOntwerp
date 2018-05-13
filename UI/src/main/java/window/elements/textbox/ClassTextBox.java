package window.elements.textbox;

import diagram.party.Party;
import exception.UIException;

import java.awt.geom.Point2D;

public class ClassTextBox extends TextBox {

    public ClassTextBox(Point2D coordinate) throws UIException {
        super(coordinate);
    }

    @Override
    public boolean hasValidContents() {
        return Party.isValidClassString(getContents());
    }
}
