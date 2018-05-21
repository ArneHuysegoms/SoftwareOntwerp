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

    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        Point2D textBoxPos=textBox.getCoordinate(),
                titlePos = new Point2D.Double(textBoxPos.getX()+TextBox.WIDTH+3,textBoxPos.getY()),
                contentPos = new Point2D.Double(textBoxPos.getX()+3,textBoxPos.getY());

        new Rectangle(textBoxPos, TextBox.HEIGHT, TextBox.WIDTH).draw(graphics, minX, minY, maxX, maxY);
        drawTitle(graphics, titlePos);
        drawContents(graphics, contentPos);
    }

    @Override
    public void draw(Graphics graphics) {
        Point2D textBoxPos=textBox.getCoordinate(),
                titlePos = new Point2D.Double(textBoxPos.getX()+TextBox.WIDTH+3,textBoxPos.getY()),
                contentPos = new Point2D.Double(textBoxPos.getX()+3,textBoxPos.getY());

        new Rectangle(textBoxPos, TextBox.HEIGHT, TextBox.WIDTH).draw(graphics);
        drawTitle(graphics, titlePos);
        drawContents(graphics, contentPos);
    }

    private void drawTitle(Graphics graphics, Point2D titlePos) {
        graphics.drawString(title, (int)titlePos.getX(), (int)titlePos.getY());
    }

    private void drawContents(Graphics graphics, Point2D contentPos) {
        graphics.drawString(textBox.getContents(), (int)contentPos.getX(), (int)contentPos.getY());
    }


}
