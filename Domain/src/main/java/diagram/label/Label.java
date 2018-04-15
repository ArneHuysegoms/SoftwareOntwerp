package diagram.label;

import diagram.DiagramElement;

/**
 * abstract superclass for labels
 */
public abstract class Label extends DiagramElement {

    protected String label;

    /**
     * create a completely empty label
     */
    public Label(){

    }

    /**
     * checks if the given string is a valid string
     *
     * @param label the contents of this label
     * @return whether the given label is valid
     */
    public abstract boolean isValidLabel(String label);


    /**
     * @return  returns the label of this MessageLabel
     */
    public String getLabel() {
        return label;
    }

    /**
     * checks of the given char is a valid char for this label
     *
     * only alfabetical chars, spaces and colons are allowed
     *
     * @param charToAdd
     * @return
     */
    public static boolean isCorrectCharForLabel(char charToAdd){
        return Character.toString(charToAdd).matches("[a-zA-Z]") || charToAdd == ':' || charToAdd == ' ';
    }
}
