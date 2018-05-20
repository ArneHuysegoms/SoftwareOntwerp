package figures.drawable.subwindowFigures;

import window.diagram.DiagramSubwindow;

public class DiagramSubwindowFigure extends SubwindowFigure {

    /**
     //* @param start top-left point of the subwindow
     //* @param end   bottom-right point of the subwindow
     */
    public DiagramSubwindowFigure(DiagramSubwindow subwindow) {
        super(subwindow.getFrame());
    }

    /**
     * a draw fucntion that draws on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value

    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        super.draw(graphics,minX,minY,maxX,maxY);
    }*/
}
