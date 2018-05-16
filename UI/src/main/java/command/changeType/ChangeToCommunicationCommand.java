package command.changeType;

import command.Command;
import window.diagram.DiagramSubwindow;

public class ChangeToCommunicationCommand extends Command {

    private DiagramSubwindow diagramSubwindow;

    public ChangeToCommunicationCommand(DiagramSubwindow diagramSubwindow){
        this.diagramSubwindow = diagramSubwindow;
    }

    @Override
    public void performAction() {
        if(! (diagramSubwindow.activeDiagramIsCommunication())){
            diagramSubwindow.changeActiveDiagram();
        }
    }
}
