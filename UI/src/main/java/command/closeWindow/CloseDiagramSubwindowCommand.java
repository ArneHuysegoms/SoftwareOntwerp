package command.closeWindow;

import controller.InteractionController;
import window.diagram.DiagramSubwindow;

public class CloseDiagramSubwindowCommand extends CloseSubwindowCommand{

    private InteractionController interactionController;
    private DiagramSubwindow diagramSubwindow;

    public CloseDiagramSubwindowCommand(InteractionController interactionController, DiagramSubwindow diagramSubwindow){
        super(diagramSubwindow);
        this.setInteractionController(interactionController);
    }

    public InteractionController getCanvasController() {
        return interactionController;
    }

    private void setInteractionController(InteractionController interactionController) {
        this.interactionController = interactionController;
    }

    @Override
    public void performAction() {
        interactionController.removeSubwindow(diagramSubwindow);
    }
}
