package figures.diagramFigures;

import uievents.KeyEvent;
import uievents.MouseEvent;
import window.dialogbox.DialogBox;

import java.awt.geom.Point2D;

public class ResultMessageDialogBoxFigure extends SubwindowFigure{

    private ResultMessageDialogBox dialogBox;

    public ResultMessageDialogBoxFigure(ResultMessageDialogBox dialogBox) {
        super(dialogBox.getFrame());
        this.dialogBox = dialogBox;
    }

    //TODO A dialog box for a result message shows a text box for the label.
}
