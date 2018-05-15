package command.changeType;

import command.Command;
import window.diagram.DiagramSubwindow;

public class ChangeToSequenceCommand extends Command {

    private DiagramSubwindow diagramSubwindow;

    public ChangeToSequenceCommand(DiagramSubwindow diagramSubwindow){
        this.diagramSubwindow = diagramSubwindow;
    }

    @Override
    public void performAction() {
        if(! (diagramSubwindow.activeDiagamIsSequence())){
            diagramSubwindow.changeActiveDiagram();
        }
    }
}
