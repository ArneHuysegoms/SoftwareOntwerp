package diagram.party;

import diagram.label.Label;
import exceptions.DomainException;

public class Actor extends Party {

    /**
     * @param label
     *        The label belonging with this actor
     * @throws DomainException
     *         This Actor cannot have the given instanceName, className, positionInSequenceDiagram, coordinate or label
     */
    public Actor(Label label) throws DomainException{
        this.setLabel(label);
    }
}
