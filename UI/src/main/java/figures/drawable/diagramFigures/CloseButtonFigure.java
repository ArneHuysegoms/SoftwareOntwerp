package figures.drawable.diagramFigures;

import figures.drawable.IDrawable;
import figures.drawable.basicShapes.Line;
import figures.drawable.basicShapes.Rectangle;

import java.awt.*;
import java.awt.geom.Point2D;

public class CloseButtonFigure implements IDrawable {

    private int x, y, width, height;

    /**
     * @param position Top-left point of the closebutton
     * @param width    the button's width
     * @param height   the button's height
     */
    public CloseButtonFigure(Point2D position, int width, int height) {
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
     * a draw method that draws a close button on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        drawAndPaintBox(graphics);
        new Line(x + (int) Math.floor(width / 3), y + (int) Math.floor(height / 4), x + ((int) Math.floor(width / 3) * 2), y + ((int) Math.floor(height / 4) * 3))
                .draw(graphics, minX, minY, maxX, maxY);
        new Line(x + ((int) Math.floor(width / 3) * 2), y + ((int) Math.floor(height / 4)), x + (int) Math.floor(width / 3), y + ((int) Math.floor(height / 4) * 3))
                .draw(graphics, minX, minY, maxX, maxY);
    }

    /**
     * method that draws the framed colored square of a close button
     *
     * @param graphics object used to draw on the program's window
     */
    private void drawAndPaintBox(Graphics graphics) {
        Color temp = graphics.getColor();
        graphics.setColor(new Color(221, 82, 82));
        graphics.fillRect(x, y, width, height);
        graphics.setColor(Color.BLACK);

        new Rectangle(x, y, width, height)
                .draw(graphics);
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
