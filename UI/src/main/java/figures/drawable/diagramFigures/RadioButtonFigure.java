package figures.drawable.diagramFigures;

import figures.drawable.IDrawable;
import figures.drawable.basicShapes.Circle;
import window.elements.RadioButton;

import java.awt.*;
import java.awt.geom.Point2D;

public class RadioButtonFigure implements IDrawable {

    private RadioButton rb;
    private String title;
    protected Point2D absolutePosition;

    public RadioButtonFigure(RadioButton rb, Point2D absolutePosition, String title) {
        this.absolutePosition = absolutePosition;
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
        Point2D textPosition = new Point2D.Double(absolutePosition.getX() + RadioButton.WIDTH + 3, absolutePosition.getY());

        drawTitle(graphics, textPosition);
        drawCircle(graphics, absolutePosition);
    }

    /**
     * a draw function that draws the circle of a radio button on the Graphics parameter object
     *
     * @param graphics       object used to draw on the program's window
     * @param circlePosition coordinate of the top left point of this button
     */
    protected void drawCircle(Graphics graphics, Point2D circlePosition) {
        graphics.drawOval((int)circlePosition.getX(), (int)circlePosition.getY(),RadioButton.WIDTH, RadioButton.HEIGHT);
        //new Circle(circlePosition, RadioButton.HEIGHT).draw(graphics);
    }

    /**
     * a draw function that draws a the title of a radio button on the Graphics parameter object
     *
     * @param graphics      object used to draw on the program's window
     * @param titlePosition coordinate of the top left point of this button
     */
    private void drawTitle(Graphics graphics, Point2D titlePosition) {
        graphics.drawString(title, (int) titlePosition.getX() + 3, (int) titlePosition.getY() + 15);
    }
}
