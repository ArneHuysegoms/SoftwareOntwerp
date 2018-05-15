package window.elements.button;

import command.Command;
import window.Clickable;

import java.awt.geom.Point2D;

public class Button implements Clickable {

    private Command command;

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
    public Button(Command command){
        this.width = 30;
        this.height = 30;
        this.setCommand(command);
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * sets the position for this button
     * @param position the position for this button
     */
    public void setPosition(Point2D position){
        this.position = position;
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

    public void performAction(){
        command.performAction();
    }

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
