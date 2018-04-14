package diagram.label;

import diagram.DiagramElement;

public abstract class Label extends DiagramElement {

    protected String label;

    public Label(){

    }

    public abstract boolean isValidLabel(String label);


    /**
     * @return  returns the label of this MessageLabel
     */
    public String getLabel() {
        return label;
    }

    public static boolean isCorrectCharForLabel(char charToAdd){
        return Character.toString(charToAdd).matches("[a-zA-Z]") || charToAdd == ':' || charToAdd == ' ';
    }
}
