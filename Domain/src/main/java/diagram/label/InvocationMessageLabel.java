package diagram.label;

import diagram.Argument;
import exceptions.DomainException;

import java.io.Serializable;
import java.util.List;

public class InvocationMessageLabel extends Label implements Serializable {
    private List<Argument> arguments;

    /**
     * @param label the methodname for this invocationMessageLabel
     * @param arguments List of arguments belonginging to this InvocationMessage
     * @throws DomainException
     *         a method name has to start with a lowercase character and can only contain letters, digits and underscores
     */
    public InvocationMessageLabel(String label, List<Argument> arguments) throws DomainException {
        this.setLabel(label);
        this.setArguments(arguments);
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

    /**
     * @param label
     *        The text to use for this methodname
     * @return
     *        True if label starts with a lowercase character, and contains only letters digits and underscores
     */
    @Override
    public boolean isValidLabel(String label) {

        //Method starts with lowercase
        boolean startsWithLowercase = Character.isLowerCase(label.charAt(0));

        //only letters digits and underscores
        boolean onlyLetDigUnd = true;
        for (int i = 0; i < label.length(); i++){
            char c = label.charAt(i);
            if(!(Character.isLetter(c) || Character.isDigit(c) || c == '_')){
                onlyLetDigUnd = false;
            }
        }
        return startsWithLowercase && onlyLetDigUnd;
    }
}
