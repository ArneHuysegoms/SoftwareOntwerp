package figures.diagramFigures;

import figures.basicShapes.DashedLine;

import java.awt.*;
import java.awt.geom.Point2D;

public class Lifeline extends Figure {

    private Point2D top, bottom;

    public Lifeline(Point2D activationBarTop, Point2D activationBarBottom) {
        this.top = new Point2D.Double(activationBarTop.getX(), activationBarTop.getY() - 14);
        this.bottom = new Point2D.Double(activationBarBottom.getX(), activationBarBottom.getY() + 14);
    }

    @Override
    public void draw(Graphics graphics) {
        new DashedLine(top, bottom).draw(graphics);
    }
}
