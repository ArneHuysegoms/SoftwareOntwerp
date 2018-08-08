package command.changeType.DiagramCommand;

import action.Action;
import action.EmptyAction;
import command.Command;
import window.diagram.DiagramSubwindow;

/**
 * command for changing a diagram to a communication diagram
 */
public class ChangeToCommunicationCommand extends Command {

    private DiagramSubwindow diagramSubwindow;

    /**
     * creates a new changeToCommunicationCommand for the given diagramSubWindow
     *
     * @param diagramSubwindow the diagramSubwindow to change
     */
    public ChangeToCommunicationCommand(DiagramSubwindow diagramSubwindow) {
        this.diagramSubwindow = diagramSubwindow;
    }

    /**
     * performs the action of this command
     *
     * @return an action detailing the change by handling the action
     */
    @Override
    public Action performAction() {
        if (!(diagramSubwindow.activeDiagramIsCommunication())) {
            diagramSubwindow.changeActiveDiagram();
        }
        return new EmptyAction();
    }
}
