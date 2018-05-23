package figures.converters.dialogbox;

import figures.drawable.subwindowFigures.PartyDialogBoxFigure;
import window.dialogbox.PartyDialogBox;

import java.awt.*;

public class PartyDialogBoxConverter extends DialogBoxConverter {

    private PartyDialogBox dialogBox;

    public PartyDialogBoxConverter(PartyDialogBox dialogBox) {
        this.dialogBox = dialogBox;
        this.figure = new PartyDialogBoxFigure(dialogBox);
    }

    /**
     * method that draws the dialog box's contents
     *
     * @param graphics object used to draw on the program's window
     */
    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);
        //drawSelectionBox(graphics, dialogBox.getSelected(), dialogBox.getAbsolutePosition(dialogBox.getSelected().getCoordinate()));
    }
}
