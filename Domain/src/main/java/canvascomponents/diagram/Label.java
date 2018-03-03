package canvascomponents.diagram;

import canvascomponents.Clickable;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public abstract class Label implements Clickable {

    private Point2D coordinate;

    public static final int width = 45;
    public static final int height = 14;

    public Label(){

    }

    public Label(Point2D coordinate){
       this.setCoordinate(coordinate);
    }

    public Point2D getCoordinate() {
        return coordinate;
    }

    private void setCoordinate(Point2D coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public boolean isClicked(Point2D point2D) {
        double clickX = point2D.getX();
        double clickY = point2D.getY();
        double startX = this.getCoordinate().getX();
        double startY = this.getCoordinate().getY();
        double endX = startX + width;
        double endY = startY + height;
        return (clickX >= startX && clickX <= endX) && (clickY >= startY && clickY <= endY);
    }
}
