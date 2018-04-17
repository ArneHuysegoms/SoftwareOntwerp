package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.Drawer;
import figures.diagramFigures.DashedArrow;

import java.awt.*;
import java.awt.geom.Point2D;

public class SequenseResponseMessageDrawer implements Drawer{
    /**
     *
     * @param graphics
     *      object used to draw on the program's window
     * @param start
     *      start point of the arrow
     * @param end
     *      end point of the arrow
     * @param label
     *      empty in this implementation
     */
    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label, int minX, int minY, int maxX, int maxY) {
        new DashedArrow(start, end).draw(graphics,minX,minY,maxX,maxY);
    }
}
