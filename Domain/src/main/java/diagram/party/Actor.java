package diagram.party;

import diagram.label.Label;

import java.io.Serializable;

public class Actor extends Party implements Serializable {

    /**
     * @param label
     *        The label belonging with this actor
     */
    public Actor(Label label){
        super(label);
    }

    @Override
    public String toString() {
        return this.getLabel().toString();
    }
}
