package figures.diagramFigures;

import figures.basicShapes.CloseButton;
import figures.basicShapes.Line;
import figures.basicShapes.Rectangle;

import java.awt.*;
import java.awt.geom.Point2D;

public class SubwindowFigure extends Figure {

    private int x1, y1, x2, y2;

    public SubwindowFigure(Point2D start, Point2D end) {
        x1 = (int) start.getX();
        y1 = (int) start.getY();
        x2 = (int) end.getX();
        y2 = (int) end.getY();
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(x1, y1, x2 - x1, y2 - y1);
        graphics.setColor(Color.BLACK);
        new Rectangle(x1, y1, x2 - x1, y2 - y1);
        CloseButton closeButton = new CloseButton(x1 + x2-x1, y1);
        new Line(x1, y1 + closeButton.getHeight(), x1 + x2-x1, y1 + y2-y1);
    }
}
