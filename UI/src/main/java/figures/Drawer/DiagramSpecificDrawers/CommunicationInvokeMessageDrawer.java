package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.Drawer;
import figures.diagramFigures.Arrow;

import java.awt.*;
import java.awt.geom.Point2D;

public class CommunicationInvokeMessageDrawer implements Drawer{
    public CommunicationInvokeMessageDrawer() {

    }

    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        new Arrow(start, end).draw(graphics);
    }
}
