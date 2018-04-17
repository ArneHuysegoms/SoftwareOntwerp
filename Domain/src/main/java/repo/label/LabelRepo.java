package repo.label;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import diagram.DiagramElement;
import diagram.label.Label;

/**
 * Class for keeping track of labels of the diagram and everything label-positional orientated
 */
public class LabelRepo implements Serializable {

    public static final int WIDTH = 45;
    public static final int HEIGHT = 14;

    private Map<Label, Point2D> labelPoint2DMap;

    /**
     * make a new empty labelrepo
     */
    public LabelRepo() {
        this(new HashMap<>());
    }

    /**
     * makes a new labelrepo of which the state is equal to the state given by the provided map
     * @param labelPoint2DMap a map containing the state of this labelrepo
     */
    public LabelRepo(Map<Label, Point2D> labelPoint2DMap){
        this.setLabelPoint2DMap(labelPoint2DMap);
    }

    /**
     * sets the map of this repo to the provided map
     * @param labelPoint2DMap the provided map
     * @throws IllegalArgumentException if  the provided map is null
     */
    private void setLabelPoint2DMap(Map<Label, Point2D> labelPoint2DMap) throws IllegalArgumentException{
        if(labelPoint2DMap == null){
            throw new IllegalArgumentException("Map may not be null");
        }
        this.labelPoint2DMap = labelPoint2DMap;
    }

    /**
     * @return the map containing the state of this labelrepo
     */
    public Map<Label, Point2D> getMap(){
        return this.labelPoint2DMap;
    }

    /**
     * returns the label at the specified location
     *
     * @param location the location on which you want to find a label
     * @return a label if a label exists on that location, null otherwise
     */
    public Label getLabelAtPosition(Point2D location){
        return this.getMap().entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(location))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    /**
     * updates the position of the provided label to the provided location
     * @param newPosition the new location of the label
     * @param label the label to change the location of
     */
    public void updateLabelPosition(Point2D newPosition, Label label){
        this.getMap().put(label, newPosition);
    }

    /**
     * returns the location of the provided label
     *
     * @param label the label of which you want to find the label
     * @return the location of the label
     */
    public Point2D getLocationOfLabel(Label label){
        return this.getMap().get(label);
    }

    /**
     * adds a label to the repo with the given location
     * @param label the label to add
     * @param location the location of the label
     */
    public void addLabelWithLocation(Label label, Point2D location){
        this.getMap().put(label, location);
    }

    /**
     * renmoves a label from the repository
     * @param label the label to remove
     */
    public void removeLabel(Label label){
        this.getMap().remove(label);
    }

    /**
     * removes a label from the repository if a label exists on the given location
     * @param location the location of the label we want to remove
     */
    public void removeLabelByPosition(Point2D location){
        Label l = this.getLabelAtPosition(location);
        this.removeLabel(l);
    }

    /**
     * gets the distances for every label too the given point
     * @param point the location we want the distance too
     * @return a map containing the distance for each label too the given point
     */
    public Map<Label, Double> getDistancesFromPointForLabels(Point2D point){
        return this.getMap().entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> getDistance(point, e.getKey())));
    }

    /**
     * returns every label that is clicked by clicking on the given location
     * @param location the location on which was clicked
     * @return a set containing every element that was clicked on
     */
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
