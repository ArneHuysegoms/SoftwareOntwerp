package window.elements.button;

import window.elements.DialogboxElement;

import java.awt.geom.Point2D;

public class FakeButton extends DialogboxElement {

    private Point2D position;

    private int width;
    private int height;

    public FakeButton(Point2D position){
        this.setPosition(position);
        this.setHeight(30);
        this.setWidth(30);
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
        return (startX <= getPosition().getX() && endX >= getPosition().getX()) && (startY <= getPosition().getY() && endY >= getPosition().getY());
    }
}
