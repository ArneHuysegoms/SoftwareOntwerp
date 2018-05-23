package action;

import window.diagram.DiagramSubwindow;
import window.dialogbox.DialogBox;

/**
 * Action class that details the action of opening a new dialogbox
 */
public class DialogBoxOpenedAction extends Action {

    DialogBox dialogBox;

    /**
     * creates a new DialogBoxOpenedAction
     *
     * @param dialogBox the dialogbox that was openend
     */
    public DialogBoxOpenedAction(DialogBox dialogBox) {
        this.dialogBox = dialogBox;
    }

    /**
     * @return the opened dialogbox
     */
    public DialogBox getDialogBox() {
        return dialogBox;
    }

    /**
     * this method does nothing, but needs to be implemented
     *
     * @param subwindow the subwindow to perform an action on
     */
    @Override
    public void performAction(DiagramSubwindow subwindow) {

    }
}
