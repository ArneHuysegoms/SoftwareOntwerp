package window.elements.button;

import java.awt.geom.Point2D;

/**
 * a fakebutton with a textual description
 */
public class TextualFakeButton extends FakeButton {

    private final String description;

    /**
     * creates a new textualfakebutton with the given parameters
     *
     * @param position    the new position
     * @param description the new description
     */
    public TextualFakeButton(Point2D position, String description) {
        super(position);
        this.description = description;
    }

    /**
     * @return the description of this fake button
     */
    public String getDescription() {
        return description;
    }
}
