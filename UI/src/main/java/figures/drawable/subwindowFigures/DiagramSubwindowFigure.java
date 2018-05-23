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

    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        drawWindowFrame(graphics);
    }
}
