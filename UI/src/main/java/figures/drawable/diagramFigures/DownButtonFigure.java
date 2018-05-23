package figures.drawable.diagramFigures;

import figures.drawable.IDrawable;
import figures.drawable.basicShapes.Line;
import figures.drawable.basicShapes.Rectangle;

import java.awt.*;
import java.awt.geom.Point2D;

public class DownButtonFigure implements IDrawable {

    private int x, y, width, height;

    /**
     * @param position
     * @param width
     * @param height
     */
    public DownButtonFigure(Point2D position, int width, int height) {
        this.x = (int) position.getX();
        this.y = (int) position.getY();
        this.width = width;
        this.height = height;
    }

    /**
     * @return the hight of the close button
     */
    public int getHeight() {
        return height;
    }

    /**
     * a draw function that draws a button on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        new Rectangle(x, y, width, height).draw(graphics, minX, minY, maxX, maxY);
        drawUpShape(graphics);
    }

    /**
     * draw the button's icon
     * @param graphics object used to draw on the program's window
     */
    private void drawUpShape(Graphics graphics) {
        int offsetCenter = (int) Math.ceil(((double) width) / 2),
                offsetX = (int) Math.floor(width / 5),
                offsetY = (int) Math.floor(height / 5);
        new Line(x+offsetCenter,y+(offsetY*4),x+offsetX,y+(offsetY)).draw(graphics);
        new Line(x+offsetCenter,y+(offsetY*4),x+(offsetX*4),y+(offsetY)).draw(graphics);
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