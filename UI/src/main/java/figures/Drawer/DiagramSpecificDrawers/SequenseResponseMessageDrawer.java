package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.Drawer;
import figures.diagramFigures.DashedArrow;

import java.awt.*;
import java.awt.geom.Point2D;

public class SequenseResponseMessageDrawer implements Drawer{
    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        new DashedArrow(start, end).draw(graphics);
    }
}
