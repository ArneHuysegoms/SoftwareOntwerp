package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.Drawer;
import figures.diagramFigures.StickMan;

import java.awt.*;
import java.awt.geom.Point2D;

public class SequenceActorDrawer implements Drawer {

    /**
     * default constructor
     */
    public SequenceActorDrawer(){

    }

    /**
     *
     * @param graphics
     *      object used to draw on the program's window
     * @param start
     *      point at the top of the stickmans head
     * @param end
     *      null in this implementation
     * @param label
     *      empty in this implementation
     */
    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label, int minX, int minY, int maxX, int maxY) {
        new StickMan(start).draw(graphics,minX,minY,maxX,maxY);
    }
}