package command.InvocationCommand;

import action.Action;
import command.Command;
import diagram.label.InvocationMessageLabel;
import window.diagram.DiagramSubwindow;
import window.elements.ListBox;

public abstract class InvocationCommand extends Command {
    private ListBox listBox;
    private InvocationMessageLabel invocationMessageLabel;
    private DiagramSubwindow diagramSubwindow;

    public InvocationCommand(ListBox listBox, InvocationMessageLabel label, DiagramSubwindow diagramSubwindow){
        setListBox(listBox);
        setInvocationMessageLabel(label);
        setDiagramSubwindow(diagramSubwindow);
    }

    public void setDiagramSubwindow(DiagramSubwindow diagramSubwindow) {
        this.diagramSubwindow = diagramSubwindow;
    }

    public void setInvocationMessageLabel(InvocationMessageLabel invocationMessageLabel) {
        this.invocationMessageLabel = invocationMessageLabel;
    }

    public void setListBox(ListBox listBox) {
        this.listBox = listBox;
    }

    public ListBox getListBox() {
        return listBox;
    }

    public DiagramSubwindow getDiagramSubwindow() {
        return diagramSubwindow;
    }

    public InvocationMessageLabel getInvocationMessageLabel() {
        return invocationMessageLabel;
    }
}
