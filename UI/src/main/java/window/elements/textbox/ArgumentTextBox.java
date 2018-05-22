package window.elements.textbox;

import diagram.Argument;
import exception.UIException;

import java.awt.geom.Point2D;

/**
 * class for an argument textbox
 */
public class ArgumentTextBox extends TextBox {

    /**
     * creates a new argument textbox with the given parameters
     *
     * @param coordinate  the new coordinate
     * @param description the new description
     * @throws UIException if the coordinate is null
     */
    public ArgumentTextBox(Point2D coordinate, String description) throws UIException {
        super(coordinate, description);
    }

    /**
     * @return true if he contents are valid
     */
    @Override
    public boolean hasValidContents() {
        if (super.hasValidContents()) {
            String[] args = this.getContents().split(":");
            if (args.length > 1) {
                return Argument.isValidArgument(args[0], args[1]);
            }
            return false;
        }
        return false;
    }
}
