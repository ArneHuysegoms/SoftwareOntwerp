package window.elements.button;

import exception.UIException;

import java.awt.geom.Point2D;

public class AddArgumentFakeButton extends TextualFakeButton {
    /**
     * create a new fakeButton at the given position
     *
     * @param position the position for the fakebutton
     * @throws UIException if position is null
     */
    public AddArgumentFakeButton(Point2D position, String description) throws UIException {
        super(position, description);
    }
}
