package figures.drawable.diagramFigures;

import figures.drawable.basicShapes.Rectangle;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * a class used to draw a rectangle box
 */
public class Box extends Figure {

    private Point2D tl, br;

    /**
     * @param start the box's top-left point
     * @param end   the box's bottom-right point
     */
    public Box(Point2D start, Point2D end) {
        setTl(start);
        setBr(end);
    }

    /**
     * retuns the box's top-left point
     *
     * @return the box's top-left point
     */
    public Point2D getTl() {
        return tl;
    }

    /**
     * sets the box's top-left point
     *
     * @param tl the box's top-left point
     */
    public void setTl(Point2D tl) {
        this.tl = tl;
    }

    /**
     * return the box's bottom-right point
     *
     * @return the box's bottom-right point
     */
    public Point2D getBr() {
        return br;
    }

    /**
     * sets the box's bottom-right point
     *
     * @param br the box's bottom-right point
     */
    public void setBr(Point2D br) {
        this.br = br;
    }

    /**
     * a draw fucntion that draws on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        new Rectangle(this.getTl(), this.getBr()).draw(graphics, minX, minY, maxX, maxY);
    }
}
