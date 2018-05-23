package figures.converters.dialogbox;

import figures.drawable.subwindowFigures.InvocationMessageDialogBoxFigure;
import window.dialogbox.InvocationMessageDialogBox;

import java.awt.*;

public class InvocationMessageDialogBoxConverter extends DialogBoxConverter {

    private InvocationMessageDialogBox dialogBox;

    public InvocationMessageDialogBoxConverter(InvocationMessageDialogBox dialogBox) {
        this.dialogBox = dialogBox;
        this.figure = new InvocationMessageDialogBoxFigure(dialogBox);
    }

    /**
     * method that draws the dialog box's contents
     *
     * @param graphics object used to draw on the program's window
     */
    @Override
    public void draw(Graphics graphics){
        super.draw(graphics);
        //drawSelectionBox(graphics, dialogBox.getSelected(), dialogBox.getAbsolutePosition(dialogBox.getSelected().getCoordinate()));
    }
}
