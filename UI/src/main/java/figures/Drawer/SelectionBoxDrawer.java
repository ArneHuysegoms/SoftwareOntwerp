package figures.Drawer;

import figures.diagramFigures.Box;

import java.awt.*;
import java.awt.geom.Point2D;

public class SelectionBoxDrawer implements Drawer {
    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        graphics.setColor(Color.RED);
        new Box(start, end).draw(graphics);
        graphics.setColor(Color.BLACK);
    }
}
