package figures.Drawer;

import java.awt.*;
import java.awt.geom.Point2D;

public class LabelDrawer implements Drawer {

    /**
     * default constructor
     */
    public LabelDrawer() {
    }

    /**
     * @param graphics object used to draw on the program's window
     * @param start    point slightly to the top-left of the label string
     * @param end      null in this implementation
     * @param label    string to be drawn
     */
    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label, int minX, int minY, int maxX, int maxY) {
        double x1 = start.getX(), y1 = start.getY(), stringPixelEstimate = 50;
        if ((label.length()) * 5 > stringPixelEstimate) {
            stringPixelEstimate = (label.length()) * 5;
        }
        if (x1 > minX && y1 > minY && x1 + stringPixelEstimate < maxX && y1 < maxY) {
            graphics.setColor(Color.WHITE);
            graphics.fillRect((int) x1, (int) y1, 50, 15);
            graphics.setColor(Color.BLACK);
            graphics.drawString(label, (int) start.getX() + 3, (int) start.getY() + 10);
        }
    }
}
