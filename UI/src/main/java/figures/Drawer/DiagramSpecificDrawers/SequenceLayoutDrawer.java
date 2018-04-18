package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.Drawer;
import figures.basicShapes.DashedLine;

import java.awt.*;
import java.awt.geom.Point2D;

public class SequenceLayoutDrawer implements Drawer {
    /**
     *
     * @param graphics
     *      object used to draw on the program's window
     * @param start
     *      null in this implementation
     * @param end
     *      null in this implementation
     * @param label
     *      empty in this implementation
     */
    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label, int minX, int minY, int maxX, int maxY) {
        graphics.setColor(Color.LIGHT_GRAY);
        new DashedLine(0,45,600,45).draw(graphics,minX,minY,maxX,maxY);
        new DashedLine(0,105,600,105).draw(graphics,minX,minY,maxX,maxY);
        graphics.setColor(Color.BLACK);
    }
}
