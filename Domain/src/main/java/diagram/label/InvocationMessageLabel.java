package diagram.label;

import exceptions.DomainException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * class for the label of an invocation message with a list of arguments and a label methodname
 */
public class InvocationMessageLabel extends MessageLabel implements Serializable {

    private List<String> arguments;
    private int index;

    /**
     * @param label     the methodname for this invocationMessageLabel
     * @param arguments List of arguments belonginging to this InvocationMessage
     * @throws DomainException a method name has to start with a lowercase character and can only contain letters, digits and underscores
     */
    public InvocationMessageLabel(String label, List<String> arguments) throws DomainException {
        super(label);
        this.setArguments(arguments);
        index = -1;
    }


    /**
     * @param label the text to set this methodName to
     * @throws DomainException a method name has to start with a lowercase character and can only contain letters, digits and underscores
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
    public List<String> getArguments() {
        return arguments;
    }

    /**
     * @param arguments The list of arguments to set with this invocationMessage
     */
    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * adds a new argument with the given strings to the list
     *
     * @param argument the argument to add
     */
    public void addArgument(String argument) {
        getArguments().add(argument);
        setIndex(getArguments().size() - 1);
    }

    /**
     * adds a new argument with the given strings to the list at the specified location
     *
     * @param argument the argument to add
     * @param position the position in the list to add to
     */
    public void addArgumentAtPosition(String argument, int position) {
        getArguments().add(position, argument);
    }

    /**
     * deletes the argument at the given position from the list
     *
     * @param position the position to remove at
     */
    public void deleteArgument(int position) {
        getArguments().remove(position);
        setIndex(index - 1);
    }

    /**
     * @return the pointer to the currently selected argument
     */
    public int getIndex() {
        return index;
    }

    /**
     * sets the index to the given index, with sanitizing for the range of [-1, arguments.size() -1]
     *
     * @param index the index to set the index pointer too
     */
    public void setIndex(int index) {
        if (index < -1) {
            this.index = -1;
        } else if (index > arguments.size() - 1) {
            this.index = arguments.size() - 1;
        } else {
            this.index = index;
        }
    }

    /**
     * @param label The text to use for this methodname
     * @return True if label starts with a lowercase character, and contains only letters digits and underscores
     */
    @Override
    public boolean isValidLabel(String label) {

        //Method starts with lowercase
        if (!Character.isLowerCase(label.charAt(0))) {
            return false;
        }

        //only letters digits and underscores
        for (int i = 0; i < label.length(); i++) {
            char c = label.charAt(i);
            if (!Character.isLetter(c)) {
                if (!Character.isDigit(c)) {
                    if (!(c == '_')) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * @return the text for this invocationmessage label
     */
    @Override
    public String toString() {
        String toString = this.getLabel() + "(";
        for (int i = 0; i < this.getArguments().size(); i++){
            if(i == this.getArguments().size() - 1){
                toString += getArguments().get(i);
            }
            else{
                toString += getArguments().get(i) + ",";
            }
        }
        toString += ")";
        return toString;
    }

    /**
     * moves the argument at the current index 1 up the list of arguments
     */
    public void moveUp() {
        System.out.println("2e MOVEUPPPPPPPPPPPPPPPPP" + this + " " + index);
        if (index > 0) {
            Collections.swap(arguments, index, index - 1);
            this.setIndex(index - 1);
            System.out.println("2e MOVEUPPPPPPPPPPPPPPPPPPPPPP" + this + " " + index);
        }
    }

    /**
     * moves the argument at the current index 1 down the list of arguments
     */
    public void moveDown() {
        if (index < arguments.size() - 1) {
            Collections.swap(arguments, index, index + 1);
            this.setIndex(index + 1);
        }
    }

    /**
     * parses a complete label
     *
     * @param label the label to parse
     * @throws DomainException if illegal arguments are made
     */
    public void setCompleteLabel(String label) throws DomainException {
        arguments = new ArrayList<>();
        int open = label.indexOf('(');
        int close = label.indexOf(')');
        this.setLabel(label.substring(0, open));
        String[] args = label.substring(open + 1, close).split(",");
        for (String s : args) {
            if (!s.isEmpty()) {
                arguments.add(s);
            }
        }
    }

    /**
     * checks if the given label is a valid invocation message label
     *
     * @param label the label to check
     * @return true if the label is valid
     */
    public boolean isValidCompleteLabel(String label) {
        int open = label.indexOf('(');
        if (open < 0) {
            return false;
        }
        int close = label.indexOf(')');
        if (close < 0 || label.charAt(label.length() - 1) != ')') {
            return false;
        }
        if (!isValidLabel(label.substring(0, open))) {
            return false;
        }
        String[] args = label.substring(open + 1, close).split(",");
        for (String s : args) {
            if (!s.isEmpty()) {
                if(s.contains(",") || s.contains("(") || s.contains(")")){
                    return false;
                }
            }
            else{
                if(args.length != 1){
                   return false;
                }
            }
        }
        return true;
    }
}
