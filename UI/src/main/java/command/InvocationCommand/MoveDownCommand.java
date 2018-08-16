package command.InvocationCommand;

import action.Action;
import action.UpdateLabelAction;
import command.Command;
import diagram.label.InvocationMessageLabel;
import window.diagram.DiagramSubwindow;
import window.elements.ListBox;
import window.elements.textbox.ArgumentTextBox;

public class MoveDownCommand extends InvocationCommand {

    public MoveDownCommand(ListBox lb, InvocationMessageLabel iml, DiagramSubwindow diagramSubwindow){
        super(lb,iml,diagramSubwindow);
    }

    @Override
    public Action performAction() {
        getListBox().moveDown();
        getInvocationMessageLabel().moveDown();
        return new UpdateLabelAction(getDiagramSubwindow().getFacade().findParentElement(getInvocationMessageLabel()), getInvocationMessageLabel());
    }
}
