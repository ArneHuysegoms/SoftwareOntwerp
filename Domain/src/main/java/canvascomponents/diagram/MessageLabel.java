package canvascomponents.diagram;

import exceptions.DomainException;

import java.awt.geom.Point2D;

public class MessageLabel extends Label {
    private String label;

    public MessageLabel(){
        super();
    }

    public MessageLabel(String label, Point2D coordinate) throws DomainException {
        super(coordinate);
        if (!isValidMessageLabel(label)) {
            throw new DomainException("a message label has to start with a lowercase character");
        }
        this.setLabel(label);

    }

    public static boolean isValidMessageLabel(String label){
        return Character.isUpperCase(label.charAt(0));
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public double getDistance(Point2D point2D) {
        return this.getCoordinate().distance(point2D);
    }
}
