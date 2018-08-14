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

public class AddArgumentCommand extends Command {
    private ListBox listBox;
    private ArgumentTextBox textBox;
    private InvocationMessageLabel label;
    private DiagramSubwindow subwindow;

    public AddArgumentCommand(ListBox lb, ArgumentTextBox atb, InvocationMessageLabel iml, DiagramSubwindow diagramSubwindow){
        this.listBox = lb;
        this.textBox = atb;
        this.label = iml;
        this.subwindow = diagramSubwindow;
    }

    @Override
    public Action performAction() {
        if (textBox.hasValidContents()) {
            String argumentString = textBox.getContents();
            listBox.addArgument(argumentString);
            label.addArgument(argumentString);
            return new UpdateLabelAction(subwindow.getFacade().findParentElement(label), label);
        }
        return new EmptyAction();
    }
}
