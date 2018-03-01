package canvascomponents;

import java.awt.geom.Point2D;

public abstract class CanvasComponent {

    private Point2D coordinate;

    public CanvasComponent(){
        this.setCoordinate(new Point2D.Double(0, 0));
    }

    public CanvasComponent(Point2D coordinate){
        this.setCoordinate(coordinate);
    }


    public Point2D getCoordinate() {
        return coordinate;
    }

    private void setCoordinate(Point2D coordinate) {
        this.coordinate = coordinate;
    }

    public abstract Clickable findClickedElement(Point2D point2D);
}
