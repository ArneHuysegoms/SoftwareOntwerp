package subwindow;

import controller.CanvasController;

import java.awt.geom.Point2D;

public abstract class Button implements Clickable{

    private CanvasController controller;
    private Subwindow subwindow;

    private Point2D position;

    private int width;
    private int height;

    /**
     * default constructor
     */
    public Button(){
        this.width = 30;
        this.height = 30;
    }

    /**
     * constructor with controller
     */
    public Button(CanvasController controller){
        this.width = 30;
        this.height = 30;
        this.setController(controller);
    }

    /**
     * sets the controller for this button
     * @param controller the controller that handles actions of this button
     */
    private void setController(CanvasController controller) throws IllegalArgumentException{
        if(controller == null){
            throw  new IllegalArgumentException("Canvascontroller may not be null");
        }
        this.controller = controller;
    }

    /**
     * sets the position for this button
     * @param position the position for this button
     */
    public void setPosition(Point2D position){
        this.position = position;
    }

    /**
     * @return controller for this button
     */
    public CanvasController getController(){
        return this.controller;
    }

    /**
     * sets the subwindow for this button
     * @param subwindow  the subwindow of this button
     */
    public void setSubwindow(Subwindow subwindow){
        this.subwindow = subwindow;
    }

    /**
     *
     * @return the subwindow of this button
     */
    public Subwindow getSubwindow(){
        return this.subwindow;
    }

    /**
     *
     * @return the position of this label
     */
    public Point2D getPosition() {
        return position;
    }

    /**
     * @return width for this button
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return height for this button
     */
    public int getHeight() {
        return height;
    }

    public abstract void performAction();

    /**
     * checks if this button is clicked
     * @param location  the location of the click
     * @return true if this button is clicked, false otherwise
     */
    @Override
    public boolean isClicked(Point2D location) {
        double startX = position.getX();
        double endX = position.getX() + width;
        double startY = position.getY() ;
        double endY = position.getY() + height;
        return (startX <= location.getX() && endX >= location.getX()) && (startY <= location.getY() && endY >= location.getY());
    }
}
