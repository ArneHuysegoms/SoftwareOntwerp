package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.Drawer;
import figures.diagramFigures.Arrow;

import java.awt.*;
import java.awt.geom.Point2D;

public class CommunicationInvokeMessageDrawer implements Drawer {

    /**
     * default constructor
     */
    public CommunicationInvokeMessageDrawer() {

    }

    /**
     * @param graphics object used to draw on the program's window
     * @param start    start point of the arrow
     * @param end      end point of the arrow
     * @param label    empty in this implementation
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label, int minX, int minY, int maxX, int maxY) {
        new Arrow(start, end).draw(graphics, minX, minY, maxX, maxY);
    }
}
