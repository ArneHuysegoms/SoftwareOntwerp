package figures;

import java.awt.*;


public interface IDrawable {
    /**
     * a draw fucntion that draws on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY);
}
