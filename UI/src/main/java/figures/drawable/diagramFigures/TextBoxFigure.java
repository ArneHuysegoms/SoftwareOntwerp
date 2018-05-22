package figures.drawable.diagramFigures;

import figures.drawable.IDrawable;
import figures.drawable.basicShapes.Rectangle;
import window.elements.textbox.TextBox;

import java.awt.*;
import java.awt.geom.Point2D;

public class TextBoxFigure implements IDrawable {

    private TextBox textBox;
    private String title;

    public TextBoxFigure(TextBox textBox, String title){
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
        Point2D textBoxPos=textBox.getCoordinate(),
                titlePos = new Point2D.Double(textBoxPos.getX()+TextBox.WIDTH+3,textBoxPos.getY()),
                contentPos = new Point2D.Double(textBoxPos.getX()+3,textBoxPos.getY());

        new Rectangle(textBoxPos, TextBox.HEIGHT, TextBox.WIDTH).draw(graphics, minX, minY, maxX, maxY);
        drawTitle(graphics, titlePos);
        drawContents(graphics, contentPos);
    }

    /**
     * a draw function that draws a TextBox on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     */
    @Override
    public void draw(Graphics graphics) {
        Point2D textBoxPos=textBox.getCoordinate(),
                titlePos = new Point2D.Double(textBoxPos.getX()+TextBox.WIDTH+3,textBoxPos.getY()),
                contentPos = new Point2D.Double(textBoxPos.getX()+3,textBoxPos.getY());

        new Rectangle(textBoxPos, TextBox.HEIGHT, TextBox.WIDTH).draw(graphics);
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
        graphics.drawString(title, (int)titlePos.getX(), (int)titlePos.getY());
    }

    /**
     * draws what's being typed in the TextBox
     * @param graphics object used to draw on the program's window
     * @param contentPos
     */
    private void drawContents(Graphics graphics, Point2D contentPos) {
        graphics.drawString(textBox.getContents(), (int)contentPos.getX(), (int)contentPos.getY());
    }


}
