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

    /**
     * @param coordinate
     *        The coordinate of the left upmost point of the label
     * @post the coordinate of the new label equals the given coordinate
     *       new.getCoordinate == coordinate;
     */
    public Label(Point2D coordinate){
       this.setCoordinate(coordinate);
    }

    /**
     * @return  returns the coordinate of label
     */
    public Point2D getCoordinate() {
        return coordinate;
    }

    /**
     * @param coordinate
     *        The coordinate of the left upmost point of the label
     * @post the coordinate of the new label equals the given coordinate
     *       new.getCoordinate == coordinate;
     */
    private void setCoordinate(Point2D coordinate) {
        this.coordinate = coordinate;
    }


    /**
     * @param point2D
     *        The coordinates of the mouse where the user clicked
     * @return
     *        True if the clicked coordinates are within the coordinates of the image of this actor
     */
    @Override
    public boolean isClicked(Point2D point2D) {
        double clickX = point2D.getX();
        double clickY = point2D.getY();
        double startX = this.getCoordinate().getX();
        double startY = this.getCoordinate().getY();
        double endX = startX + width;
        double endY = startY + height;
        return (clickX >= startX && clickX <= endX) && (clickY <= startY && clickY >= endY);
    }
}
