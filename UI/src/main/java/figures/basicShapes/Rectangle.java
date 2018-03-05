package figures.basicShapes;

import java.awt.*;
import java.awt.geom.Point2D;

public class Rectangle extends Shape {

    protected Point2D positionTL;
    protected Point2D cornerTR;
    protected Point2D cornerBR;
    protected Point2D cornerBL;

    public Rectangle(int x, int y, int width, int length) {
        this.positionTL = new Point2D.Double(x, y);
        this.cornerTR = new Point2D.Double(x+width, y);
        this.cornerBR = new Point2D.Double(x+width, y+length);
        this.cornerBL = new Point2D.Double(x, y+length);
    }

    public Rectangle(Point2D tl, Point2D br){
        this.positionTL = tl;
        this.cornerBR = br;
        this.cornerBL = new Point2D.Double(tl.getX(), br.getY());
        this.cornerTR = new Point2D.Double(br.getX(), tl.getY());
    }

    public Point2D getPosition() {
        return positionTL;
    }

    @Override
    public void draw(Graphics graphics) {
        new Line(positionTL, cornerTR).draw(graphics);
        new Line(cornerTR, cornerBR).draw(graphics);
        new Line(cornerBR, cornerBL).draw(graphics);
        new Line(cornerBL, positionTL).draw(graphics);
    }
}
