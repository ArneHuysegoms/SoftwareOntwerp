package command.changeType;

import action.Action;
import action.EmptyAction;
import command.Command;
import window.diagram.DiagramSubwindow;

public class ChangeToCommunicationCommand extends Command {

    private DiagramSubwindow diagramSubwindow;

    public ChangeToCommunicationCommand(DiagramSubwindow diagramSubwindow){
        this.diagramSubwindow = diagramSubwindow;
    }

    @Override
    public Action performAction() {
        if(! (diagramSubwindow.activeDiagramIsCommunication())){
            diagramSubwindow.changeActiveDiagram();
        }
        return new EmptyAction();
    }
}
