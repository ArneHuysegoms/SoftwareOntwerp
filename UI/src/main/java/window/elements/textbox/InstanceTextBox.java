package window.elements.textbox;

import diagram.party.Party;
import exception.UIException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import window.Subwindow;
import window.elements.DialogboxElement;

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

    @Override
    public DialogboxElement clone() {
        try {
            return new InstanceTextBox(getCoordinate(), getDescription());
        } catch (UIException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @return true if the contents are valid
     */
    @Override
    public boolean hasValidContents() {
        return getContents().equals("") || Party.isValidInstanceString(this.getContents());
    }
}
