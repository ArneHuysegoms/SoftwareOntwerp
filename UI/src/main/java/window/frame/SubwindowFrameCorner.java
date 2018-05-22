package window.frame;

import window.Clickable;

import java.awt.geom.Point2D;

/**
 * represents a corner of a window.diagram
 */
public class SubwindowFrameCorner implements Clickable {

    private final int OFFSET = 5;

    private Point2D center;
    private CornerType type;

    /**
     * create a new subwindowcorner on the specified location
     *
     * @param centerPoint the center of the subwindowcorner
     * @param type        the type for this corner
     */
    public SubwindowFrameCorner(Point2D centerPoint, CornerType type) {
        this.setCenter(centerPoint);
        this.setType(type);
    }

    /**
     * @return the  type of the corner
     */
    public CornerType getType() {
        return type;
    }

    /**
     * sets the type of this corner to the given corner
     *
     * @param type
     */
    private void setType(CornerType type) {
        this.type = type;
    }

    /**
     * @return the center location of the window.diagram
     */
    public Point2D getCenter() {
        return center;
    }

    /**
     * sets the center of this window.diagram to the given centerpoint
     *
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
