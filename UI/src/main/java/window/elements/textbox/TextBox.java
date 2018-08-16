package window.elements.textbox;

import exception.UIException;
import util.MutableString;
import window.Clickable;
import window.elements.DialogboxElement;

import javax.swing.*;
import java.awt.geom.Point2D;

/**
 * abstract superclass for textboxes
 */
public abstract class TextBox extends DialogboxElement implements Clickable {

    public static final int HEIGHT = 14;
    public static final int WIDTH = 70;

    /**
     * creates a new textbox with the given paramters
     *
     * @param coordinate  the coordinate for the textbox
     * @param description the description for the textbox
     * @throws UIException if the coordinate is null
     */
    public TextBox(Point2D coordinate, String description) throws UIException {
        super(coordinate, description);
        this.setContents("");
    }

    /**
     * return true if this textbox is clicked
     *
     * @param location the location of the click
     * @return true if the textbox is clicked on the given location
     */
    @Override
    public boolean isClicked(Point2D location) {
        double startX = getCoordinate().getX();
        double endX = getCoordinate().getX() + WIDTH;
        double startY = getCoordinate().getY();
        double endY = getCoordinate().getY() + HEIGHT;
        return (startX <= location.getX() && endX >= location.getX()) && (startY <= location.getY() && endY >= location.getY());
    }
}
