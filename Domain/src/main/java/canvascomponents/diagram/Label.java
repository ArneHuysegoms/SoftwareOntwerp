package canvascomponents.diagram;

import java.awt.geom.Point2D;

public class Label

    private String label;
    private Point2D coordinate;

    public Label(){

    }

    public Label(String label, Point2D coordinate){
        this.setLabel(label);
        this.setCoordinate(coordinate);
    }
    public String getLabel() {
        return label;
    }

    public Point2D getCoordinate() {
        return coordinate;
    }

    private void setLabel(String label) {
        this.label = label;
    }

    private void setCoordinate(Point2D coordinate) {
        this.coordinate = coordinate;
    }
}
