package figures.Drawer;

import figures.diagramFigures.SubwindowFigure;

import java.awt.*;
import java.awt.geom.Point2D;

public class SubwindowDrawer implements Drawer {

    /**
     * default constructor
     */
    public SubwindowDrawer() {
    }

    /**
     * @param graphics object used to draw on the program's window
     * @param start    top-left point of the subwindow
     * @param end      bottom-right point of the subwindow
     * @param label    null in this implementation
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label, int minX, int minY, int maxX, int maxY) {
        new SubwindowFigure(start, end).draw(graphics, minX, minY, maxX, maxY);
    }
}
