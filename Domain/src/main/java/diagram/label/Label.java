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

    @Override
    public boolean equals(Object o){
        if(o instanceof Label){
            Label l = (Label) o;
            return this.label.equals(l.getLabel());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return this.label.hashCode();
    }
}
