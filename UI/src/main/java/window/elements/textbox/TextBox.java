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

    private String contents;

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
     * @return the contents of the textbox
     */
    public String getContents() {
        return contents;
    }


    /**
     * sets the contents to the given contents
     *
     * @param contents the new contents
     */
    public void setContents(String contents) {
        this.contents = contents;
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

    /**
     * returns if the textbox has valid contents
     *
     * @return ture if the contents are valid
     */
    public boolean hasValidContents() {
        return !this.getContents().isEmpty();
    }


    /**
     * adds the given char to the contents
     *
     * @param toAdd the char to add
     */
    public void addCharToContents(char toAdd) {
        this.contents += toAdd;
    }

    /**
     * deletes the last char from the contents
     */
    public void deleteLastCharFromContents() {
        if (contents.length() > 1) {
            this.setContents(this.getContents().substring(0, this.getContents().length() - 1));
        } else {
            this.setContents("");
        }
    }
}
