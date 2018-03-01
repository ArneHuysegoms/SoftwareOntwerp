package figures.basicShapes;

import figures.PointXY;

import java.awt.*;

public class Circle extends Shape{

    private PointXY center;
    private int radius;

    public Circle(int x, int y, int radius){
        center = new PointXY(x-radius, y-radius);
        this.radius = radius*2;
    }

    public Circle(PointXY center, int radius){
        this(center.getX(), center.getY(), radius);
    }

    public PointXY getCenter(){
        return this.center;
    }

    public int getRadius (){
        return this.radius;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawOval(center.getX(), center.getY(), radius, radius);
    }
}
