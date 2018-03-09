package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.Drawer;
import figures.basicShapes.DashedLine;

import java.awt.*;
import java.awt.geom.Point2D;

public class SequenceLifelineDrawer implements Drawer {
    /**
     *
     * @param graphics
     *      object used to draw on the program's window
     * @param start
     *      start point of the lifeline
     * @param end
     *      end point of the lifeline
     * @param label
     *      empty in this implementation
     */
    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        graphics.setColor(Color.GRAY);
        new DashedLine(start,end).draw(graphics);
        graphics.setColor(Color.BLACK);
    }
}
