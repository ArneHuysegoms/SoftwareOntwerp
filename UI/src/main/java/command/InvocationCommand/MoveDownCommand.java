package command.InvocationCommand;

import action.Action;
import action.UpdateLabelAction;
import command.Command;
import diagram.label.InvocationMessageLabel;
import window.diagram.DiagramSubwindow;
import window.elements.ListBox;
import window.elements.textbox.ArgumentTextBox;

public class MoveDownCommand extends Command {
    private ListBox listBox;
    private ArgumentTextBox textBox;
    private InvocationMessageLabel label;
    private DiagramSubwindow subwindow;

    public MoveDownCommand(ListBox lb, ArgumentTextBox atb, InvocationMessageLabel iml, DiagramSubwindow diagramSubwindow){
        this.listBox = lb;
        this.textBox = atb;
        this.label = iml;
        this.subwindow = diagramSubwindow;
    }

    @Override
    public Action performAction() {
        listBox.moveDown();
        label.moveDown();
        return new UpdateLabelAction(subwindow.getFacade().findParentElement(label), label);
    }
}
