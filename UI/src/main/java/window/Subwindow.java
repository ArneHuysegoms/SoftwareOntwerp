package window;

import uievents.KeyEvent;
import uievents.MouseEvent;
import window.windowElements.Button;
import window.windowElements.CloseWindowButton;
import windowElements.SubwindowFrame;

import java.awt.geom.Point2D;

public abstract class Subwindow {

    public static final int WINDOWHEIGHT = 600;
    public static final int WINDOWWIDTH = 600;

    private int width;
    private int height;
    private Point2D Coordinate;

    private SubwindowFrame frame;

    private Button button;

    private Subwindow(){

    }

    private Subwindow(CloseWindowButton closeWindowButton){

    }

    public Subwindow(Point2D coordinate){
        this.setCoordinate(coordinate);
        this.setWidth(Subwindow.WINDOWWIDTH);
        this.setHeight(Subwindow.WINDOWHEIGHT);
        this.createFrame();
    }

    public Subwindow(Point2D coordinate, CloseWindowButton closeWindowButton){
        this.setCoordinate(coordinate);
        this.setWidth(Subwindow.WINDOWWIDTH);
        this.setHeight(Subwindow.WINDOWHEIGHT);
        this.createFrame(closeWindowButton);
    }

    public SubwindowFrame getFrame() {
        return frame;
    }


    private void createFrame(){
        //TODO
    }

    private void createFrame(CloseWindowButton closeWindowButton){
        //TODO
    }

    public int getWidth() {
        return width;
    }

    private void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    public Point2D getCoordinate() {
        return Coordinate;
    }

    private void setCoordinate(Point2D coordinate) {
        Coordinate = coordinate;
    }

    public abstract void handleMouseEvent(MouseEvent mouseEvent);

    public abstract void handleKeyEvent(KeyEvent keyEvent);
}
