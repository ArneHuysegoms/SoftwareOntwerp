package subwindow;

import controller.CanvasController;

public class CloseButton extends Button{

    public CloseButton(){
        super();
    }

    public CloseButton(CanvasController canvasController){
        super(canvasController);
    }

    @Override
    public void performAction() {
        this.getController().removeSubwindow(getSubwindow());
    }
}
