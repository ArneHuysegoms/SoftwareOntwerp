package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.PartyDrawer;
import figures.basicShapes.DashedLine;
import figures.diagramFigures.Box;

import java.awt.*;
import java.awt.geom.Point2D;

public class SequenceObjectDrawer extends PartyDrawer {
    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        new Box(start, end).draw(graphics);
        new DashedLine(new Point2D.Double(start.getX()+40,start.getY()+50), new Point2D.Double(start.getX()+40,start.getY()+250)).draw(graphics);
    }
}
