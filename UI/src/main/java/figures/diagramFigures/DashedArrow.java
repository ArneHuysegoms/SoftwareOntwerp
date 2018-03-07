package figures.diagramFigures;

import figures.basicShapes.DashedLine;

import java.awt.*;
import java.awt.geom.Point2D;

public class DashedArrow extends Arrow {
    public DashedArrow(Point2D start, Point2D end) {
        super(start, end);
    }

    @Override
    public void draw(Graphics graphics) {
        new DashedLine(this.getLineStart(), this.getLineEnd()).draw(graphics);
        this.getArrowTop().draw(graphics);
        this.getArrowBottom().draw(graphics);
    }
}
