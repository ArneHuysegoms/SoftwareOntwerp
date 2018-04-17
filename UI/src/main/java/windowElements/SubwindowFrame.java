package windowElements;

import subwindow.Clickable;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * subwindow frame, consists of subwindowframe rectangles for ease of use
 */
public class SubwindowFrame{

    private final int FRAMEHOFFSET = 5;

    Point2D subwindowPoint;
    int subwindowHeight;
    int subwindowWidth;

    private List<SubwindowFrame.SubwindowFrameRectangle> rectangles;

    /**
     * create a new subwindowframe based on the given properties
     * @param subWindowPoint the top left corner of the subwindow
     * @param subwindowHeight the height of the subwindow
     * @param subwindowWidth the width of the subwindow
     */
    public SubwindowFrame(Point2D subWindowPoint, int subwindowHeight, int subwindowWidth){
        this.setSubwindowPoint(subWindowPoint);
        this.setSubwindowHeight(subwindowHeight);
        this.setSubwindowWidth(subwindowWidth);
        rectangles = new ArrayList<>();
        rectangles.add(new SubwindowFrame.SubwindowFrameRectangle(new Point2D.Double(subwindowPoint.getX() + FRAMEHOFFSET, subWindowPoint.getY() - FRAMEHOFFSET), 10, subwindowWidth - 10));
        rectangles.add(new SubwindowFrame.SubwindowFrameRectangle(new Point2D.Double(subwindowPoint.getX() - FRAMEHOFFSET, subWindowPoint.getY() + FRAMEHOFFSET), subwindowHeight - 10, 10));
        rectangles.add(new SubwindowFrame.SubwindowFrameRectangle(new Point2D.Double(subwindowPoint.getX() + FRAMEHOFFSET, subWindowPoint.getY() - FRAMEHOFFSET + subwindowHeight), 10, subwindowWidth - 10));
        rectangles.add(new SubwindowFrame.SubwindowFrameRectangle(new Point2D.Double(subwindowPoint.getX() - FRAMEHOFFSET + subwindowWidth, subWindowPoint.getY() + FRAMEHOFFSET), subwindowHeight - 10, 10));
    }

    /**
     * sets the subwindowpoint to the given point
     * @param subwindowPoint
     */
    private void setSubwindowPoint(Point2D subwindowPoint) {
        this.subwindowPoint = subwindowPoint;
    }

    /**
     * sets the subwindowheight to the given height
     * @param subwindowHeight
     */
    private void setSubwindowHeight(int subwindowHeight) {
        this.subwindowHeight = subwindowHeight;
    }

    /**
     * sets the width to the given width
     * @param subwindowWidth
     */
    private void setSubwindowWidth(int subwindowWidth) {
        this.subwindowWidth = subwindowWidth;
    }

    /**
     * @return the list of rectangles of which this frame consists of
     */
    public List<SubwindowFrameRectangle> getRectangles(){
        return this.rectangles;
    }

    /**
     * helper class to build a frame off
     */
    public class SubwindowFrameRectangle implements Clickable {

        private Point2D position;
        private int height;
        private int width;

        /**
         * constructs a new subwindowframerectangle with the given properties
         * @param position the position of this rectangle
         * @param height the height of this rectangle
         * @param width the width of this rectangle
         */
        public SubwindowFrameRectangle(Point2D position, int height, int width){
            this.position = position;
            this.height = height;
            this.width = width;
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
    }
}
