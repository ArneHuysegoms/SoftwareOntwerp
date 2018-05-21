package figures.drawable.basicShapes;

import figures.drawable.IDrawable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.awt.geom.Point2D;

public class FilledCircle implements IDrawable {

    private Point2D upperLeft;
    private double radius;
    private double width;

    /**
     * @param x      the x-coordinate of the circle's upperLeft point
     * @param y      the y-coordinate of the circle's upperLeft point
     * @param radius the circles radius
     */
    public FilledCircle(int x, int y, int radius) {
        upperLeft = new Point2D.Double(x - radius, y - radius);
        this.radius = radius;
        this.width = radius * 2;
    }

    /**
     * @param upperLeft the circle's upperLeft point
     * @param radius the circle's radius
     */
    public FilledCircle(Point2D upperLeft, int radius) {
        this((int) upperLeft.getX(), (int) upperLeft.getY(), radius);
    }

    /**
     * @return returns the upperLeft point of the circle
     */
    public Point2D getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return returns the circle's radius
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * @return returns the circle's width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * a draw function that draws a filled circle on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        throw new NotImplementedException();
    }

    /**
     * a draw function that draws a filled circle on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     */
    @Override
    public void draw(Graphics graphics) {
        graphics.fillOval((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) width);
    }
}
