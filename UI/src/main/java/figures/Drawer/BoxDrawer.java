package figures.Drawer;

import figures.diagramFigures.Box;

import java.awt.*;
import java.awt.geom.Point2D;

public class BoxDrawer implements Drawer{

    public BoxDrawer(){
    }

    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        new Box(start, end).draw(graphics);
    }
}
