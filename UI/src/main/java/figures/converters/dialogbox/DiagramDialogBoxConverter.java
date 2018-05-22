package figures.converters.dialogbox;

import figures.drawable.subwindowFigures.DiagramDialogBoxFigure;
import window.dialogbox.DiagramDialogBox;

import java.awt.*;

public class DiagramDialogBoxConverter extends DialogBoxConverter {

    private DiagramDialogBox dialogBox;

    public DiagramDialogBoxConverter(DiagramDialogBox dialogBox) {
        this.dialogBox = dialogBox;
        this.figure = new DiagramDialogBoxFigure(dialogBox);
    }

    /**
     * method that draws the dialog box's contents
     *
     * @param graphics object used to draw on the program's window
     */
    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);
        drawSelectionBox(graphics, dialogBox.getSelected(), dialogBox.getAbsolutePosition(dialogBox.getSelected().getCoordinate()));
    }
}
