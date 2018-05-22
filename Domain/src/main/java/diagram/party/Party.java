package diagram.party;

import diagram.DiagramElement;
import diagram.label.Label;

import java.io.Serializable;

/**
 * abstract superclass for parties of he diagram
 */
public abstract class Party extends DiagramElement implements Serializable {

    private Label label;

    /**
     * default constructor
     */
    public Party(){

    }

    /**
     * @param label
     *        The label belonging with this actor
     */
    public Party(Label label){
        this.setLabel(label);
    }

    /**
     * @return returns het label of this party
     */
    public Label getLabel() {
        return label;
    }

    /**
     * @param label the label to edit to
     */
    public void setLabel(Label label) {
        this.label = label;
    }

    public static boolean isValidClassString(String toCheck){
        return Character.isUpperCase(toCheck.charAt(0));
    }

    public static boolean isValidInstanceString(String toCheck){
        return Character.isLowerCase(toCheck.charAt(0));
    }

    @Override
    public abstract String toString();
}
