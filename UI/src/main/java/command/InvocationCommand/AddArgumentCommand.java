package command.InvocationCommand;

import action.Action;
import action.EmptyAction;
import action.UpdateLabelAction;
import command.Command;
import diagram.label.InvocationMessageLabel;
import window.diagram.DiagramSubwindow;
import window.elements.ListBox;
import window.elements.textbox.ArgumentTextBox;
import window.elements.textbox.TextBox;

public class AddArgumentCommand extends InvocationCommand {
    private ArgumentTextBox textBox;

    public AddArgumentCommand(ListBox lb, ArgumentTextBox atb, InvocationMessageLabel iml, DiagramSubwindow diagramSubwindow){
        super(lb,iml,diagramSubwindow);
        this.textBox = atb;
    }

    @Override
    public Action performAction() {
        if (textBox.hasValidContents()) {
            String argumentString = textBox.getContents();
            getListBox().addArgument(argumentString);
            getInvocationMessageLabel().addArgument(argumentString);
            return new UpdateLabelAction(getDiagramSubwindow().getFacade().findParentElement(getInvocationMessageLabel()), getInvocationMessageLabel());
        }
        return new EmptyAction();
    }
}
