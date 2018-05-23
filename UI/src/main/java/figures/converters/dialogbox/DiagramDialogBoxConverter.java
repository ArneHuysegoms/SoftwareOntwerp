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
}
