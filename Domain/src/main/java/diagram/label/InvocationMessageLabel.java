package diagram.label;

import diagram.Argument;
import exceptions.DomainException;

import java.io.Serializable;
import java.util.List;

public class InvocationMessageLabel extends Label implements Serializable {
    private List<Argument> arguments;

    public InvocationMessageLabel(String label, List<Argument> arguments) throws DomainException {
        this.setLabel(label);
        this.setArguments(arguments);
    }


    public void setLabel(String label) throws DomainException {
        if (!isValidLabel(label)) {
            throw new DomainException("a method name has to start with a lowercase character and can only contain letters, digits and underscores");
        }
        super.label = label;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public void setArguments(List<Argument> arguments) {
        this.arguments = arguments;
    }

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
