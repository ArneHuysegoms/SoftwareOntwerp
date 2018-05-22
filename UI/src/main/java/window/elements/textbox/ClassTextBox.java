package window.elements.textbox;

import diagram.party.Party;
import exception.UIException;

import java.awt.geom.Point2D;

/**
 * a textbox for a class
 */
public class ClassTextBox extends TextBox {

    /**
     * creates a new ClassTextBox
     *
     * @param coordinate  the new coordinate for this textbox
     * @param description the new description for this textbox
     * @throws UIException if the coordinate is null
     */
    public ClassTextBox(Point2D coordinate, String description) throws UIException {
        super(coordinate, description);
    }

    /**
     * @return true if the contents are valid
     */
    @Override
    public boolean hasValidContents() {
        return super.hasValidContents() && Party.isValidClassString(getContents());
    }
}
