package window.elements.button;

import java.awt.geom.Point2D;

public class TextualFakeButton extends FakeButton{

    private final String description;


    public TextualFakeButton(Point2D position, String description) {
        super(position);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
