package window.frame;

import window.elements.button.Button;
import window.Clickable;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * window.diagram frame, consists of subwindowframe rectangles for ease of use
 */
public class SubwindowFrame implements Clickable {

    private final int FRAMEHOFFSET = 5;

    Point2D subwindowPoint;
    int subwindowHeight;
    int subwindowWidth;

    private List<SubwindowFrameRectangle> rectangles;
    private List<SubwindowFrameCorner> corners;
    private TitleBar titleBar;
    private Button button;

    /**
     * create a new subwindowframe based on the given properties
     *
     * @param subWindowPoint  the top left corner of the window.diagram
     * @param subwindowHeight the height of the window.diagram
     * @param subwindowWidth  the width of the window.diagram
     */
    public SubwindowFrame(Point2D subWindowPoint, int subwindowHeight, int subwindowWidth) {
        this(subWindowPoint, subwindowHeight, subwindowWidth, null);
    }

    /**
     * create a new subwindowframe based on the given properties
     *
     * @param subWindowPoint  the top left corner of the window.diagram
     * @param subwindowHeight the height of the window.diagram
     * @param subwindowWidth  the width of the window.diagram
     */
    public SubwindowFrame(Point2D subWindowPoint, int subwindowHeight, int subwindowWidth, Button button) {
        this.setSubwindowPoint(subWindowPoint);
        this.setSubwindowHeight(subwindowHeight);
        this.setSubwindowWidth(subwindowWidth);
        rectangles = new ArrayList<>();
        rectangles.add(new SubwindowFrameRectangle(new Point2D.Double(subwindowPoint.getX() + FRAMEHOFFSET, subWindowPoint.getY() - FRAMEHOFFSET), 10, subwindowWidth - 10, RectangleType.TOP));
        rectangles.add(new SubwindowFrameRectangle(new Point2D.Double(subwindowPoint.getX() - FRAMEHOFFSET, subWindowPoint.getY() + FRAMEHOFFSET), subwindowHeight - 10, 10, RectangleType.LEFT));
        rectangles.add(new SubwindowFrameRectangle(new Point2D.Double(subwindowPoint.getX() + FRAMEHOFFSET, subWindowPoint.getY() - FRAMEHOFFSET + subwindowHeight), 10, subwindowWidth - 10, RectangleType.BOTTOM));
        rectangles.add(new SubwindowFrameRectangle(new Point2D.Double(subwindowPoint.getX() - FRAMEHOFFSET + subwindowWidth, subWindowPoint.getY() + FRAMEHOFFSET), subwindowHeight - 10, 10, RectangleType.RIGHT));

        corners = new ArrayList<>();
        corners.add(new SubwindowFrameCorner(new Point2D.Double(subWindowPoint.getX(), subWindowPoint.getY()), CornerType.TOPLEFT));
        corners.add(new SubwindowFrameCorner(new Point2D.Double(subWindowPoint.getX() + subwindowWidth, subWindowPoint.getY()), CornerType.TOPRIGHT));
        corners.add(new SubwindowFrameCorner(new Point2D.Double(subWindowPoint.getX(), subWindowPoint.getY() + subwindowHeight), CornerType.BOTTOMLEFT));
        corners.add(new SubwindowFrameCorner(new Point2D.Double(subWindowPoint.getX() + subwindowWidth, subWindowPoint.getY() + subwindowHeight), CornerType.BOTTOMRIGHT));

        setTitleBar(new TitleBar(subWindowPoint, subwindowWidth - 30));

        this.setButton(button);
    }

    /**
     * sets the button to the given button
     *
     * @param button the new button for this subwindow
     */
    public void setButton(Button button) {
        this.button = button;
        if (button != null) {
            this.button.setPosition(new Point2D.Double(getSubwindowPoint().getX() + subwindowWidth - 30, getSubwindowPoint().getY()));
        }
    }

    /**
     * sets the subwindowpoint to the given point
     *
     * @param subwindowPoint the point of the window.diagram this frame is the frame off
     */
    private void setSubwindowPoint(Point2D subwindowPoint) {
        this.subwindowPoint = subwindowPoint;
    }

    /**
     * returns point of window.diagram
     *
     * @return subwindowPoint
     */
    public Point2D getSubwindowPoint() {
        return this.subwindowPoint;
    }

    /**
     * sets the subwindowheight to the given height
     *
     * @param subwindowHeight the height of this frame
     */
    private void setSubwindowHeight(int subwindowHeight) {
        this.subwindowHeight = subwindowHeight;
    }

    /**
     * returns height of window.diagram
     *
     * @return subwindowHeight
     */
    public int getSubwindowHeight() {
        return subwindowHeight;
    }

    /**
     * sets the width to the given width
     *
     * @param subwindowWidth the width of this frame
     */
    private void setSubwindowWidth(int subwindowWidth) {
        this.subwindowWidth = subwindowWidth;
    }

    /**
     * returns width of window.diagram
     *
     * @return subwindowWidth
     */
    public int getSubwindowWidth() {
        return subwindowWidth;
    }

    /**
     * @return the list of rectangles of which this frame consists of
     */
    public List<SubwindowFrameRectangle> getRectangles() {
        return this.rectangles;
    }

    /**
     * @return the corners of this frame
     */
    public List<SubwindowFrameCorner> getCorners() {
        return this.corners;
    }

    /**
     * @return the button of this frame
     */
    public Button getButton() {
        return button;
    }

    /**
     * @return the titlebar of this frame
     */
    public TitleBar getTitleBar() {
        return titleBar;
    }

    /**
     * sets the titlebar to the given titlebar
     *
     * @param titleBar the new titlebar
     */
    private void setTitleBar(TitleBar titleBar) {
        this.titleBar = titleBar;
    }

    /**
     * closes this subwindow
     */
    public void close() {
        if (this.getButton() != null) {
            this.getButton().performAction();
        }
    }

    /**
     * @param location the location of the click
     * @return true if this frame is clicked, alse otherwise
     */
    @Override
    public boolean isClicked(Point2D location) {
        for (SubwindowFrameCorner corner : corners) {
            if (corner.isClicked(location)) {
                return true;
            }
        }
        for (SubwindowFrameRectangle rectangle : getRectangles()) {
            if (rectangle.isClicked(location)) {
                return true;
            }
        }
        if (titleBar.isClicked(location)) {
            return true;
        }
        if (button.isClicked(location)) {
            return true;
        }
        return false;
    }

    /**
     * @param location the location we want to find the clickable for
     * @return a clickable denoting one of the frame elements
     */
    public Clickable getFrameElement(Point2D location) {
        for (SubwindowFrameCorner corner : corners) {
            if (corner.isClicked(location)) {
                return corner;
            }
        }
        for (SubwindowFrameRectangle rectangle : getRectangles()) {
            if (rectangle.isClicked(location)) {
                return rectangle;
            }
        }
        if (titleBar.isClicked(location)) {
            return new TitleBarClick(titleBar, location);
        }
        if (button.isClicked(location)) {
            return button;
        }
        return null;
    }
}
