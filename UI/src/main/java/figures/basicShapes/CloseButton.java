package figures.basicShapes;

import java.awt.*;

public class CloseButton extends Shape{

    private int x,y;
    private final int width = 21,height = 16;

    /**
     * @param x
     *      the x-coordinate of the top-right corner of the subwindow
     * @param y
     *      the y-coordinate of the top-right corner of the subwindow
     */
    public CloseButton(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getHeight(){
        return height;
    }

    @Override
    public void draw(Graphics graphics) {
        new Rectangle(x-width, y, width, height).draw(graphics);
        new Line(x-(int)Math.floor(width/3),y+(int)Math.floor(height/4),x-((int)Math.floor(width/3)*2),y+((int)Math.floor(height/4)*3)).draw(graphics);
        new Line(x-((int)Math.floor(width/3)*2),y+((int)Math.floor(height/4)),x-(int)Math.floor(width/3),y+((int)Math.floor(height/4)*3)).draw(graphics);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
