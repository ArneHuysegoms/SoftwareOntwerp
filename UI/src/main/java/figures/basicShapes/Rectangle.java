package figures.basicShapes;

import figures.PointXY;

import java.awt.*;

public class Rectangle extends Shape{

    private PointXY position;
    private int width;
    private int length;

    public Rectangle(int x, int y, int width, int length){
        this.position = new PointXY(x, y);
        this.width = width;
        this.length = length;
    }

    public PointXY getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getLength(){
        return length;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawRect(position.getX(), position.getY(), width, length);
    }
}
