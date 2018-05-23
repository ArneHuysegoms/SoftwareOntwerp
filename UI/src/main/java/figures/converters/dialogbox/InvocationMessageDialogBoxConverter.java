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
}
