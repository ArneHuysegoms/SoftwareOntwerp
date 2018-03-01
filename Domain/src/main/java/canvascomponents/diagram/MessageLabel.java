package canvascomponents.diagram;

import java.awt.geom.Point2D;

public class MessageLabel extends Label {

    public MessageLabel(){
        super();
    }

    public MessageLabel(String label, Point2D coordinate) {
        super(label, coordinate);
    }

    public static boolean isValidMessageLabel(String label){
        return false;
        //to be implemented
    }
}
