package figures.converters.dialogbox;

import figures.drawable.subwindowFigures.DiagramDialogBoxFigure;
import window.dialogbox.DiagramDialogBox;

import java.awt.*;

public class DiagramDialogboxConverter extends DialogboxConverter {
    private DiagramDialogBox dialogBox;

    public DiagramDialogboxConverter(DiagramDialogBox dialogBox) {
        this.dialogBox = dialogBox;
        setFigure();
    }

    protected void setFigure() {
        this.figure = new DiagramDialogBoxFigure(dialogBox);
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);
        drawSelectionBox(graphics, dialogBox.getSelected());
    }
}
