package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.MessageDrawer;
import figures.diagramFigures.Arrow;

import java.awt.*;
import java.awt.geom.Point2D;

public class SequenceMessageDrawer extends MessageDrawer{

    public SequenceMessageDrawer(){

    }

    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        Arrow a = new Arrow(start, end);
        a.draw(graphics);
    }
}
