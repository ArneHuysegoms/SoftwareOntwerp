package windowElements;

import subwindow.Clickable;

import java.awt.geom.Point2D;

public class TitleBar implements Clickable {

    private final int HEIGHT = 30;

    Point2D position;
    int width;

    public TitleBar(Point2D position, int width){
        this.position = position;
        this.width = width;
    }

    @Override
    public boolean isClicked(Point2D location) {
        double startX = position.getX();
        double endX = position.getX() + width;
        double startY = position.getY() ;
        double endY = position.getY() + HEIGHT;
        return (startX <= position.getX() && endX >= location.getX()) && (startY <= location.getY() && endY >= location.getY());
    }
}
