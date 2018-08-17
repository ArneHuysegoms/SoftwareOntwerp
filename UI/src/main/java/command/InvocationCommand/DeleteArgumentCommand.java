package command.InvocationCommand;

import action.Action;
import action.EmptyAction;
import action.UpdateLabelAction;
import command.Command;
import diagram.label.InvocationMessageLabel;
import window.diagram.DiagramSubwindow;
import window.elements.ListBox;
import window.elements.textbox.ArgumentTextBox;

public class DeleteArgumentCommand extends InvocationCommand {

    public DeleteArgumentCommand(ListBox lb, InvocationMessageLabel iml, DiagramSubwindow diagramSubwindow){
        super(lb,iml,diagramSubwindow);
    }

    @Override
    public Action performAction() {
        getInvocationMessageLabel().deleteArgument(getListBox().getSelectedIndex());
        getListBox().removeArgument();
        return new UpdateLabelAction(getDiagramSubwindow().getFacade().findParentElement(getInvocationMessageLabel()), getInvocationMessageLabel());
    }
}
