package window.elements.textbox;

import diagram.party.Party;
import exception.UIException;

import java.awt.geom.Point2D;

/**
 * textbox for an instance
 */
public class InstanceTextBox extends TextBox {

    /**
     * creates a new instance textbox with the given parameters
     *
     * @param coordinate  the new coordinate
     * @param description the new description
     * @throws UIException if the coordinate is null
     */
    public InstanceTextBox(Point2D coordinate, String description) throws UIException {
        super(coordinate, description);
    }

    /**
     * @return true if the contents are valid
     */
    @Override
    public boolean hasValidContents() {
        return getContents().equals("") || Party.isValidInstanceString(this.getContents());
    }
}
