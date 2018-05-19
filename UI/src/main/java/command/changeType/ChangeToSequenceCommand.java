package command.changeType;

import action.Action;
import action.EmptyAction;
import command.Command;
import window.diagram.DiagramSubwindow;

public class ChangeToSequenceCommand extends Command {

    private DiagramSubwindow diagramSubwindow;

    public ChangeToSequenceCommand(DiagramSubwindow diagramSubwindow){
        this.diagramSubwindow = diagramSubwindow;
    }

    @Override
    public Action performAction() {
        if(! (diagramSubwindow.activeDiagamIsSequence())){
            diagramSubwindow.changeActiveDiagram();
        }
        return new EmptyAction();
    }
}
