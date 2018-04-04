package diagram.label;

import diagram.Clickable;
import exceptions.DomainException;

import java.awt.geom.Point2D;

public abstract class Label{

    private String label;

    public Label(){

    }

    public abstract boolean isValidLabel(String label);


    /**
     * @return  returns the label of this MessageLabel
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label
     *        The text to set the label to
     * @throws DomainException
     *         The label has to start with a lowercase character
     */
    public void setLabel(String label) throws DomainException {
        if (!isValidLabel(label)) {
            throw new DomainException("a message label has to start with a lowercase character");
        }
        this.label = label;
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
