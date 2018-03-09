package figures.basicShapes;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * a class used to draw a dashed line
 */
public class DashedLine extends Line {

    private double lengthLeft;

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
    public DashedLine(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
        updateLength(x1, y1);
    }

    /**
     *
     * @param p1
     *      the line's start point
     * @param p2
     *      the line's end point
     */
    public DashedLine(Point2D p1, Point2D p2) {
        super(p1, p2);
        updateLength(p1.getX(), p1.getY());
    }

    /**
     * method dat updates the length left to the end point, used to calculate the dashes to be drawn
     * @param currentX
     * @param currentY
     */
    private void updateLength(double currentX, double currentY) {
        lengthLeft = Math.sqrt(Math.pow((end.getX() - currentX), 2) + Math.pow((end.getY() - currentY), 2));
    }

    /**
     * a draw fucntion that draws on the Graphics parameter object
     * @param graphics
     *      object used to draw on the program's window
     */
    @Override
    public void draw(Graphics graphics) {
        boolean toggleDraw = true;
        final int dashLen = 5;

        double currentX = start.getX();
        double currentY = start.getY();
        double nextX;
        double nextY;

        double temp;

        while (lengthLeft >= 5) {

            temp = dashLen / lengthLeft;

            nextX = (1 - temp) * currentX + temp * end.getX();
            nextY = (1 - temp) * currentY + temp * end.getY();

            if (toggleDraw) {
                graphics.drawLine((int) currentX, (int) currentY, (int) nextX, (int) nextY);
                toggleDraw = false;
            } else {
                toggleDraw = true;
            }

            currentX = nextX;
            currentY = nextY;
            updateLength(nextX, nextY);
        }
    }
}
