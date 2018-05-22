package figures.drawable.diagramFigures;

import figures.drawable.IDrawable;
import figures.drawable.basicShapes.DashedRectangle;
import figures.drawable.basicShapes.Rectangle;
import window.elements.ListBox;

import java.awt.*;
import java.awt.geom.Point2D;

public class ListBoxFigure implements IDrawable {

    private ListBox listBox;
    private Point2D absolutePosition;
    private Point2D absoluteArgumentPosition;

    public ListBoxFigure(ListBox listBox, Point2D absolutePosition) {
        this.absolutePosition = absolutePosition;
        this.listBox = listBox;
    }

    /**
     * a draw function that draws a ListBox on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        drawBox(graphics, minX, minY, maxX, maxY);
        drawArguments(graphics);
        drawArgumentSelection(graphics, minX, minY, maxX, maxY);
    }

    /**
     * a draw function that draws a ListBox on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     */
    @Override
    public void draw(Graphics graphics) {
        drawBox(graphics);
        drawArguments(graphics);
        drawArgumentSelection(graphics);
    }

    /**
     * draws the box of a list box
     *
     * @param graphics object used to draw on the program's window
     */
    private void drawBox(Graphics graphics) {
        Color temp = graphics.getColor();
        graphics.setColor(Color.GRAY);
        new Rectangle(absolutePosition, ListBox.WIDTH, ListBox.HEIGHT)
                .draw(graphics);
        graphics.setColor(temp);
    }

    /**
     * marks an argument
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    private void drawArgumentSelection(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        if (listBox.getSelectedIndex() >= 0) {
            Color temp = graphics.getColor();
            graphics.setColor(Color.GRAY);
            int index = (listBox.getSelectedIndex() * ListBox.ARGUMENT_HEIGHT);
            new DashedRectangle((int) absolutePosition.getX() + 2, (int) absolutePosition.getY() + index, ListBox.WIDTH - 2, ListBox.ARGUMENT_HEIGHT)
                    .draw(graphics, minX, minY, maxX, maxY);
            graphics.setColor(temp);
        }
    }

    /**
     * marks an argument
     *
     * @param graphics object used to draw on the program's window
     */
    private void drawArgumentSelection(Graphics graphics) {
        //TODO tweak coordinates
        if (listBox.getSelectedIndex() >= 0) {
            Color temp = graphics.getColor();
            graphics.setColor(Color.GRAY);
            int index = (listBox.getSelectedIndex() * ListBox.ARGUMENT_HEIGHT);
            new DashedRectangle((int) absolutePosition.getX() + 2, (int) absolutePosition.getY() + index, ListBox.WIDTH - 2, ListBox.ARGUMENT_HEIGHT)
                    .draw(graphics);
            graphics.setColor(temp);
        }
    }

    /**
     * draws the argument in the list box
     *
     * @param graphics object used to draw on the program's window
     */
    private void drawArguments(Graphics graphics) {
        //TODO tweak coordinates
        int x = (int) absolutePosition.getX() + 3, y = (int) absolutePosition.getY();
        int argumentHeight = ListBox.ARGUMENT_HEIGHT;

        for (String str : listBox.getArguments()) {
            graphics.drawString(str, x + 3, y + 12);
            y += argumentHeight;
        }
    }

    /**
     * draws the box of a list box
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    private void drawBox(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        Color temp = graphics.getColor();
        graphics.setColor(Color.GRAY);
        new Rectangle(absolutePosition, ListBox.WIDTH, ListBox.HEIGHT)
                .draw(graphics, minX, minY, maxX, maxY);
        graphics.setColor(temp);
    }
}
