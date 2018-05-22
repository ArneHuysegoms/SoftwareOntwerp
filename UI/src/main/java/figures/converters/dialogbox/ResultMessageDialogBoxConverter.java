package figures.converters.dialogbox;

import figures.drawable.subwindowFigures.ResultMessageDialogBoxFigure;
import window.dialogbox.ResultMessageDialogBox;

import java.awt.*;

public class ResultMessageDialogBoxConverter extends DialogBoxConverter {

    private ResultMessageDialogBox dialogBox;

    public ResultMessageDialogBoxConverter(ResultMessageDialogBox dialogBox) {
        this.dialogBox = dialogBox;
        this.figure = new ResultMessageDialogBoxFigure(dialogBox);
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
