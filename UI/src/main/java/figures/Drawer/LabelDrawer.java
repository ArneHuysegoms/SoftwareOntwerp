package figures.Drawer;

import canvascomponents.diagram.Diagram;
import figures.diagramFigures.Box;

import java.awt.*;
import java.awt.geom.Point2D;

public class LabelDrawer implements Drawer{

    public LabelDrawer(){
    }

    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        new Box(start, end).draw(graphics);
        graphics.drawString(label, (int)start.getX()+3, (int)start.getY()+10);
    }
}
