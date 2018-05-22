package figures.drawable.basicShapes;

import figures.drawable.IDrawable;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * a class used to draw a circle
 */
public class Circle implements IDrawable {

    private Point2D upperLeft;
    private double radius;
    private double width;

    /**
     * @param x      the x-coordinate of the circle's center point
     * @param y      the y-coordinate of the circle's center point
     * @param radius the circles radius
     */
    public Circle(int x, int y, int radius) {
        upperLeft = new Point2D.Double(x - radius, y - radius);
        this.radius = radius;
        this.width = radius * 2;
    }

    /**
     * @param center the circle's center point
     * @param radius the circle's radius
     */
    public Circle(Point2D center, int radius) {
        this((int) center.getX(), (int) center.getY(), radius);
    }

    /**
     * @return returns the center point of the circle
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
     * a draw function that draws a circle on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {

        double startAngle;
        double arcAngle;

        if (upperLeft.getX() < minX && upperLeft.getX() + width <= maxX) {
            if (upperLeft.getY() >= minY && upperLeft.getY() + width <= maxY) {
                startAngle = Math.toDegrees(Math.acos((minX - radius) / radius));
                arcAngle = -(startAngle * 2);
                graphics.drawArc((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) width, (int) Math.round(startAngle), (int) Math.round(arcAngle));
            }
        } else if (upperLeft.getX() >= minX && upperLeft.getX() + width > maxX) {
            if (upperLeft.getY() >= minY && upperLeft.getY() + width <= maxY) {
                startAngle = Math.toDegrees(Math.acos((maxX - radius) / radius));
                arcAngle = (360 - (startAngle * 2));
                graphics.drawArc((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) width, (int) Math.round(startAngle), (int) Math.round(arcAngle));
            }
        } else if (upperLeft.getY() < minY && upperLeft.getY() + width <= maxY) {
            if (upperLeft.getX() >= minX && upperLeft.getX() + width <= maxX) {
                startAngle = Math.toDegrees(Math.asin((radius - minY) / radius));
                arcAngle = -(360 - ((90 - startAngle) * 2));
                graphics.drawArc((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) width, (int) Math.round(startAngle), (int) Math.round(arcAngle));
            }
        } else if (upperLeft.getY() >= minY && upperLeft.getY() + width > maxY) {
            if (upperLeft.getX() >= minX && upperLeft.getX() + width <= maxX) {
                startAngle = Math.toDegrees(Math.asin((radius - maxY) / radius));
                arcAngle = (360 - ((90 + startAngle) * 2));
                graphics.drawArc((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) width, (int) Math.round(startAngle), (int) Math.round(arcAngle));
            }
        } else if (upperLeft.getX() >= minX && upperLeft.getX() + width <= maxX && upperLeft.getY() >= minY && upperLeft.getY() + width <= maxY) {
            graphics.drawOval((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) width);
        }

    }

    /**
     * a draw function that draws a circle on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     */
    @Override
    public void draw(Graphics graphics) {
        graphics.drawOval((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) width);
    }
}
