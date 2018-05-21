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

public abstract class DialogboxConverter extends SubwindowConverter {

    protected SubwindowFigure figure;

    @Override
    public void draw(Graphics graphics) {
        drawSubwindow(graphics);
    }

    protected void drawSelectionBox(Graphics graphics, DialogboxElement selected) {
        if (selected instanceof TextBox) {
            TextBox tb = (TextBox) selected;
            Point2D tbPos = tb.getCoordinate();
            Point2D start = new Point2D.Double(tbPos.getX() - 3, tbPos.getY() - 3),
                    end = new Point2D.Double(tbPos.getX() + TextBox.WIDTH + 3, tbPos.getY() + TextBox.HEIGHT + 3);
            new DialogBoxSelectionBoxFigure(start, end).draw(graphics);
        } else if (selected instanceof RadioButton) {
            RadioButton rb = (RadioButton) selected;
            Point2D rbPos = rb.getCoordinate();
            Point2D start = new Point2D.Double(rbPos.getX() - 3, rbPos.getY() - 3),
                    end = new Point2D.Double(rbPos.getX() + RadioButton.WIDTH + 2, rbPos.getY() + RadioButton.HEIGHT + 2);
            new DialogBoxSelectionBoxFigure(start, end).draw(graphics, 0, 0, 2000, 2000);
        } else if (selected instanceof FakeButton) {
            FakeButton fb = (FakeButton) selected;
            Point2D fbPos = fb.getCoordinate();
            Point2D start = new Point2D.Double(fbPos.getX() - 2, fbPos.getY() - 2),
                    end = new Point2D.Double(fbPos.getX() + fb.getWidth() + 2, fbPos.getY() + fb.getHeight()+ 2);
            new DialogBoxSelectionBoxFigure(start, end).draw(graphics, 0, 0, 2000, 2000);
        } else if (selected instanceof ListBox) {
            ListBox lb = (ListBox) selected;
            Point2D lbPos = lb.getCoordinate();
            Point2D start = new Point2D.Double(lbPos.getX() - 3, lbPos.getY() - 3),
                    end = new Point2D.Double(lbPos.getX() + ListBox.WIDTH + 3, lbPos.getY() + ListBox.HEIGHT+ 3);
            new DialogBoxSelectionBoxFigure(start, end).draw(graphics, 0, 0, 2000, 2000);
        }

    }


    @Override
    public void drawSubwindow(Graphics graphics) {
        figure.draw(graphics, 0, 0, 2000, 2000);
    }
}
