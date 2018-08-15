package command.changeType.DiagramCommand;

import command.Command;
import window.diagram.DiagramSubwindow;

public abstract class DiagramCommand extends Command {

    protected DiagramSubwindow diagramSubwindow;

    /**
     * diagramCommand is a superclass for toSequence and toCommunication command
     * @param diagramSubwindow
     */
    public DiagramCommand(DiagramSubwindow diagramSubwindow){
        setDiagramSubwindow(diagramSubwindow);
    }

    /**
     *
     * @return diagramSubwindow
     */
    public DiagramSubwindow getDiagramSubwindow() {
        return diagramSubwindow;
    }

    /**
     *
     * @param diagramSubwindow
     */
    public void setDiagramSubwindow(DiagramSubwindow diagramSubwindow) {
        this.diagramSubwindow = diagramSubwindow;
    }
}
