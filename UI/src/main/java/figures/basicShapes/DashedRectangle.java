package figures.basicShapes;

import java.awt.*;

/**
 * a class used to draw a dashed rectangle
 */
public class DashedRectangle extends Rectangle{

    /**
     *
     * @param x
     *      the x-coordinate of the rectangle's center point
     * @param y
     *      the y-coordinate of the rectangle's center point
     * @param width
     *      the rectangles width
     * @param length
     *      the rectangles height
     */
    public DashedRectangle(int x, int y, int width, int length) {
        super(x, y, width, length);
    }

    /**
     * a draw fucntion that draws on the Graphics parameter object
     * @param graphics
     *      object used to draw on the program's window
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY){
        new DashedLine(positionTL, cornerTR).draw(graphics,minX,minY,maxX,maxY);
        new DashedLine(cornerTR, cornerBR).draw(graphics,minX,minY,maxX,maxY);
        new DashedLine(cornerBR, cornerBL).draw(graphics,minX,minY,maxX,maxY);
        new DashedLine(cornerBL, positionTL).draw(graphics,minX,minY,maxX,maxY);
    }
}
