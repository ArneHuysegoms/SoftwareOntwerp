package figures.basicShapes;

import java.awt.*;

public class DashedRectangle extends Rectangle{
    public DashedRectangle(int x, int y, int width, int length) {
        super(x, y, width, length);
    }

    @Override
    public void draw(Graphics graphics){
        new DashedLine(positionTL, cornerTR).draw(graphics);
        new DashedLine(cornerTR, cornerBR).draw(graphics);
        new DashedLine(cornerBR, cornerBL).draw(graphics);
        new DashedLine(cornerBL, positionTL).draw(graphics);
    }
}
