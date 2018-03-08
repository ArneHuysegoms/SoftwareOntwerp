package figures.diagramFigures;

import figures.basicShapes.Rectangle;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * a class used to draw a rectangle box
 */
public class Box extends Figure {

    private Point2D tl, br;
    public Box(Point2D start, Point2D end) {
        setTl(start);
        setBr(end);
    }

    public Point2D getTl() {
        return tl;
    }

    public void setTl(Point2D tl) {
        this.tl = tl;
    }

    public Point2D getBr() {
        return br;
    }

    public void setBr(Point2D br) {
        this.br = br;
    }

    @Override
    public void draw(Graphics graphics) {
        new Rectangle(this.getTl(),this.getBr()).draw(graphics);
    }
}
