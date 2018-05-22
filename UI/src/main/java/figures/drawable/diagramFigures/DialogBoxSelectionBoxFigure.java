package figures.drawable.diagramFigures;

import figures.drawable.basicShapes.Box;

import java.awt.*;
import java.awt.geom.Point2D;

public class DialogBoxSelectionBoxFigure extends Box {

    public DialogBoxSelectionBoxFigure(Point2D start, Point2D end){
        super(start,end);
    }

    /**
     * a draw fucntion that draws a selection box on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        Color temp = graphics.getColor();
        graphics.setColor(Color.CYAN);
        super.draw(graphics,minX,minY,maxX,maxY);
        graphics.setColor(temp);
    }

    /**
     * a draw fucntion that draws a selection box on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     */
    @Override
    public void draw(Graphics graphics) {
        Color temp = graphics.getColor();
        graphics.setColor(Color.CYAN);
        super.draw(graphics);
        graphics.setColor(temp);
    }
}