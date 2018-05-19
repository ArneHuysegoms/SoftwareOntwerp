package figures.drawable.diagramFigures;

import figures.drawable.basicShapes.Line;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * a class used to draw an arrow
 */
public class Arrow extends Figure {
    private Point2D lineStart, lineEnd;
    private Line arrowTop;
    private Line arrowBottom;

    /**
     * @param start the arrow's start point
     * @param end   the arrow's end point
     */
    public Arrow(Point2D start, Point2D end) {
        lineStart = start;
        lineEnd = end;
        calculateArrowHead((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
    }

    /**
     * methods that calculates the arrow's head at the end point
     *
     * @param x  the x-coordinate of the arrow's start point
     * @param y  the y-coordinate of the arrow's start point
     * @param x2 the x-coordinate of the arrow's end point
     * @param y2 the y-coordinate of the arrow's end point
     */
    private void calculateArrowHead(int x, int y, int x2, int y2) {
        if (x < x2) {
            if (y == y2) {
                arrowTop = new Line(x2, y, x2 - 10, y - 10);
                arrowBottom = new Line(x2, y, x2 - 10, y + 10);
            } else if (y < y2) {
                //ZuidOost
                arrowTop = new Line(x2, y2, x2, y2 - 10);
                arrowBottom = new Line(x2, y2, x2 - 10, y2);
            } else if (y > y2) {
                //NoordOost
                arrowTop = new Line(x2, y2, x2 - 10, y2);
                arrowBottom = new Line(x2, y2, x2, y2 + 10);
            }
        } else if (x > x2) {
            if (y == y2) {
                arrowTop = new Line(x2, y2, x2 + 10, y2 - 10);
                arrowBottom = new Line(x2, y2, x2 + 10, y2 + 10);
            } else if (y < y2) {
                //ZuiWest
                arrowTop = new Line(x2, y2, x2, y2 - 10);
                arrowBottom = new Line(x2, y2, x2 + 10, y2);
            } else if (y > y2) {
                //NoordWest
                arrowTop = new Line(x2, y2, x2 + 10, y2);
                arrowBottom = new Line(x2, y2, x2, y2 + 10);
            }
        } else if (x == x2) {
            if (y < y2) {
                //South
                arrowTop = new Line(x2, y2, x2 + 10, y2 - 10);
                arrowBottom = new Line(x2, y2, x2 - 10, y2 - 10);
            } else if (y > y2) {
                //North
                arrowTop = new Line(x2, y2, x2 - 10, y2 + 10);
                arrowBottom = new Line(x2, y2, x2 + 10, y2 + 10);
            }
        }

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
        new Line(this.getLineStart(), this.getLineEnd()).draw(graphics, minX, minY, maxX, maxY);
        this.getArrowTop().draw(graphics, minX, minY, maxX, maxY);
        this.getArrowBottom().draw(graphics, minX, minY, maxX, maxY);
    }

    /**
     * returns the arrow's start point
     *
     * @return
     */
    public Point2D getLineStart() {
        return lineStart;
    }

    /**
     * returns the arrow's end point
     *
     * @return
     */
    public Point2D getLineEnd() {
        return lineEnd;
    }

    /**
     * returns the arrow head's upper line
     *
     * @return
     */
    public Line getArrowTop() {
        return arrowTop;
    }

    /**
     * returns the arrow head's bottom line
     *
     * @return
     */
    public Line getArrowBottom() {
        return arrowBottom;
    }
}
