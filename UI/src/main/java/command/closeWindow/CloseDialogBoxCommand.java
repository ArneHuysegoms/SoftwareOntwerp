package command.closeWindow;

import action.Action;
import action.EmptyAction;
import window.diagram.DiagramSubwindow;
import window.dialogbox.DialogBox;

public class CloseDialogBoxCommand extends CloseSubwindowCommand {

    private DialogBox dialogBox;

    public CloseDialogBoxCommand(DiagramSubwindow diagramSubwindow, DialogBox dialogBox){
        super(diagramSubwindow);
        this.setDialogBox(dialogBox);
    }

    public DialogBox getDialogBox() {
        return dialogBox;
    }

    public void setDialogBox(DialogBox dialogBox) {
        this.dialogBox = dialogBox;
    }

    @Override
    public Action performAction() {
        getDiagramSubwindow().removeDialogBox(getDialogBox());
        return new EmptyAction();
    }
}
