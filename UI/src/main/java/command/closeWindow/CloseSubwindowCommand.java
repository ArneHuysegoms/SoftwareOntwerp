package command.closeWindow;

import action.Action;
import action.EmptyAction;
import command.Command;
import controller.InteractionController;
import window.Subwindow;
import window.diagram.DiagramSubwindow;

public class CloseSubwindowCommand extends Command {

    private Subwindow subwindow;
    private InteractionController interactionController;

    public CloseSubwindowCommand(Subwindow subwindow, InteractionController interactionController){
       this.setSubwindow(subwindow);
       this.setInteractionController(interactionController);
    }

    public Subwindow getSubwindow() {
        return subwindow;
    }

    public void setSubwindow(Subwindow subwindow) {
        this.subwindow = subwindow;
    }

    public InteractionController getInteractionController() {
        return interactionController;
    }

    public void setInteractionController(InteractionController interactionController) {
        this.interactionController = interactionController;
    }

    @Override
    public Action performAction(){
        getInteractionController().removeSubwindow(getSubwindow());
        //if DiagramSubwindow -> delete alle bijhorende Dialogboxes
        return new EmptyAction();
    }
}
