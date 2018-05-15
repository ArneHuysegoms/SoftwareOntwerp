package command.closeWindow;

import controller.CanvasController;
import window.diagram.DiagramSubwindow;

public class CloseDiagramSubwindowCommand extends CloseSubwindowCommand{

    private CanvasController canvasController;
    private DiagramSubwindow diagramSubwindow;

    public CloseDiagramSubwindowCommand(CanvasController canvasController, DiagramSubwindow diagramSubwindow){
        super(diagramSubwindow);
        this.setCanvasController(canvasController);
    }

    public CanvasController getCanvasController() {
        return canvasController;
    }

    private void setCanvasController(CanvasController canvasController) {
        this.canvasController = canvasController;
    }

    @Override
    public void performAction() {
        canvasController.removeSubwindow(diagramSubwindow);
    }
}
