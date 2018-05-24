package figures.drawable.diagramFigures;

import figures.drawable.IDrawable;

import java.awt.*;
import java.awt.geom.Point2D;

public class LabelFigure implements IDrawable {

    private Point2D start;
    private String labelText;

    /**
     *
     * @param start the top-left point
     * @param labelText text that had to be drawn in the label
     */
    public LabelFigure(Point2D start, String labelText){
        this.labelText = labelText;
        this.start = start;
    }

    /**
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        double stringPixelEstimate = 50;
        if ((labelText.length()) * 5 > stringPixelEstimate) {
            stringPixelEstimate = (labelText.length()) * 5;
        }
        if (start.getX() > minX && start.getY() > minY && start.getX() + stringPixelEstimate < maxX && start.getY() < maxY) {
            Color c = graphics.getColor();
            graphics.setColor(Color.WHITE);
            graphics.fillRect((int) start.getX(), (int) start.getY(), 50, 15);
            graphics.setColor(c);
            graphics.drawString(labelText, (int) start.getX() + 3, (int) start.getY() + 10);
        }
    }
}
