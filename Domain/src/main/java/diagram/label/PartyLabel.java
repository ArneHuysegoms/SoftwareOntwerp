package diagram.label;

import diagram.label.Label;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public class PartyLabel extends Label {
    private String label;

    public PartyLabel(){
        super();
    }

    /**
     * @param label
     *        The label that has to be given to this party
     * @param coordinate
     *        The left upmost coordinate where this label starts
     * @throws DomainException
     *        The label of this party has to be in the form instanceName:classname
     * @post  The new label of this party is equal to the given label
     *        | new.getLabel == label
     * @post  The new coordinate of this label is equal to the given label
     *        | new.getCoordinate == coordinate
     */
    public PartyLabel(String label, Point2D coordinate) throws DomainException {
        super(coordinate);
        this.setLabel(label);
    }


    /**
     * @return
     *        returns the text in this label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label
     *        the text to set in this label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @param label
     *        The text this label should be set to
     * @return
     *        True if label is of the form instanceName:classname (instanceName optional), or empty for empty strings
     */
    public boolean isValidLabel(String label){
        return label.equals("") || label.matches("[a-z]*:[A-Z][a-z]*.");
    }


    /**
     * @param point2D
     *        The coordinates of the mouse where the user clicked
     * @return
     *       returns the distance between the coordinate of this label and the given point
     */
    @Override
    public double getDistance(Point2D point2D) {
        return this.getCoordinate().distance(point2D);
    }
}
