package figures.drawable.diagramFigures;

import figures.drawable.basicShapes.DashedLine;

import java.awt.*;
import java.awt.geom.Point2D;

public class LifeLineFigure extends DashedLine{

    public LifeLineFigure(Point2D p1, Point2D p2) {
        super(p1, p2);
    }

    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        graphics.setColor(Color.GRAY);
        super.draw(graphics,minX,minY,maxX,maxY);
        graphics.setColor(Color.BLACK);
    }
}
