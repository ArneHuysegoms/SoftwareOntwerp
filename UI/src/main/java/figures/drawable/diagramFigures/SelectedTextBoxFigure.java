package figures.drawable.diagramFigures;

import window.elements.textbox.TextBox;

import java.awt.*;
import java.awt.geom.Point2D;

public class SelectedTextBoxFigure extends TextBoxFigure {

    /**
     *
     * @param textBox text box to be drawn
     * @param absolutePosition position of the text box
     * @param title text box's title
     */
    public SelectedTextBoxFigure(TextBox textBox, Point2D absolutePosition, String title) {
        super(textBox, absolutePosition, title);
    }

    /**
     *
     * @param graphics   object used to draw on the program's window
     * @param contentPos top-left corner of the text box
     */
    @Override
    protected void drawContents(Graphics graphics, Point2D contentPos) {
        graphics.drawString(textBox.getContents()+"I", (int) contentPos.getX() + 3, (int) contentPos.getY() + 12);
    }
}
