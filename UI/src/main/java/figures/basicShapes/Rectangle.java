package figures.basicShapes;

import java.awt.*;
import java.awt.geom.Point2D;

public class Rectangle extends Shape {

    protected Point2D positionTL;
    protected Point2D cornerTR;
    protected Point2D cornerBR;
    protected Point2D cornerBL;

    public Rectangle(int x, int y, int width, int length) {
        setPositionTL(new Point2D.Double(x, y));
        setCornerTR(new Point2D.Double(x+width, y));
        setCornerBR(new Point2D.Double(x+width, y+length));
        setCornerBL(new Point2D.Double(x, y+length));
    }

    public Rectangle(Point2D tl, Point2D br){
        setPositionTL(tl);
        setCornerBR(br);
        setCornerBL(new Point2D.Double(tl.getX(), br.getY()));
        setCornerTR(new Point2D.Double(br.getX(), tl.getY()));
    }

    public Point2D getPositionTL() {
        return positionTL;
    }

    public void setPositionTL(Point2D positionTL) {
        this.positionTL = positionTL;
    }

    public Point2D getCornerTR() {
        return cornerTR;
    }

    public void setCornerTR(Point2D cornerTR) {
        this.cornerTR = cornerTR;
    }

    public Point2D getCornerBR() {
        return cornerBR;
    }

    public void setCornerBR(Point2D cornerBR) {
        this.cornerBR = cornerBR;
    }

    public Point2D getCornerBL() {
        return cornerBL;
    }

    public void setCornerBL(Point2D cornerBL) {
        this.cornerBL = cornerBL;
    }

    @Override
    public void draw(Graphics graphics) {
        new Line(positionTL, cornerTR).draw(graphics);
        new Line(cornerTR, cornerBR).draw(graphics);
        new Line(cornerBR, cornerBL).draw(graphics);
        new Line(cornerBL, positionTL).draw(graphics);
    }
}
