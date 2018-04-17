package figures.basicShapes;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * a class used to draw a dashed line
 */
public class DashedLine extends Line {

    private double lengthLeft;

    /**
     * @param x1 the x-coordinate of the line's start point
     * @param y1 the y-coordinate of the line's start point
     * @param x2 the x-coordinate of the line's end point
     * @param y2 the y-coordinate of the line's end point
     */
    public DashedLine(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    /**
     * @param p1 the line's start point
     * @param p2 the line's end point
     */
    public DashedLine(Point2D p1, Point2D p2) {
        super(p1, p2);
    }

    /**
     * method dat updates the length left to the end point, used to calculate the dashes to be drawn
     *
     * @param currentX
     * @param currentY
     */
    private void updateLength(double currentX, double currentY, double endX, double endY) {
        lengthLeft = Math.sqrt(Math.pow((endX - currentX), 2) + Math.pow((endY - currentY), 2));
    }

    /**
     * a draw fucntion that draws on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
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

        updateLength(x1, y1, x2, y2);

        boolean toggleDraw = true;
        final int dashLen = 5;

        double currentX = x1;
        double currentY = y1;
        double nextX;
        double nextY;

        double temp;

        while (lengthLeft >= 5) {

            temp = dashLen / lengthLeft;

            nextX = (1 - temp) * currentX + temp * x2;
            nextY = (1 - temp) * currentY + temp * y2;

            if (toggleDraw) {
                graphics.drawLine((int) currentX, (int) currentY, (int) nextX, (int) nextY);
                toggleDraw = false;
            } else {
                toggleDraw = true;
            }

            currentX = nextX;
            currentY = nextY;
            updateLength(nextX, nextY, x2, y2);
        }
    }
}
