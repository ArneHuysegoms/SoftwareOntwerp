package windowElements;

import subwindow.Clickable;

import java.awt.geom.Point2D;

/**
 * titlebar of a frame
 */
public class TitleBar implements Clickable {

    private final int HEIGHT = 30;

    Point2D position;
    int width;

    /**
     * creates a new titlebar on the given location with the specified width
     * @param position the position of the titlebar, upperleft corner
     * @param width the width of the titlebar
     */
    public TitleBar(Point2D position, int width){
        this.position = position;
        this.width = width;
    }

    /**
     * checks if the titlebar was clicked on
     *
     * @param location the location of the click
     * @return true if the titlebar was clicked on by a click on the given location, false otherwise
     */
    @Override
    public boolean isClicked(Point2D location) {
        double startX = position.getX();
        double endX = position.getX() + width;
        double startY = position.getY() ;
        double endY = position.getY() + HEIGHT;
        return (startX <= position.getX() && endX >= location.getX()) && (startY <= location.getY() && endY >= location.getY());
    }
}
