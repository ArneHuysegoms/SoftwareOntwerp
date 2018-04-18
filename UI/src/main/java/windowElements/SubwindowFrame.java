package windowElements;

import subwindow.Button;
import subwindow.Clickable;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * subwindow frame, consists of subwindowframe rectangles for ease of use
 */
public class SubwindowFrame implements Clickable{

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
     * @param subWindowPoint the top left corner of the subwindow
     * @param subwindowHeight the height of the subwindow
     * @param subwindowWidth the width of the subwindow
     */
    public SubwindowFrame(Point2D subWindowPoint, int subwindowHeight, int subwindowWidth, Button button){
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

        titleBar = new TitleBar(subWindowPoint, subwindowWidth - 30);

        this.button = button;
        this.button.setPosition(new Point2D.Double(subWindowPoint.getX() + subwindowWidth - 30, subWindowPoint.getY()));
    }

    /**
     * sets the subwindowpoint to the given point
     * @param subwindowPoint
     */
    private void setSubwindowPoint(Point2D subwindowPoint) {
        this.subwindowPoint = subwindowPoint;
    }

    /**
     * returns point of subwindow
     * @return subwindowPoint
     */
    public Point2D getSubwindowPoint(){
        return this.subwindowPoint;
    }
    /**
     * sets the subwindowheight to the given height
     * @param subwindowHeight
     */
    private void setSubwindowHeight(int subwindowHeight) {
        this.subwindowHeight = subwindowHeight;
    }

    /**
     * returns height of subwindow
     * @return subwindowHeight
     */
    public int getSubwindowHeight(){
        return subwindowHeight;
    }
    /**
     * sets the width to the given width
     * @param subwindowWidth
     */
    private void setSubwindowWidth(int subwindowWidth) {
        this.subwindowWidth = subwindowWidth;
    }

    /**
     *  returns width of subwindow
     * @return subwindowWidth
     */
    public int getSubwindowWidth(){
        return subwindowWidth;
    }
    /**
     * @return the list of rectangles of which this frame consists of
     */
    public List<SubwindowFrameRectangle> getRectangles(){
        return this.rectangles;
    }

    public List<SubwindowFrameCorner> getCorners() { return this.corners;}

    public Button getButton() { return button; }

    public TitleBar getTitleBar() {
        return titleBar;
    }

    private void setTitleBar(TitleBar titleBar) {
        this.titleBar = titleBar;
    }

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

    public Clickable getFrameElement(Point2D location){
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