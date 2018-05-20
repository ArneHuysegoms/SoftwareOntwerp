package window.elements.textbox;

import exception.UIException;

import java.awt.geom.Point2D;

public class MethodTextBox extends TextBox{

    public MethodTextBox(Point2D coordinate, String description) throws UIException {
        super(coordinate, description);
    }

    @Override
    public boolean hasValidContents() {
        return ! this.getContents().isEmpty();
    }
}
