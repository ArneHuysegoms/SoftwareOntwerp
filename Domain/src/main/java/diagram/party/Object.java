package diagram.party;

import diagram.label.Label;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public class Object extends Party {

    /**
     * @param label
     *        The label belonging with this object
     * @throws DomainException
     *         This object cannot have the given positionInSequenceDiagram, coordinate or label
     */
    public Object(Label label) throws DomainException{
        this.setLabel(label);
    }
}
