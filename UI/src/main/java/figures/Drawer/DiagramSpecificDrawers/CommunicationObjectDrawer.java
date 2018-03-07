package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.PartyDrawer;
import figures.diagramFigures.Box;

import java.awt.*;
import java.awt.geom.Point2D;

public class CommunicationObjectDrawer extends PartyDrawer {
    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        new Box(start, end).draw(graphics);
    }
}
