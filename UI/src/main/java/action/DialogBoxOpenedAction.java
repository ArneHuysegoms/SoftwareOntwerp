package action;

import window.diagram.DiagramSubwindow;
import window.dialogbox.DialogBox;

public class DialogBoxOpenedAction extends Action{

    DialogBox dialogBox;

    public DialogBoxOpenedAction(DialogBox dialogBox){
        this.dialogBox = dialogBox;
    }

    public DialogBox getDialogBox() {
        return dialogBox;
    }

    @Override
    public void performAction(DiagramSubwindow subwindow) {

    }
}
