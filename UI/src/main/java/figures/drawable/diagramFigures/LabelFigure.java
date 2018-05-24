package figures.drawable.diagramFigures;

import figures.drawable.IDrawable;

import java.awt.*;
import java.awt.geom.Point2D;

public class LabelFigure implements IDrawable {

    private Point2D start;
    private String labelText;
    public LabelFigure(Point2D start, String labelText){
        this.labelText = labelText;
        this.start = start;
    }

    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        double x1 = start.getX(), y1 = start.getY(), stringPixelEstimate = 50;
        if ((labelText.length()) * 5 > stringPixelEstimate) {
            stringPixelEstimate = (labelText.length()) * 5;
        }
        if (x1 > minX && y1 > minY && x1 + stringPixelEstimate < maxX && y1 < maxY) {
            Color c = graphics.getColor();
            graphics.setColor(Color.WHITE);
            graphics.fillRect((int) x1, (int) y1, 50, 15);
            graphics.setColor(c);
            graphics.drawString(labelText, (int) start.getX() + 3, (int) start.getY() + 10);
        }
    }
}
