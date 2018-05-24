package figures.drawable.diagramFigures;

import figures.drawable.IDrawable;
import figures.drawable.basicShapes.Rectangle;

import java.awt.*;
import java.awt.geom.Point2D;

public class AddButtonFigure implements IDrawable {

    private int x,y,width,height;

    /**
     *
     * @param position  the button's top-left point
     * @param width the button's top-left width
     * @param height the button's top-left height
     */
    public AddButtonFigure(Point2D position, int width, int height){
        this.x = (int)position.getX();
        this.y = (int)position.getY();
        System.out.println("x dan y: "+ x + " "+ y);
        this.width = width;
        this.height = height;
    }

    /**
     * @return the height of the close button
     */
    public int getHeight(){
        return height;
    }

    /**
     * a draw function that draws a button on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     * @param minX minimum possible x coördinate value
     * @param minY minimum possible y coördinate value
     * @param maxX maximum possible x coördinate value
     * @param maxY maximum possible y coördinate value
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        new Rectangle(x, y, width, height).draw(graphics,minX,minY,maxX,maxY);
        drawAddShape(graphics);
    }

    /**
     * draws the button's icon
     *
     * @param graphics object used to draw on the program's window
     */
    private void drawAddShape(Graphics graphics){
        int offsetX = (int)Math.floor(width/5),
                offsetY = (int)Math.floor(height/5);
        Color temp = graphics.getColor();
        graphics.setColor(Color.green);
        graphics.fillRect(offsetX+getX(),(offsetY*2)+getY(),3*offsetX,offsetY);
        graphics.fillRect((offsetX*2)+getX(),(offsetY)+getY(),offsetY, 3*offsetX);
        graphics.setColor(temp);
    }

    /**
     * @return the x-coordinate of the top-right corner of the window.diagram
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return the y-coordinate of the top-right corner of the window.diagram
     */
    public int getY() {
        return this.y;
    }
}
