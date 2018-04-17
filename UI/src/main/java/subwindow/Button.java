package subwindow;

import controller.CanvasController;

import java.awt.geom.Point2D;

public abstract class Button implements Clickable{

    private CanvasController controller;
    private Subwindow subwindow;

    private Point2D position;

    private int width;
    private int height;

    public Button(){
        this.width = 30;
        this.height = 30;
    }

    public Button(CanvasController controller){
        this.width = 30;
        this.height = 30;
        this.setController(controller);
    }

    private void setController(CanvasController controller) throws IllegalArgumentException{
        if(controller == null){
            throw  new IllegalArgumentException("Canvascontroller may not be null");
        }
        this.controller = controller;
    }

    public void setPosition(Point2D position){
        this.position = position;
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

    public Point2D getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public abstract void performAction();

    @Override
    public boolean isClicked(Point2D location) {
        double startX = position.getX();
        double endX = position.getX() + width;
        double startY = position.getY() ;
        double endY = position.getY() + height;
        return (startX <= position.getX() && endX >= location.getX()) && (startY <= location.getY() && endY >= location.getY());
    }
}
