package figures.diagramFigures;

import uievents.KeyEvent;
import uievents.MouseEvent;
import window.dialogbox.DialogBox;
import window.dialogbox.InvocationMessageDialogBox;

import java.awt.geom.Point2D;

public class InvocationMessageDialogBoxFigure extends SubwindowFigure {

    private InvocationMessageDialogBox dialogBox;
    public InvocationMessageDialogBoxFigure(InvocationMessageDialogBox dialogBox) {
        super(dialogBox.getFrame());
        this.dialogBox = dialogBox;
    }

    //TODO A dialog box for an invocation message shows a text box for the method name, a list box
    //for the arguments, a text box and a button for adding a new argument to the end
    //of the argument list, buttons for moving the selected argument up or down in the
    //argument list, and a button for deleting the selected argument.
}
