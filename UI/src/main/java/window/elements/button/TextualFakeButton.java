package window.elements.button;

import exception.UIException;

import java.awt.geom.Point2D;

/**
 * a fakebutton with a textual description
 */
public class TextualFakeButton extends FakeButton {

    /**
     * creates a new textualfakebutton with the given parameters
     *
     * @param position    the new position
     * @param description the new description
     * @throws UIException if position is null
     */
    public TextualFakeButton(Point2D position, String description) throws UIException {
        super(position);
        super.setDescription(description);
    }
}
