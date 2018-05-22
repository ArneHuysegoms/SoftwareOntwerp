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

    public SubwindowFigure(SubwindowFrame subwindow) {
        this.subwindow = subwindow;
        this.titleBar = subwindow.getTitleBar();
        this.closeButton = subwindow.getButton();
    }

    /**
     * a draw function that draws a subwindow on the Graphics parameter object
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

    /**
     * a draw function that draws a subwindow on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     */
    @Override
    public void draw(Graphics graphics) {
        drawBackgroundColor(graphics, Color.WHITE);
        drawFrame(graphics);
        drawTitleBar(graphics);
        drawCloseButton(graphics);
    }

    /**
     * paints on the subwindow's backgrouind color
     *
     * @param graphics object used to draw on the program's window
     * @param c        subwindows background color
     */
    private void drawBackgroundColor(Graphics graphics, Color c) {
        graphics.setColor(c);
        graphics.fillRect((int) subwindow.getSubwindowPoint().getX(), (int) subwindow.getSubwindowPoint().getY(), subwindow.getSubwindowWidth(), subwindow.getSubwindowHeight());
        graphics.setColor(Color.BLACK);
    }

    /**
     * draws the subwindow it's frame
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    private void drawFrame(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        new figures.drawable.basicShapes.Rectangle(subwindow.getSubwindowPoint(), subwindow.getSubwindowWidth(), subwindow.getSubwindowHeight()).draw(graphics, minX, minY, maxX, maxY);
    }

    /**
     * draws the subwindow it's title bar
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    private void drawTitleBar(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        new Rectangle(titleBar.getPosition(), titleBar.getWidth(), TitleBar.HEIGHT).draw(graphics, minX, minY, maxX, maxY);
    }

    /**
     * draws the subwindow it's close button
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    private void drawCloseButton(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        new CloseButtonFigure(closeButton.getPosition(), closeButton.getHeight(), closeButton.getWidth()).draw(graphics, minX, minY, maxX, maxY);
    }

    /**
     * draws the subwindow it's frame
     *
     * @param graphics object used to draw on the program's window
     */
    private void drawFrame(Graphics graphics) {
        new figures.drawable.basicShapes.Rectangle(subwindow.getSubwindowPoint(), subwindow.getSubwindowWidth(), subwindow.getSubwindowHeight())
                .draw(graphics);
    }

    /**
     * draws the subwindow it's title bar
     *
     * @param graphics object used to draw on the program's window
     */
    private void drawTitleBar(Graphics graphics) {
        new Rectangle(titleBar.getPosition(), titleBar.getWidth(), TitleBar.HEIGHT)
                .draw(graphics);
    }

    /**
     * draws the subwindow it's close button
     *
     * @param graphics object used to draw on the program's window
     */
    private void drawCloseButton(Graphics graphics) {
        new CloseButtonFigure(closeButton.getPosition(), closeButton.getHeight(), closeButton.getWidth())
                .draw(graphics);
    }
}
