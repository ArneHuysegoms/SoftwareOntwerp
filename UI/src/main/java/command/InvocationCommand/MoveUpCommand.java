package command.InvocationCommand;

import action.Action;
import action.UpdateLabelAction;
import command.Command;
import diagram.label.InvocationMessageLabel;
import window.diagram.DiagramSubwindow;
import window.elements.ListBox;
import window.elements.textbox.ArgumentTextBox;

public class MoveUpCommand extends InvocationCommand {

    public MoveUpCommand(ListBox lb, InvocationMessageLabel iml, DiagramSubwindow diagramSubwindow){
        super(lb,iml,diagramSubwindow);
    }

    @Override
    public Action performAction() {
        getListBox().moveUp();
        getInvocationMessageLabel().moveUp();
        return new UpdateLabelAction(getDiagramSubwindow().getFacade().findParentElement(getInvocationMessageLabel()), getInvocationMessageLabel());
    }
}
