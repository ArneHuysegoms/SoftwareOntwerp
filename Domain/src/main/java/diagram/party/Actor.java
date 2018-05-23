package diagram.party;

import diagram.label.Label;

import java.io.Serializable;

/**
 * Party class that is a stickman
 */
public class Actor extends Party implements Serializable {

    /**
     * @param label
     *        The label belonging with this actor
     */
    public Actor(Label label){
        super(label);
    }

    /**
     *
     * @return a textual description of this actor
     */
    @Override
    public String toString() {
        return this.getLabel().toString();
    }
}
