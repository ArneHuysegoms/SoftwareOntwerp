package figures.drawable.diagramFigures;

import figures.drawable.IDrawable;
import figures.drawable.basicShapes.Circle;
import window.elements.RadioButton;

import java.awt.*;
import java.awt.geom.Point2D;

public class RadioButtonFigure implements IDrawable {

    private RadioButton rb;
    private String title;

    public RadioButtonFigure(RadioButton rb, String title) {
        this.rb = rb;
        this.title = title;
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
        //TODO might have to tweek coordinates
        Point2D circlePosition = rb.getCoordinate();
        Point2D textPosition = new Point2D.Double(circlePosition.getX() + RadioButton.WIDTH + 3, circlePosition.getY());

        drawTitle(graphics, textPosition);
        drawCircle(graphics, circlePosition, minX, minY, maxX, maxY);
    }

    /**
     * a draw function that draws a button on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     */
    @Override
    public void draw(Graphics graphics) {
        //TODO might have to tweek coordinates
        Point2D circlePosition = rb.getCoordinate();
        Point2D textPosition = new Point2D.Double(circlePosition.getX() + RadioButton.WIDTH + 3, circlePosition.getY());

        drawTitle(graphics, textPosition);
        drawCircle(graphics, circlePosition);
    }

    /**
     * a draw function that draws a the circle of a radio button on the Graphics parameter object
     *
     * @param graphics       object used to draw on the program's window
     * @param circlePosition coordinate of the top left point of this button
     * @param minX           minimum possible x coördinate value
     * @param minY           minimum possible y coördinate value
     * @param maxX           maximum possible x coördinate value
     * @param maxY           maximum possible y coördinate value
     */
    protected void drawCircle(Graphics graphics, Point2D circlePosition, int minX, int minY, int maxX, int maxY) {
        new Circle(circlePosition, RadioButton.HEIGHT).draw(graphics, minX, minY, maxX, maxY);
    }

    /**
     * a draw function that draws the circle of a radio button on the Graphics parameter object
     *
     * @param graphics       object used to draw on the program's window
     * @param circlePosition coordinate of the top left point of this button
     */
    protected void drawCircle(Graphics graphics, Point2D circlePosition) {
        new Circle(circlePosition, RadioButton.HEIGHT).draw(graphics);
    }

    /**
     * a draw function that draws a the title of a radio button on the Graphics parameter object
     *
     * @param graphics      object used to draw on the program's window
     * @param titlePosition coordinate of the top left point of this button
     */
    private void drawTitle(Graphics graphics, Point2D titlePosition) {
        graphics.drawString(title, (int) titlePosition.getX(), (int) titlePosition.getY());
    }
}
