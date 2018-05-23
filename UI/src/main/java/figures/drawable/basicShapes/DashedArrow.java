package figures.drawable.basicShapes;

import figures.drawable.basicShapes.Arrow;
import figures.drawable.basicShapes.DashedLine;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * a class used to draw a dashed arrow
 */
public class DashedArrow extends Arrow {
    /**
     * @param start the arrow's start point
     * @param end   the arrow's end point
     */
    public DashedArrow(Point2D start, Point2D end) {
        super(start, end);
    }

    /**
     * a draw function that draws a dashed arrow on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        new DashedLine(this.getLineStart(), this.getLineEnd()).draw(graphics, minX, minY, maxX, maxY);
        this.getArrowTop().draw(graphics, minX, minY, maxX, maxY);
        this.getArrowBottom().draw(graphics, minX, minY, maxX, maxY);
    }
}
