package window.elements.textbox;

import exception.UIException;

import java.awt.geom.Point2D;

/**
 * textbox for a method name
 */
public class MethodTextBox extends TextBox {

    /**
     * creates a new methodtextbox with the given parameters
     *
     * @param coordinate  the coordinate
     * @param description the description
     * @throws UIException if the coordinate is null
     */
    public MethodTextBox(Point2D coordinate, String description) throws UIException {
        super(coordinate, description);
    }

    /**
     * @return true if this textbox has valid contents
     */
    @Override
    public boolean hasValidContents() {
        return !this.getContents().isEmpty();
    }
}
