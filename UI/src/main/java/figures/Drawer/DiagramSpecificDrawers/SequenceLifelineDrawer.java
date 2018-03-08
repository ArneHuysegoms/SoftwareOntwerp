package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.Drawer;
import figures.basicShapes.DashedLine;

import java.awt.*;
import java.awt.geom.Point2D;

public class SequenceLifelineDrawer implements Drawer {
    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        new DashedLine(start,end).draw(graphics);
    }
}
