package figures.converters;

import java.awt.*;

public abstract class SubwindowConverter {
    /**
     * method that draws the subwindow's seleton, an empty subwindow
     * @param g        object used to draw on the program's window
     */
    public abstract void drawSubwindow(Graphics g);
    public abstract void draw(Graphics g);
}
