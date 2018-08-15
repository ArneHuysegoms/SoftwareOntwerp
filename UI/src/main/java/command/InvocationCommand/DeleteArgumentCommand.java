package command.InvocationCommand;

import action.Action;
import action.EmptyAction;
import action.UpdateLabelAction;
import command.Command;
import diagram.label.InvocationMessageLabel;
import window.diagram.DiagramSubwindow;
import window.elements.ListBox;
import window.elements.textbox.ArgumentTextBox;

public class DeleteArgumentCommand extends Command {
    private ListBox listBox;
    private InvocationMessageLabel label;
    private DiagramSubwindow subwindow;

    public DeleteArgumentCommand(ListBox lb, InvocationMessageLabel iml, DiagramSubwindow diagramSubwindow){
        this.listBox = lb;
        this.label = iml;
        this.subwindow = diagramSubwindow;
    }

    @Override
    public Action performAction() {
        System.out.println();
        label.deleteArgument(listBox.getSelectedIndex());
        listBox.removeArgument();
        return new UpdateLabelAction(subwindow.getFacade().findParentElement(label), label);
    }
}
