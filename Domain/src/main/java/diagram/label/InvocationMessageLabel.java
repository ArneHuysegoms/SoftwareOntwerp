package diagram.label;

import diagram.Argument;
import exceptions.DomainException;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class InvocationMessageLabel extends MessageLabel implements Serializable {

    private List<Argument> arguments;
    private int index;

    /**
     * @param label the methodname for this invocationMessageLabel
     * @param arguments List of arguments belonginging to this InvocationMessage
     * @throws DomainException
     *         a method name has to start with a lowercase character and can only contain letters, digits and underscores
     */
    public InvocationMessageLabel(String label, List<Argument> arguments) throws DomainException {
        super(label);
        this.setArguments(arguments);
        index = -1;
    }


    /**
     * @param label the text to set this methodName to
     * @throws DomainException
     *         a method name has to start with a lowercase character and can only contain letters, digits and underscores
     */
    public void setLabel(String label) throws DomainException {
        if (!isValidLabel(label)) {
            throw new DomainException("a method name has to start with a lowercase character and can only contain letters, digits and underscores");
        }
        super.label = label;
    }

    /**
     * @return returns the list of arguments for this invocationMessageLabel
     */

    public List<Argument> getArguments() {
        return arguments;
    }

    /**
     * @param arguments
     *        The list of arguments to set with this invocationMessage
     */
    public void setArguments(List<Argument> arguments) {
        this.arguments = arguments;
    }

    public void addArgument(String instance, String clas){
        getArguments().add(new Argument(instance, clas));
    }

    public void addArgumentAtPosition(String instance, String clas, int position){
        getArguments().add(position, new Argument(instance, clas));
    }

    public void deleteArgument(int position){
        getArguments().remove(position);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        if(index < 0){
            this.index = 0;
        }
        else if( index > arguments.size() - 1){
            this.index = arguments.size() - 1;
        }
        else {
            this.index = index;
        }
    }

    /**
     * @param label
     *        The text to use for this methodname
     * @return
     *        True if label starts with a lowercase character, and contains only letters digits and underscores
     */
    @Override
    public boolean isValidLabel(String label) {

        //Method starts with lowercase
        if( ! Character.isLowerCase(label.charAt(0))){
            return false;
        }

        //only letters digits and underscores
        for (int i = 0; i < label.length(); i++){
            char c = label.charAt(i);
            if( ! Character.isLetter(c)){
                if(! Character.isDigit(c)) {
                    if(! (c == '_')) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String toString(){
        String toString = this.getLabel() + "(";
        for(Argument a : getArguments()){
            toString += a.toString() + " ";
        }
        toString += ")";
        return toString;
    }

    public void moveUp(){
        if(index != 0) {
            Collections.swap(arguments, index, index - 1);
            index++;
        }
    }

    public void moveDown(){
        if(index != arguments.size() - 1) {
            Collections.swap(arguments, index, index + 1);
            index--;
        }
    }
}
