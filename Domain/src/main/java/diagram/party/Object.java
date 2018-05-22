package diagram.party;

import diagram.label.Label;

import java.io.Serializable;

public class Object extends Party implements Serializable {

    /**
     * @param label
     *        The label belonging with this object
     */
    public Object(Label label){
        super(label);
    }

    @Override
    public String toString() {
        return this.getLabel().toString();
    }
}
