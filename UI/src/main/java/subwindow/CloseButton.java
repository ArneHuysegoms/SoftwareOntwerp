package subwindow;

import controller.CanvasController;

public class CloseButton extends Button{

    /**
     * default constructor
     */
    public CloseButton(){
        super();
    }

    /**
     * constructor with controller
     */
    public CloseButton(CanvasController canvasController){
        super(canvasController);
    }

    /**
     * perform the action of closing the subwindow
     */
    @Override
    public void performAction() {
        this.getController().removeSubwindow(getSubwindow());
    }
}
