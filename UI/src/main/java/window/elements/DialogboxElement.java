package window.elements;

import exception.UIException;
import window.Clickable;

import java.awt.geom.Point2D;

public abstract class DialogboxElement implements Clickable {

    private Point2D coordinate;

    protected DialogboxElement(){

    }

    public DialogboxElement(Point2D coordinate) throws UIException{
        this.setCoordinate(coordinate);
    }


    public Point2D getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point2D coordinate) throws UIException{
        if(coordinate == null){
            throw new UIException("Coordinate may not be null for dialogboxElements");
        }
        this.coordinate = coordinate;
    }

    @Override
    public abstract boolean isClicked(Point2D coordinate);
}
