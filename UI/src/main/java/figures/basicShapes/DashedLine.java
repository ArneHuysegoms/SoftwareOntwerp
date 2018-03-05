package figures.basicShapes;

import java.awt.*;
import java.awt.geom.Point2D;

public class DashedLine extends Line {

    private double lengthLeft;

    public DashedLine(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
        updateLength(x1, y1);
    }

    public DashedLine(Point2D p1, Point2D p2) {
        super(p1, p2);
        updateLength(p1.getX(), p1.getY());
    }

    private void updateLength(double currentX, double currentY) {
        lengthLeft = Math.sqrt(Math.pow((end.getX() - currentX), 2) + Math.pow((end.getY() - currentY), 2));
    }

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
