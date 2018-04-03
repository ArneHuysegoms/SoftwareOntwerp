package figures.Drawer;

import java.awt.*;
import java.awt.geom.Point2D;

public class LabelDrawer implements Drawer{

    /**
     * default constructor
     */
    public LabelDrawer(){
    }

    /**
     *
     * @param graphics
     *      object used to draw on the program's window
     * @param start
     *      point slightly to the top-left of the label string
     * @param end
     *      null in this implementation
     * @param label
     *      string to be drawn
     */
    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        graphics.drawString(label, (int)start.getX()+3, (int)start.getY()+10);
    }
}
