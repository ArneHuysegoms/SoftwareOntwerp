package diagram.party;

import diagram.label.Label;

import java.io.Serializable;

/**
 * a party that is a box
 */
public class Object extends Party implements Serializable {

    /**
     * @param label
     *        The label belonging with this object
     */
    public Object(Label label){
        super(label);
    }

    /**
     *
     * @return a textual description of this object
     */
    @Override
    public String toString() {
        return this.getLabel().toString();
    }
}
