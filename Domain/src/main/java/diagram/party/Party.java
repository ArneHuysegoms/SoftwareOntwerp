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
    public Party() {

    }

    /**
     * @param label The label belonging with this actor
     */
    public Party(Label label) {
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

    /**
     * checks if the given string is a valid Class String
     *
     * @param toCheck the string to check
     * @return true if the given string is a valid Class String
     */
    public static boolean isValidClassString(String toCheck) {
        return Character.isUpperCase(toCheck.charAt(0));
    }

    /**
     * checks if the given string is a valid instance String
     *
     * @param toCheck the string to check
     * @return true if the given s tring is a valid instance String
     */
    public static boolean isValidInstanceString(String toCheck) {
        return Character.isLowerCase(toCheck.charAt(0));
    }

    /**
     * @return a string with the textual description of the party
     */
    @Override
    public abstract String toString();
}
