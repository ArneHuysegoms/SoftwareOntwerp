package subwindow;

import controller.CanvasController;

import java.awt.geom.Point2D;

public abstract class Button {

    private CanvasController controller;
    private Subwindow subwindow;

    private Point2D location;

    private int width;
    private int height;

    public Button(){
        this.width = 50;
        this.height = 50;
    }

    public Button(CanvasController controller){
        this.width = 50;
        this.height = 50;
        this.setController(controller);
    }

    private void setController(CanvasController controller) throws IllegalArgumentException{
        if(controller == null){
            throw  new IllegalArgumentException("Canvascontroller may not be null");
        }
        this.controller = controller;
    }

    public CanvasController getController(){
        return this.getController();
    }

    public void setSubwindow(Subwindow subwindow){
        this.subwindow = subwindow;
    }

    public Subwindow getSubwindow(){
        return this.subwindow;
    }

    public abstract void performAction();
}
