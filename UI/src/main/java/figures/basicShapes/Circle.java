package figures.basicShapes;

import java.awt.*;
import java.awt.geom.Point2D;

public class Circle extends Shape{

    private Point2D center;
    private int radius;

    public Circle(int x, int y, int radius){
        center = new Point2D.Double(x-radius, y-radius);
        this.radius = radius*2;
    }

    public Circle(Point2D center, int radius){
        this((int)center.getX(), (int)center.getY(), radius);
    }

    public Point2D getCenter(){
        return this.center;
    }

    public int getRadius (){
        return this.radius;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawOval((int)center.getX(), (int)center.getY(), radius, radius);
    }
}
