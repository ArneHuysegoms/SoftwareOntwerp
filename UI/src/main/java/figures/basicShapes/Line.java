package figures.basicShapes;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * a class used to draw a line
 */
public class Line extends Shape {

    protected Point2D start;
    protected Point2D end;

    /**
     *
     * @param x1
     *      the x-coordinate of the line's start point
     * @param y1
     *      the y-coordinate of the line's start point
     * @param x2
     *      the x-coordinate of the line's end point
     * @param y2
     *      the y-coordinate of the line's end point
     */
    public Line(int x1, int y1, int x2, int y2) {
        start = new Point2D.Double(x1, y1);
        end = new Point2D.Double(x2, y2);
    }

    /**
     *
     * @param p1
     *      the line's start point
     * @param p2
     *      the line's end point
     */
    public Line(Point2D p1, Point2D p2) {
        start = p1;
        end = p2;
    }

    /**
     * returns the line's start point
     * @return
     *      the line's start point
     */
    public Point2D getStart() {
        return start;
    }

    /**
     *  returns the line's end point
     * @return
     *      the line's end point
     */
    public Point2D getEnd() {
        return end;
    }

    /**
     * a draw fucntion that draws on the Graphics parameter object
     * @param graphics
     *      object used to draw on the program's window
     */
    @Override
    public void draw(Graphics graphics) {
        graphics.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
    }

}
