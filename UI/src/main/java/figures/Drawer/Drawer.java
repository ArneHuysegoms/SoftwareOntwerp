package figures.Drawer;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Interface that will be implemented by different classes with different drawing goals
 */
public interface Drawer {
    public void draw(Graphics graphics, Point2D start, Point2D end, String label);

}
