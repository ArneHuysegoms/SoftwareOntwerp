package figures.basicShapes;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * a class used to draw a circle
 */
public class Circle extends Shape{

    private Point2D center;
    private int radius;

    /**
     *
     * @param x
     *      the x-coordinate of the circle's center point
     * @param y
     *      the y-coordinate of the circle's center point
     * @param radius
     *      the circles radius
     */
    public Circle(int x, int y, int radius){
        center = new Point2D.Double(x-radius, y-radius);
        this.radius = radius*2;
    }

    /**
     *
     * @param center
     *      the circle's center point
     * @param radius
     *      the circle's radius
     */
    public Circle(Point2D center, int radius){
        this((int)center.getX(), (int)center.getY(), radius);
    }

    /**
     *
     * @return
     *      returns the center point of the circle
     */
    public Point2D getCenter(){
        return this.center;
    }

    /**
     *
     * @return
     *      returns the circle's radius
     */
    public int getRadius (){
        return this.radius;
    }

    /**
     * a draw fucntion that draws on the Graphics parameter object
     * @param graphics
     *      object used to draw on the program's window
     */
    @Override
    public void draw(Graphics graphics) {
        graphics.drawOval((int)center.getX(), (int)center.getY(), radius, radius);
    }
}
