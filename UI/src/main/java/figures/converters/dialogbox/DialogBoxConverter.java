package figures.converters.dialogbox;

import figures.converters.SubwindowConverter;
import figures.drawable.diagramFigures.DialogBoxSelectionBoxFigure;
import figures.drawable.subwindowFigures.*;
import window.elements.DialogboxElement;
import window.elements.ListBox;
import window.elements.RadioButton;
import window.elements.button.FakeButton;
import window.elements.textbox.TextBox;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class DialogBoxConverter extends SubwindowConverter {

    protected SubwindowFigure figure;

    /**
     * method that draws the dialog box's contents
     *
     * @param graphics object used to draw on the program's window
     */
    @Override
    public void draw(Graphics graphics) {
        drawSubwindow(graphics);
    }

    /**
     * method that draws a selection box around the currently selected selectable part of the dialog box
     *  @param graphics object used to draw on the program's window
     * @param selected The selected element in the dialogbox, this can be any DialogBoxElement
     * @param absolutePosition
     *
    protected void drawSelectionBox(Graphics graphics, DialogboxElement selected, Point2D absolutePosition) {
        if (selected instanceof TextBox) {
            TextBox tb = (TextBox) selected;
            Point2D start = new Point2D.Double(absolutePosition.getX() - 3, absolutePosition.getY() - 3),
                    end = new Point2D.Double(absolutePosition.getX() + TextBox.WIDTH + 3, absolutePosition.getY() + TextBox.HEIGHT + 3);
            new DialogBoxSelectionBoxFigure(start, end).draw(graphics);
        } else if (selected instanceof RadioButton) {
            RadioButton rb = (RadioButton) selected;
            Point2D start = new Point2D.Double(absolutePosition.getX() - 3, absolutePosition.getY() - 3),
                    end = new Point2D.Double(absolutePosition.getX() + RadioButton.WIDTH + 2, absolutePosition.getY() + RadioButton.HEIGHT + 2);
            new DialogBoxSelectionBoxFigure(start, end).draw(graphics, 0, 0, 2000, 2000);
        } else if (selected instanceof FakeButton) {
            FakeButton fb = (FakeButton) selected;
            Point2D start = new Point2D.Double(absolutePosition.getX() - 2, absolutePosition.getY() - 2),
                    end = new Point2D.Double(absolutePosition.getX() + fb.getWidth() + 2, absolutePosition.getY() + fb.getHeight() + 2);
            new DialogBoxSelectionBoxFigure(start, end).draw(graphics, 0, 0, 2000, 2000);
        } else if (selected instanceof ListBox) {
            ListBox lb = (ListBox) selected;
            Point2D start = new Point2D.Double(absolutePosition.getX() - 3, absolutePosition.getY() - 3),
                    end = new Point2D.Double(absolutePosition.getX() + ListBox.WIDTH + 3, absolutePosition.getY() + ListBox.HEIGHT + 3);
            new DialogBoxSelectionBoxFigure(start, end).draw(graphics, 0, 0, 2000, 2000);
        }
    }*/


    /**
     * method that draws the subwindow's skeleton, an empty subwindow
     *
     * @param graphics object used to draw on the program's window
     */
    @Override
    public void drawSubwindow(Graphics graphics) {
        figure.draw(graphics);
    }
}
