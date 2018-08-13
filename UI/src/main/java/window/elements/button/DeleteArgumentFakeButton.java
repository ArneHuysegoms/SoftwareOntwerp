package window.elements.button;

import exception.UIException;

import java.awt.geom.Point2D;

public class DeleteArgumentFakeButton extends TextualFakeButton {

    /**
     * creates a new textualfakebutton with the given parameters
     *
     * @param position    the new position
     * @param description the new description
     * @throws UIException if position is null
     */
    public DeleteArgumentFakeButton(Point2D position, String description) throws UIException {
        super(position, description);
    }
}
