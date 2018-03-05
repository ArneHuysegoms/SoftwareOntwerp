package figures.Drawer;

import java.awt.*;
import java.awt.geom.Point2D;

public interface Drawer {
    public void draw(Graphics graphics, Point2D start, Point2D end, String label);

}
