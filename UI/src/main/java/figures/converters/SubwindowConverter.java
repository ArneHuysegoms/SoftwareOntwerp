package figures.converters;

import java.awt.*;

public abstract class SubwindowConverter{
    /**
     * method that draws the subwindow's skeleton, an empty subwindow
     *
     * @param g object used to draw on the program's window
     */
    public abstract void drawSubwindow(Graphics g);

    /**
     * method that draws the subwindow's contents
     *
     * @param g object used to draw on the program's window
     */
    public abstract void draw(Graphics g);
}
