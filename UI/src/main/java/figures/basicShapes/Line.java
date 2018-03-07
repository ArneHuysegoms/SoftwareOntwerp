package figures.basicShapes;

import java.awt.*;
import java.awt.geom.Point2D;

public class Line extends Shape {

    protected Point2D start;
    protected Point2D end;

    public Line(int x1, int y1, int x2, int y2) {
        start = new Point2D.Double(x1, y1);
        end = new Point2D.Double(x2, y2);
    }

    public Line(Point2D p1, Point2D p2) {
        start = p1;
        end = p2;
    }

    public Point2D getStart() {
        return start;
    }

    public Point2D getEnd() {
        return end;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
    }

}
