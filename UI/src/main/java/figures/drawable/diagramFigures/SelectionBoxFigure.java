package figures.drawable.diagramFigures;

import figures.drawable.basicShapes.Box;

import java.awt.*;
import java.awt.geom.Point2D;

public class SelectionBoxFigure extends Box {

    public SelectionBoxFigure(Point2D start, Point2D end) {
        super(start, end);
    }

    /**
     * a draw function that draws a selection box on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        Color temp = graphics.getColor();
        graphics.setColor(Color.RED);
        super.draw(graphics, minX, minY, maxX, maxY);
        graphics.setColor(temp);
    }
}
