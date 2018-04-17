package windowElements;

import subwindow.Clickable;

import java.awt.geom.Point2D;

/**
 * helper class to build a frame off
 */
public class SubwindowFrameRectangle implements Clickable {

    private Point2D position;
    private int height;
    private int width;
    private RectangleType type;

    /**
     * constructs a new subwindowframerectangle with the given properties
     * @param position the position of this rectangle
     * @param height the height of this rectangle
     * @param width the width of this rectangle
     * @param type the type of rectangle
     */
    public SubwindowFrameRectangle(Point2D position, int height, int width, RectangleType type){
        this.position = position;
        this.height = height;
        this.width = width;
        this.type = type;
    }

    /**
     * checks if the rectangle was clicked on
     *
     * @param location the location of the click
     * @return true if the rectangle was clicked on by a click on the given location, false otherwise
     */
    @Override
    public boolean isClicked(Point2D location) {
        double startX = position.getX();
        double endX = position.getX() + width;
        double startY = position.getY();
        double endY = position.getY() + height;
        return (startX <= location.getX() && endX >= location.getX()) && (startY <= location.getY() && endY >= location.getY());
    }

    /**
     * @return the point of this rectangle
     */
    public Point2D getPosition() {
        return position;
    }

    /**
     *
     * @return the height of this rectangle
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @return the width of this rectangle
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the type of rectangle
     */
    public RectangleType getType(){
        return type;
    }
}

