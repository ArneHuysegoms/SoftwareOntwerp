package diagram.label;

import diagram.Clickable;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public abstract class Label implements Clickable {

    private Point2D coordinate;
    private String label;

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
    public void setCoordinate(Point2D coordinate) {
        this.coordinate = coordinate;
    }

    public abstract boolean isValidLabel(String label);


    /**
     * @return  returns the label of this MessageLabel
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label
     *        The text to set the label to
     * @throws DomainException
     *         The label has to start with a lowercase character
     */
    public void setLabel(String label) throws DomainException {
        if (!isValidLabel(label)) {
            throw new DomainException("a message label has to start with a lowercase character");
        }
        this.label = label;
    }

    public static boolean isCorrectCharForLabel(char charToAdd){
        return Character.toString(charToAdd).matches("[a-zA-Z]") || charToAdd == ':' || charToAdd == ' ';
    }
}
