package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.Drawer;
import figures.basicShapes.DashedLine;

import java.awt.*;
import java.awt.geom.Point2D;

public class SequenceLayoutDrawer implements Drawer {
    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        graphics.setColor(Color.LIGHT_GRAY);
        new DashedLine(0,45,600,45).draw(graphics);
        new DashedLine(0,105,600,105).draw(graphics);
        graphics.setColor(Color.BLACK);
    }
}
