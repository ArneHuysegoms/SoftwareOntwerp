package figures.drawable.subwindowFigures;

import figures.drawable.diagramFigures.SelectedTextBoxFigure;
import figures.drawable.diagramFigures.SelectionBoxFigure;
import window.elements.DialogboxElement;
import window.elements.ListBox;
import window.elements.RadioButton;
import window.elements.button.FakeButton;
import window.elements.textbox.TextBox;
import window.frame.SubwindowFrame;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class DialogBoxSubwindowFigure extends SubwindowFigure {

    public DialogBoxSubwindowFigure(SubwindowFrame subwindow) {
        super(subwindow);
    }

    /**
     * method that draws a box around the selected element
     *
     * @param graphics object used to draw on the program's window
     * @param selected the selected element
     * @param absolutePosition the position of the selected element
     */
    protected void handleSelectedElement(Graphics graphics, DialogboxElement selected, Point2D absolutePosition) {

        if (selected instanceof TextBox) {
            drawSelectedTextBoxFigure(graphics, (TextBox) selected, absolutePosition);

            Point2D start = new Point2D.Double(absolutePosition.getX() - 3, absolutePosition.getY() - 3),
                    end = new Point2D.Double(absolutePosition.getX() + TextBox.WIDTH + 3, absolutePosition.getY() + TextBox.HEIGHT + 3);
            new SelectionBoxFigure(start, end, Color.BLUE).draw(graphics);
        } else if (selected instanceof RadioButton) {
            Point2D start = new Point2D.Double(absolutePosition.getX() - 3, absolutePosition.getY() - 3),
                    end = new Point2D.Double(absolutePosition.getX() + RadioButton.WIDTH + 2, absolutePosition.getY() + RadioButton.HEIGHT + 2);
            new SelectionBoxFigure(start, end, Color.BLUE).draw(graphics, 0, 0, 2000, 2000);
        } else if (selected instanceof FakeButton) {
            FakeButton fb = (FakeButton) selected;
            Point2D start = new Point2D.Double(absolutePosition.getX() - 2, absolutePosition.getY() - 2),
                    end = new Point2D.Double(absolutePosition.getX() + fb.getWidth() + 2, absolutePosition.getY() + fb.getHeight() + 2);
            new SelectionBoxFigure(start, end, Color.BLUE).draw(graphics, 0, 0, 2000, 2000);
        } else if (selected instanceof ListBox) {
            Point2D start = new Point2D.Double(absolutePosition.getX() - 3, absolutePosition.getY() - 3),
                    end = new Point2D.Double(absolutePosition.getX() + ListBox.WIDTH + 3, absolutePosition.getY() + ListBox.HEIGHT + 3);
            new SelectionBoxFigure(start, end, Color.BLUE).draw(graphics, 0, 0, 2000, 2000);
        }
    }

    /**
     * method used to draw the selected text box
     * @param graphics object used to draw on the program's window
     * @param selected the selected text box
     * @param absolutePosition the position of the selected text box
     */
    private void drawSelectedTextBoxFigure(Graphics graphics, TextBox selected, Point2D absolutePosition) {
        new SelectedTextBoxFigure((TextBox) selected, absolutePosition, "")
                .draw(graphics);
    }
}
