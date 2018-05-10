package command;

import controller.CanvasController;
import window.diagram.DiagramSubwindow;

public class CloseSubwindowCommand extends Command {

    private CanvasController canvasController;
    private DiagramSubwindow diagramSubwindow;

    public CloseSubwindowCommand(CanvasController canvasController, DiagramSubwindow diagramSubwindow){
        this.setDiagramSubwindow(diagramSubwindow);
        this.setCanvasController(canvasController);
    }

    public CanvasController getCanvasController() {
        return canvasController;
    }

    private void setCanvasController(CanvasController canvasController) {
        this.canvasController = canvasController;
    }

    public DiagramSubwindow getDiagramSubwindow() {
        return diagramSubwindow;
    }

    private void setDiagramSubwindow(DiagramSubwindow diagramSubwindow) {
        this.diagramSubwindow = diagramSubwindow;
    }

    @Override
    public void performAction() {
        canvasController.removeSubwindow(diagramSubwindow);
    }
}
