package command.closeWindow;

import action.Action;
import action.EmptyAction;
import command.Command;
import controller.InteractionController;
import window.Subwindow;
import window.diagram.DiagramSubwindow;

/**
 * command for closing subwindows
 */
public class CloseSubwindowCommand extends Command {

    private Subwindow subwindow;
    private InteractionController interactionController;

    /**
     * creates a new closeSubWindowcommand for closing the given subwindow in the given interactioncontroller
     *
     * @param subwindow             the subwindow to close
     * @param interactionController the interaction controller that contains the subwindow
     */
    public CloseSubwindowCommand(Subwindow subwindow, InteractionController interactionController) {
        this.setSubwindow(subwindow);
        this.setInteractionController(interactionController);
    }

    /**
     * @return the subwindow to close
     */
    public Subwindow getSubwindow() {
        return subwindow;
    }

    /**
     * sets the subwindow to close
     *
     * @param subwindow the subwindow to close
     */
    public void setSubwindow(Subwindow subwindow) {
        this.subwindow = subwindow;
    }

    /**
     * @return the interaction controller containing the subwindow
     */
    public InteractionController getInteractionController() {
        return interactionController;
    }

    /**
     * sets the interaction controller for this command
     *
     * @param interactionController the new interaction controller
     */
    public void setInteractionController(InteractionController interactionController) {
        this.interactionController = interactionController;
    }

    /**
     * performs the action of this command
     *
     * @return an action detailing the change by handling the action
     */
    @Override
    public Action performAction() {
        getInteractionController().removeSubwindow(getSubwindow());
        return new EmptyAction();
    }
}
