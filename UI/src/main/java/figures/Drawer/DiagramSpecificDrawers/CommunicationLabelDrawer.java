package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.Drawer;

import java.awt.*;
import java.awt.geom.Point2D;

public class CommunicationLabelDrawer implements Drawer {

    /**
     *
     * @param graphics
     *      object used to draw on the program's window
     * @param start
     *      point touching the sender object
     * @param end
     *      point touching the receiver object
     * @param label
     *      string to be drawn
     */
    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        double x = Math.round((start.getX() + end.getX()) / 2);
        double y = Math.round((start.getY() + end.getY()) / 2);
        start = new Point2D.Double(x, y);
        graphics.drawString(label, (int)start.getX()+3, (int)start.getY()+10);
    }
}