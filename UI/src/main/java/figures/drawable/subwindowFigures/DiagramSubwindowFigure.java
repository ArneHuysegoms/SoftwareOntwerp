package figures.drawable.subwindowFigures;

import window.diagram.DiagramSubwindow;

public class DiagramSubwindowFigure extends SubwindowFigure {

    /**
     //* @param start top-left point of the window.diagram
     //* @param end   bottom-right point of the window.diagram
     */
    public DiagramSubwindowFigure(DiagramSubwindow subwindow) {
        super(subwindow.getFrame());
    }
}
