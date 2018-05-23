package command.changeType;

import action.Action;
import action.EmptyAction;
import command.Command;
import window.diagram.DiagramSubwindow;

/**
 * command for change to a sequence diagram
 */
public class ChangeToSequenceCommand extends Command {

    private DiagramSubwindow diagramSubwindow;

    /**
     * creates a new changeToSequence command for the given diagramsubwindow
     *
     * @param diagramSubwindow the diagramsubwindow to change
     */
    public ChangeToSequenceCommand(DiagramSubwindow diagramSubwindow) {
        this.diagramSubwindow = diagramSubwindow;
    }

    /**
     * perform the action of this command
     *
     * @return an action detailing the change by handling the action
     */
    @Override
    public Action performAction() {
        if (!(diagramSubwindow.activeDiagamIsSequence())) {
            diagramSubwindow.changeActiveDiagram();
        }
        return new EmptyAction();
    }
}
