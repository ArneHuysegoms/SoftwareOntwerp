package figures.basicShapes;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * a class used to draw a ractangle
 */
public class Rectangle extends Shape {

    protected Point2D positionTL;
    protected Point2D cornerTR;
    protected Point2D cornerBR;
    protected Point2D cornerBL;

    /**
     * @param x      the x-coordinate of the rectangle's center point
     * @param y      the y-coordinate of the rectangle's center point
     * @param width  the rectangles width
     * @param length the rectangles height
     */
    public Rectangle(int x, int y, int width, int length) {
        setPositionTL(new Point2D.Double(x, y));
        setCornerTR(new Point2D.Double(x + width, y));
        setCornerBR(new Point2D.Double(x + width, y + length));
        setCornerBL(new Point2D.Double(x, y + length));
    }

    /**
     * @param tl the rectangle's top-left point
     * @param br the rectangle's bottom-right point
     */
    public Rectangle(Point2D tl, Point2D br) {
        setPositionTL(tl);
        setCornerBR(br);
        setCornerBL(new Point2D.Double(tl.getX(), br.getY()));
        setCornerTR(new Point2D.Double(br.getX(), tl.getY()));
    }

    public Rectangle(Point2D subwindowPoint, int subwindowHeight, int subwindowWidth) {
        setPositionTL(subwindowPoint);
        setCornerBR(new Point2D.Double(subwindowPoint.getX()+subwindowHeight, subwindowPoint.getY()+subwindowWidth));
        setCornerBL(new Point2D.Double(subwindowPoint.getX()+subwindowHeight, subwindowPoint.getY()));
        setCornerTR(new Point2D.Double(subwindowPoint.getX(), subwindowPoint.getY()+subwindowWidth));
    }

    /**
     * returns the rectangles top-left point
     *
     * @return the rectangles top-left point
     */
    public Point2D getPositionTL() {
        return positionTL;
    }

    /**
     * sets the top-left position of the rectangle
     *
     * @param positionTL the top-left point of the rectangle
     */
    public void setPositionTL(Point2D positionTL) {
        this.positionTL = positionTL;
    }

    /**
     * returns the rectangles top-right point
     *
     * @return the rectangles top-right point
     */
    public Point2D getCornerTR() {
        return cornerTR;
    }

    /**
     * sets the rectangles top-right point
     *
     * @param cornerTR the rectangles top-right point
     */
    public void setCornerTR(Point2D cornerTR) {
        this.cornerTR = cornerTR;
    }

    /**
     * returns the rectangles bottom-right point
     *
     * @return the rectangles bottom-right point
     */
    public Point2D getCornerBR() {
        return cornerBR;
    }

    /**
     * sets the rectangles bottom-right point
     *
     * @param cornerBR the rectangles bottom-right point
     */
    public void setCornerBR(Point2D cornerBR) {
        this.cornerBR = cornerBR;
    }

    /**
     * returns the rectangles bottom-left point
     *
     * @return the rectangles bottom-left point
     */
    public Point2D getCornerBL() {
        return cornerBL;
    }

    /**
     * sets the rectangles bottom-left point
     *
     * @param cornerBL the rectangles bottom-left point
     */
    public void setCornerBL(Point2D cornerBL) {
        this.cornerBL = cornerBL;
    }

    /**
     * a draw fucntion that draws on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */

    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        new Line(positionTL, cornerTR).draw(graphics, minX, minY, maxX, maxY);
        new Line(cornerTR, cornerBR).draw(graphics, minX, minY, maxX, maxY);
        new Line(cornerBR, cornerBL).draw(graphics, minX, minY, maxX, maxY);
        new Line(cornerBL, positionTL).draw(graphics, minX, minY, maxX, maxY);
    }
}
