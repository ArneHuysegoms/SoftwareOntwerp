package windowFrame;

import window.windowElements.Clickable;

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
        setPosition(position);
        setWidth(width);
    }

    /**
     *
     * @return the postion of this titlebar
     */
    public Point2D getPosition() {
        return position;
    }

    /**
     * sets the position of this titlebar to the given position
     * @param position
     */
    private void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     *
     * @return the width of this titleframe
     */
    public int getWidth() {
        return width;
    }

    /**
     * sets the width of this titlebar to the given titlebar
     * @param width the new width for this titlebar
     */
    private void setWidth(int width) {
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
        return (startX <= location.getX() && endX >= location.getX()) && (startY <= location.getY() && endY >= location.getY());
    }
}
