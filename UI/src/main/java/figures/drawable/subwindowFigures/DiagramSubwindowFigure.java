package figures.drawable.subwindowFigures;

import window.diagram.DiagramSubwindow;

import java.awt.*;

public class DiagramSubwindowFigure extends SubwindowFigure {

    /**
     * draws a diagram subwindow
     * @param subwindow diagram subwindow to be drawn
     */
    public DiagramSubwindowFigure(DiagramSubwindow subwindow) {
        super(subwindow.getFrame());
    }

    /**
     * draws a diagram subwindow frame
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        drawWindowFrame(graphics);
    }
}
