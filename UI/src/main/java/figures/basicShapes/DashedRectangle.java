package figures.basicShapes;

import java.awt.*;

/**
 * a class used to draw a dashed rectangle
 */
public class DashedRectangle extends Rectangle{
    public DashedRectangle(int x, int y, int width, int length) {
        super(x, y, width, length);
    }

    /**
     * a draw fucntion that draws on the Graphics parameter object
     * @param graphics
     *      object used to draw on the program's window
     */
    @Override
    public void draw(Graphics graphics){
        new DashedLine(positionTL, cornerTR).draw(graphics);
        new DashedLine(cornerTR, cornerBR).draw(graphics);
        new DashedLine(cornerBR, cornerBL).draw(graphics);
        new DashedLine(cornerBL, positionTL).draw(graphics);
    }
}
