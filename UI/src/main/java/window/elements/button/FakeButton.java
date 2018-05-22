package window.elements.button;

import exception.UIException;
import window.elements.DialogboxElement;

import java.awt.geom.Point2D;

/**
 * a class for fakebuttons, these are buttons with no command behind them; but are buttons in every other way
 */
public class FakeButton extends DialogboxElement {

    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;

    private int width;
    private int height;

    /**
     * create a new fakeButton at the given position
     *
     * @param position the position for the fakebutton
     * @throws UIException if position is null
     */
    public FakeButton(Point2D position) throws UIException {
        super(position, "");
        this.setHeight(HEIGHT);
        this.setWidth(WIDTH);
    }

    /**
     * @return the width of the fakebutton
     */
    public int getWidth() {
        return width;
    }

    /**
     * sets the width for the fake button to the given width
     *
     * @param width the new width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height of this fakebutton
     */
    public int getHeight() {
        return height;
    }

    /**
     * sets the height to the given height
     *
     * @param height the new height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * tests if the button is clicked on the given location
     *
     * @param coordinate the location of the click
     * @return true if the fakebutton is clicked on the given location
     */
    @Override
    public boolean isClicked(Point2D coordinate) {
        double startX = getCoordinate().getX();
        double endX = getCoordinate().getX() + width;
        double startY = getCoordinate().getY();
        double endY = getCoordinate().getY() + height;
        return (startX <= coordinate.getX() && endX >= coordinate.getX()) && (startY <= coordinate.getY() && endY >= coordinate.getY());
    }
}
