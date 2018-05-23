package figures.drawable.diagramFigures;

import window.elements.textbox.TextBox;

import java.awt.*;
import java.awt.geom.Point2D;

public class SelectedTextBoxFigure extends TextBoxFigure {
    public SelectedTextBoxFigure(TextBox textBox, Point2D absolutePosition, String title) {
        super(textBox, absolutePosition, title);
    }

    @Override
    protected void drawContents(Graphics graphics, Point2D contentPos) {
        graphics.drawString(textBox.getContents()+"I", (int) contentPos.getX() + 3, (int) contentPos.getY() + 12);
    }
}
