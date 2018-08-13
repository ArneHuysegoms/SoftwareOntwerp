package command.changeType.DiagramCommand;

import command.Command;
import window.diagram.DiagramSubwindow;

public abstract class DiagramCommand extends Command {

    protected DiagramSubwindow diagramSubwindow;

    public DiagramCommand(DiagramSubwindow diagramSubwindow){
        setDiagramSubwindow(diagramSubwindow);
    }

    public DiagramSubwindow getDiagramSubwindow() {
        return diagramSubwindow;
    }

    public void setDiagramSubwindow(DiagramSubwindow diagramSubwindow) {
        this.diagramSubwindow = diagramSubwindow;
    }
}
