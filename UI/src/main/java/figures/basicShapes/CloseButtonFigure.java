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

    /**
     * @return the hight of the close button
     */
    public int getHeight(){
        return height;
    }

    /**
     * a draw fucntion that draws on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     * @param minX minimum possible x coördinate value
     * @param minY minimum possible y coördinate value
     * @param maxX maximum possible x coördinate value
     * @param maxY maximum possible y coördinate value
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        new Rectangle(x-width, y, width, height).draw(graphics,minX,minY,maxX,maxY);
        new Line(x-(int)Math.floor(width/3),y+(int)Math.floor(height/4),x-((int)Math.floor(width/3)*2),y+((int)Math.floor(height/4)*3)).draw(graphics,minX,minY,maxX,maxY);
        new Line(x-((int)Math.floor(width/3)*2),y+((int)Math.floor(height/4)),x-(int)Math.floor(width/3),y+((int)Math.floor(height/4)*3)).draw(graphics,minX,minY,maxX,maxY);
    }

    /**
     * @return the x-coordinate of the top-right corner of the subwindow
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return the y-coordinate of the top-right corner of the subwindow
     */
    public int getY() {
        return this.y;
    }
}
