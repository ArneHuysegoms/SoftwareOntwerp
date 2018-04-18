package figures.basicShapes;

import java.awt.*;

public class CloseButtonFigure extends Shape{

    private int x,y;
    private final int width = 30,height = 30;

    /**
     * @param x
     *      the x-coordinate of the top-right corner of the subwindow
     * @param y
     *      the y-coordinate of the top-right corner of the subwindow
     */
    public CloseButtonFigure(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getHeight(){
        return height;
    }

    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        new Rectangle(x-width, y, width, height).draw(graphics,minX,minY,maxX,maxY);
        new Line(x-(int)Math.floor(width/3),y+(int)Math.floor(height/4),x-((int)Math.floor(width/3)*2),y+((int)Math.floor(height/4)*3)).draw(graphics,minX,minY,maxX,maxY);
        new Line(x-((int)Math.floor(width/3)*2),y+((int)Math.floor(height/4)),x-(int)Math.floor(width/3),y+((int)Math.floor(height/4)*3)).draw(graphics,minX,minY,maxX,maxY);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
