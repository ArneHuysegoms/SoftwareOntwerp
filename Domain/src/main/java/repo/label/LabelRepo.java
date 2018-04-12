package repo.label;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import diagram.DiagramElement;
import diagram.label.Label;
import exceptions.DomainException;

public class LabelRepo {

    public static final int WIDTH = 45;
    public static final int HEIGHT = 14;

    private Map<Label, Point2D> labelPoint2DMap;

    public LabelRepo(){
        this(new HashMap<>());
    }

    public LabelRepo(HashMap<Label, Point2D> labelPoint2DMap){
        this.labelPoint2DMap = labelPoint2DMap;
    }

    public Map<Label, Point2D> getMap(){
        return this.labelPoint2DMap;
    }

    public Label getLabelAtPosition(Point2D location) throws DomainException{
        return this.getMap().entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(location))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(DomainException::new);
    }

    public void updateLabelPosition(Point2D newPosition, Label label){
        this.getMap().put(label, newPosition);
    }

    public Point2D getLocationOfLabel(Label label){
        return this.getMap().get(label);
    }

    public void addLabelWithLocation(Label label, Point2D location){
        this.getMap().put(label, location);
    }

    public void removeLabel(Label label){
        this.getMap().remove(label);
    }

    public void removeLabelByPosition(Point2D location) throws DomainException{
        Label l = this.getLabelAtPosition(location);
        this.removeLabel(l);
    }

    public Map<Label, Double> getDistancesFromPointForLabels(Point2D point){
        return this.getMap().entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> getDistance(point, e.getKey())));
    }

    public Set<DiagramElement> getClickedLabels(Point2D location){
        return this.getMap().entrySet()
                .stream()
                .filter(entry -> isClicked(location, getLocationOfLabel(entry.getKey())))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    /**
     * @param clickedLocation
     *        The coordinates of the mouse where the user clicked
     * @param labelLocation
     *        The coordinate of the label to check if its clicked
     * @return
     *        True if the clicked coordinates are within the coordinates of the image of this actor
     */
    private boolean isClicked(Point2D clickedLocation, Point2D labelLocation) {
        double clickX = clickedLocation.getX();
        double clickY = clickedLocation.getY();
        double startX = labelLocation.getX();
        double startY = labelLocation.getY();
        double endX = startX + WIDTH;
        double endY = startY + HEIGHT;
        return (clickX >= startX && clickX <= endX) && (clickY >= startY && clickY <= endY);
    }

    /**
     * @param point2D
     *        The coordinates of the mouse where the user clicked
     * @return
     *       returns the distance between the coordinate of this message and the given point
     */
    private double getDistance(Point2D point2D, Label label) {
        return this.getLocationOfLabel(label).distance(point2D);
    }

}
