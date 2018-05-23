package figures.drawable.subwindowFigures;

import figures.drawable.IDrawable;
import figures.drawable.basicShapes.Rectangle;
import figures.drawable.diagramFigures.CloseButtonFigure;
import figures.drawable.diagramFigures.DialogBoxSelectionBoxFigure;
import figures.drawable.diagramFigures.SelectedTextBoxFigure;
import window.elements.DialogboxElement;
import window.elements.ListBox;
import window.elements.RadioButton;
import window.elements.button.Button;
import window.elements.button.FakeButton;
import window.elements.textbox.TextBox;
import window.frame.SubwindowFrame;
import window.frame.TitleBar;

import java.awt.*;
import java.awt.geom.Point2D;

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
     */
    public void drawWindowFrame(Graphics graphics) {
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
        Color temp = graphics.getColor();
        graphics.setColor(new Color(71, 129, 158));
        graphics.fillRect((int) titleBar.getPosition().getX(), (int) titleBar.getPosition().getY(), titleBar.getWidth(), TitleBar.HEIGHT);
        graphics.setColor(Color.BLACK);

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

    protected void handleSelectedElement(Graphics graphics,DialogboxElement selected, Point2D absolutePosition){

        if (selected instanceof TextBox) {
            drawSelectedTextBoxFigure(graphics,(TextBox)selected, absolutePosition);

            Point2D start = new Point2D.Double(absolutePosition.getX() - 3, absolutePosition.getY() - 3),
                    end = new Point2D.Double(absolutePosition.getX() + TextBox.WIDTH + 3, absolutePosition.getY() + TextBox.HEIGHT + 3);
            new DialogBoxSelectionBoxFigure(start, end).draw(graphics);
        } else if (selected instanceof RadioButton) {
            Point2D start = new Point2D.Double(absolutePosition.getX() - 3, absolutePosition.getY() - 3),
                    end = new Point2D.Double(absolutePosition.getX() + RadioButton.WIDTH + 2, absolutePosition.getY() + RadioButton.HEIGHT + 2);
            new DialogBoxSelectionBoxFigure(start, end).draw(graphics, 0, 0, 2000, 2000);
        } else if (selected instanceof FakeButton) {
            FakeButton fb = (FakeButton) selected;
            Point2D start = new Point2D.Double(absolutePosition.getX() - 2, absolutePosition.getY() - 2),
                    end = new Point2D.Double(absolutePosition.getX() + fb.getWidth() + 2, absolutePosition.getY() + fb.getHeight() + 2);
            new DialogBoxSelectionBoxFigure(start, end).draw(graphics, 0, 0, 2000, 2000);
        } else if (selected instanceof ListBox) {
            Point2D start = new Point2D.Double(absolutePosition.getX() - 3, absolutePosition.getY() - 3),
                    end = new Point2D.Double(absolutePosition.getX() + ListBox.WIDTH + 3, absolutePosition.getY() + ListBox.HEIGHT + 3);
            new DialogBoxSelectionBoxFigure(start, end).draw(graphics, 0, 0, 2000, 2000);
        }
    }

    private void drawSelectedTextBoxFigure(Graphics graphics, TextBox selected, Point2D absolutePosition) {
        new SelectedTextBoxFigure((TextBox)selected, absolutePosition, "")
                .draw(graphics);
    }
}
