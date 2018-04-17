package windowElements;

import subwindow.Clickable;

import java.awt.geom.Point2D;

/**
 * represents a corner of a subwindow
 */
public class SubwindowFrameCorner implements Clickable {

    private final int OFFSET = 5;

    public Point2D center;

    /**
     * create a new subwindowcorner on the specified location
     * @param centerPoint the center of the subwindowcorner
     */
    public SubwindowFrameCorner(Point2D centerPoint){
        this.setCenter(centerPoint);
    }


    /**
     *
     * @return the center location of the subwindow
     */
    public Point2D getCenter() {
        return center;
    }

    /**
     * sets the center of this subwindow to the given centerpoint
     * @param center
     */
    private void setCenter(Point2D center) {
        this.center = center;
    }

    /**
     * checks if the corner was clicked on
     *
     * @param location the location of the click
     * @return true if the corner was clicked on by a click on the given location, false otherwise
     */
    @Override
    public boolean isClicked(Point2D location) {
        double startX = center.getX() - OFFSET;
        double endX = center.getX() + OFFSET;
        double startY = center.getY() - OFFSET;
        double endY = center.getY() + OFFSET;
        return (startX <= location.getX() && endX >= location.getX()) && (startY <= location.getY() && endY >= location.getY());
    }
}
