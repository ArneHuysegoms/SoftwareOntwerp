package window.elements.textbox;

import diagram.party.Party;
import exception.UIException;

import java.awt.geom.Point2D;

public class ClassTextBox extends TextBox {

    public ClassTextBox(Point2D coordinate, String description) throws UIException {
        super(coordinate, description);
    }

    @Override
    public boolean hasValidContents() {
        return super.hasValidContents() && Party.isValidClassString(getContents());
    }
}
