package canvascomponents.diagram;

import exceptions.DomainException;

import java.awt.geom.Point2D;

public class MessageLabel extends Label {
    private String label;

    public MessageLabel(){
        super();
    }
/**
 * @param coordinate
 *        The coordinate of the left upmost point of the label
 * @post the coordinate of the new label equals the given coordinate
 *       new.getCoordinate == coordinate;
 */
    /**
     * @param label
     * @param coordinate
     *        The coordinate of the left upmost point of the label
     * @post the coordinate of the new label equals the given coordinate
     *       new.getCoordinate == coordinate;
     * @throws DomainException
     *         The label has to start with a lowercase character
     *
     * @post The labeltext of the new label equals the label of the new messageLabel
     *       | new.getLabel == label;
     */
    public MessageLabel(String label, Point2D coordinate) throws DomainException {
        super(coordinate);
        this.setLabel(label);

    }
    /**
     * @param label
     *        The text this label should be set to
     * @return
     *        True if label starts with a lowercase character, or is completely empty
     */

    public static boolean isValidMessageLabel(String label){
        return label.equals("") || Character.isLowerCase(label.charAt(0));
    }


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
        if (!isValidMessageLabel(label)) {
            throw new DomainException("a message label has to start with a lowercase character");
        }
        this.label = label;
    }

    /**
     * @param point2D
     *        The coordinates of the mouse where the user clicked
     * @return
     *       returns the distance between the coordinate of this message and the given point
     */

    @Override
    public double getDistance(Point2D point2D) {
        return this.getCoordinate().distance(point2D);
    }
}
