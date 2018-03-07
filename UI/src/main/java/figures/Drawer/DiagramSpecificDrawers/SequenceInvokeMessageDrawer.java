package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.MessageDrawer;
import figures.diagramFigures.Arrow;

import java.awt.*;
import java.awt.geom.Point2D;

public class SequenceInvokeMessageDrawer extends MessageDrawer{

    public SequenceInvokeMessageDrawer(){

    }

    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        new Arrow(start, end).draw(graphics);
    }
}
