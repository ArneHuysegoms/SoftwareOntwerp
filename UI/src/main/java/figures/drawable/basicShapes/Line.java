package figures.drawable.basicShapes;

import figures.drawable.IDrawable;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * a class used to draw a line
 */
public class Line implements IDrawable {

    protected Point2D start;
    protected Point2D end;

    /**
     * @param x1 the x-coordinate of the line's start point
     * @param y1 the y-coordinate of the line's start point
     * @param x2 the x-coordinate of the line's end point
     * @param y2 the y-coordinate of the line's end point
     */
    public Line(int x1, int y1, int x2, int y2) {
        start = new Point2D.Double(x1, y1);
        end = new Point2D.Double(x2, y2);
    }

    /**
     * @param p1 the line's start point
     * @param p2 the line's end point
     */
    public Line(Point2D p1, Point2D p2) {
        start = p1;
        end = p2;
    }

    /**
     * returns the line's start point
     *
     * @return the line's start point
     */
    public Point2D getStart() {
        return start;
    }

    /**
     * returns the line's end point
     *
     * @return the line's end point
     */
    public Point2D getEnd() {
        return end;
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
        double x1 = getStart().getX(), x2 = getEnd().getX(), y1 = getStart().getY(), y2 = getEnd().getY();

        if (x1 < minX) {
            x1 = minX;
        }
        if (y1 < minY) {
            y1 = minY;
        }
        if (x1 > maxX) {
            x1 = maxX;
        }
        if (y1 > maxY) {
            y1 = maxY;
        }

        if (x2 < minX) {
            x2 = minX;
        }
        if (y2 < minY) {
            y2 = minY;
        }
        if (x2 > maxX) {
            x2 = maxX;
        }
        if (y2 > maxY) {
            y2 = maxY;
        }

        graphics.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawLine((int) getStart().getX(), (int) getStart().getY(), (int) getEnd().getX(), (int) getEnd().getY());
    }
}
