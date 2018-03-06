package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.MessageDrawer;
import figures.diagramFigures.Arrow;

import java.awt.*;
import java.awt.geom.Point2D;

public class CommunicationInvokeMessageDrawer extends MessageDrawer {
    public CommunicationInvokeMessageDrawer() {

    }

    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        //TODO enkel invokes?
        new Arrow(start, end).draw(graphics);
    }
}
