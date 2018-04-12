package diagram.party;

import diagram.label.Label;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public class Object extends Party {

    /**
     * @param label
     *        The label belonging with this object
     */
    public Object(Label label){
        super(label);
    }
}
