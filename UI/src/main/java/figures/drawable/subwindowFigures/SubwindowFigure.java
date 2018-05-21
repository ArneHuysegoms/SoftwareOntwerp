package figures.drawable.subwindowFigures;

import figures.drawable.IDrawable;
import figures.drawable.basicShapes.Rectangle;
import figures.drawable.diagramFigures.CloseButtonFigure;
import window.elements.button.Button;
import window.frame.SubwindowFrame;
import window.frame.TitleBar;

import java.awt.*;

public abstract class SubwindowFigure implements IDrawable {

    private SubwindowFrame subwindow;
    private TitleBar titleBar;
    private Button closeButton;

    /**
//     * @param start top-left point of the window.diagram
//     * @param end   bottom-right point of the window.diagram
     */
    public SubwindowFigure(SubwindowFrame subwindow) {
        this.subwindow = subwindow;
        this.titleBar = subwindow.getTitleBar();
        this.closeButton = subwindow.getButton();
    }

    /**
     * a draw fucntion that draws on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        drawBackgroundColor(graphics, Color.WHITE);
        drawFrame(graphics, minX, minY, maxX, maxY);
        drawTitleBar(graphics, minX, minY, maxX, maxY);
        drawCloseButton(graphics, minX, minY, maxX, maxY);
    }


    public void drawBackgroundColor(Graphics graphics, Color c) {
        graphics.setColor(c);
        graphics.fillRect((int)subwindow.getSubwindowPoint().getX(),(int)subwindow.getSubwindowPoint().getY(),subwindow.getSubwindowHeight(),subwindow.getSubwindowWidth());
        graphics.setColor(Color.BLACK);
    }

    public void drawFrame(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        new figures.drawable.basicShapes.Rectangle(subwindow.getSubwindowPoint(),subwindow.getSubwindowHeight(),subwindow.getSubwindowWidth()).draw(graphics, minX, minY, maxX, maxY);
    }

    public void drawTitleBar(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        new Rectangle(titleBar.getPosition(),TitleBar.HEIGHT,titleBar.getWidth()).draw(graphics, minX, minY, maxX, maxY);
    }

    public void drawCloseButton(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        new CloseButtonFigure(closeButton.getPosition(),closeButton.getHeight(),closeButton.getWidth()).draw(graphics, minX, minY, maxX, maxY);
    }
}
