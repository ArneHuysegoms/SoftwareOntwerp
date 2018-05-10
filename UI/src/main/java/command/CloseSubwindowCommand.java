package command;

import controller.CanvasController;
import subwindow.Subwindow;

public class CloseSubwindowCommand extends Command {

    private CanvasController canvasController;
    private Subwindow subwindow;

    public CloseSubwindowCommand(CanvasController canvasController, Subwindow subwindow){
        this.setSubwindow(subwindow);
        this.setCanvasController(canvasController);
    }

    public CanvasController getCanvasController() {
        return canvasController;
    }

    private void setCanvasController(CanvasController canvasController) {
        this.canvasController = canvasController;
    }

    public Subwindow getSubwindow() {
        return subwindow;
    }

    private void setSubwindow(Subwindow subwindow) {
        this.subwindow = subwindow;
    }

    @Override
    public void performAction() {
        canvasController.removeSubwindow(subwindow);
    }
}
