package window.elements.button;

import window.elements.DialogboxElement;

import java.awt.geom.Point2D;

public class FakeButton extends DialogboxElement {

    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;

    private Point2D position;

    private int width;
    private int height;

    public FakeButton(Point2D position){
        this.setPosition(position);
        this.setHeight(HEIGHT);
        this.setWidth(WIDTH);
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean isClicked(Point2D coordinate) {
        double startX = position.getX();
        double endX = position.getX() + width;
        double startY = position.getY() ;
        double endY = position.getY() + height;
        return (startX <= coordinate.getX() && endX >= coordinate.getX()) && (startY <= coordinate.getY() && endY >= coordinate.getY());
    }
}
