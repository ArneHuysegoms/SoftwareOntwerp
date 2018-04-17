package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.Drawer;
import figures.diagramFigures.Box;

import java.awt.*;
import java.awt.geom.Point2D;

public class CommunicationObjectDrawer implements Drawer {

    /**
     *
     * @param graphics
     *      object used to draw on the program's window
     * @param start
     *      top-left point of the box
     * @param end
     *      bottom-right point of the box
     * @param label
     *      empty in this implementation
     */
    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label, int minX, int minY, int maxX, int maxY) {
        new Box(start, end).draw(graphics,minX,minY,maxX,maxY);
    }
}
