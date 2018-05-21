package figures.drawable.subwindowFigures;

import window.diagram.DiagramSubwindow;

public class DiagramSubwindowFigure extends SubwindowFigure {

    /**
     * draws a diagram subwindow
     * @param subwindow diagram subwindow to be drawn
     */
    public DiagramSubwindowFigure(DiagramSubwindow subwindow) {
        super(subwindow.getFrame());
    }
}
