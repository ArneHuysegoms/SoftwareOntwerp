package command.closeWindow;

import command.Command;
import window.diagram.DiagramSubwindow;

public abstract class CloseSubwindowCommand extends Command {

    private DiagramSubwindow diagramSubwindow;

    public CloseSubwindowCommand(DiagramSubwindow diagramSubwindow){
        this.setDiagramSubwindow(diagramSubwindow);
    }

    public DiagramSubwindow getDiagramSubwindow() {
        return diagramSubwindow;
    }

    private void setDiagramSubwindow(DiagramSubwindow diagramSubwindow) {
        this.diagramSubwindow = diagramSubwindow;
    }

    @Override
    public abstract void performAction();
}
