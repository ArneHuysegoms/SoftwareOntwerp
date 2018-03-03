package canvascomponents.diagram;

import canvascomponents.Clickable;

import java.awt.geom.Point2D;

public class Label implements Clickable {
    private String label;
    private Point2D coordinate;
    public static final int width = 240;
    public static final int height = 50;

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

    public void editLabel(String label){
        this.setLabel(label);
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
