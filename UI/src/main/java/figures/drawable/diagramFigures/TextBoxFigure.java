package figures.drawable.diagramFigures;

import figures.drawable.IDrawable;
import figures.drawable.basicShapes.Rectangle;
import window.elements.textbox.TextBox;

import java.awt.*;
import java.awt.geom.Point2D;

public class TextBoxFigure implements IDrawable {

    protected TextBox textBox;
    private String title;
    private Point2D absolutePosition;

    public TextBoxFigure(TextBox textBox, Point2D absolutePosition, String title) {
        this.absolutePosition = absolutePosition;
        this.textBox = textBox;
        this.title = title;
    }

    /**
     * a draw function that draws a TextBox on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        Point2D titlePos = new Point2D.Double(absolutePosition.getX() + TextBox.WIDTH + 3, absolutePosition.getY()),
                contentPos = new Point2D.Double(absolutePosition.getX() + 3, absolutePosition.getY());

        new Rectangle(absolutePosition, TextBox.WIDTH, TextBox.HEIGHT).draw(graphics, minX, minY, maxX, maxY);
        drawTitle(graphics, titlePos);
        drawContents(graphics, contentPos);
    }

    /**
     * draws the text box's title
     *
     * @param graphics object used to draw on the program's window
     * @param titlePos startingposition of the title
     */
    private void drawTitle(Graphics graphics, Point2D titlePos) {
        graphics.drawString(title, (int) titlePos.getX() + 3, (int) titlePos.getY() + 11);

    }

    /**
     * draws what's being typed in the TextBox
     *
     * @param graphics   object used to draw on the program's window
     * @param contentPos
     */
    protected void drawContents(Graphics graphics, Point2D contentPos) {
        graphics.drawString(textBox.getContents(), (int) contentPos.getX() + 3, (int) contentPos.getY() + 12);
    }


}
