package figures.diagramFigures;

import figures.basicShapes.DashedLine;

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
     * a draw fucntion that draws on the Graphics parameter object
     * @param graphics
     *      object used to draw on the program's window
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        new DashedLine(this.getLineStart(), this.getLineEnd()).draw(graphics,minX,minY,maxX,maxY);
        this.getArrowTop().draw(graphics,minX,minY,maxX,maxY);
        this.getArrowBottom().draw(graphics,minX,minY,maxX,maxY);
    }
}
