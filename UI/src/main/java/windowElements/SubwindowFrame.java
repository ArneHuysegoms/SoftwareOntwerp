package windowElements;

import subwindow.Clickable;
import subwindow.Subwindow;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class SubwindowFrame{

    private final int FRAMEHOFFSET = 5;

    Point2D subwindowPoint;
    int subwindowHeight;
    int subwindowWidth;

    private List<SubwindowFrame.SubwindowFrameRectangle> rectangles;

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

    private void setSubwindowPoint(Point2D subwindowPoint) {
        this.subwindowPoint = subwindowPoint;
    }

    private void setSubwindowHeight(int subwindowHeight) {
        this.subwindowHeight = subwindowHeight;
    }

    private void setSubwindowWidth(int subwindowWidth) {
        this.subwindowWidth = subwindowWidth;
    }

    public List<SubwindowFrameRectangle> getRectangles(){
        return this.rectangles;
    }

    public class SubwindowFrameRectangle implements Clickable {

        private Point2D position;
        private int height;
        private int width;

        public SubwindowFrameRectangle(Point2D position, int height, int width){
            this.position = position;
            this.height = height;
            this.width = width;
        }

        @Override
        public boolean isClicked(Point2D location) {
            double startX = position.getX();
            double endX = position.getX() + width;
            double startY = position.getY();
            double endY = position.getY() + height;
            return (startX <= location.getX() && endX >= location.getX()) && (startY <= location.getY() && endY >= location.getY());
        }

        public Point2D getPosition() {
            return position;
        }

        private void setPosition(Point2D position) {
            this.position = position;
        }

        public int getHeight() {
            return height;
        }

        private void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        private void setWidth(int width) {
            this.width = width;
        }
    }
}
