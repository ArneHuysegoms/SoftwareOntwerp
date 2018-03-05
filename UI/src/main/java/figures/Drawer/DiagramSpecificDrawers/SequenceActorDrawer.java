package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.PartyDrawer;
import figures.basicShapes.DashedLine;
import figures.diagramFigures.StickMan;

import java.awt.*;
import java.awt.geom.Point2D;

public class SequenceActorDrawer extends PartyDrawer {

    public SequenceActorDrawer(){

    }

    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        new StickMan(start).draw(graphics);
        new DashedLine(new Point2D.Double(start.getX(),start.getY()+64), new Point2D.Double(start.getX(),start.getY()+250)).draw(graphics);
    }
}