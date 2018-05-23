package diagram.label;

import exceptions.DomainException;

import java.io.Serializable;

/**
 * Label subtype for labels belonging to parties
 */
public class PartyLabel extends Label implements Serializable {

    /**
     * @param label
     *        The label that has to be given to this party
     * @throws DomainException
     *        The label of this party has to be in the form instanceName:classname
     */
    public PartyLabel(String label) throws DomainException {
        this.setLabel(label);
    }

    /**
     * @param label
     *        The text this label should be set to
     * @return
     *        True if label is of the form instanceName:classname (instanceName optional), or empty for empty strings
     */
    public boolean isValidLabel(String label){
        if(label.equals("")){
            return true;
        }
        else{
            if(label.charAt(0) == ':'){
                if(label.length() > 1) {
                    return Character.isUpperCase(label.charAt(1));
                }
                return false;
            }
            else{
                String[] parts = label.split(":");
                if(parts.length == 2) {
                    if (parts[1].length() > 0) {
                        return Character.isLowerCase(parts[0].charAt(0)) && Character.isUpperCase(parts[1].charAt(0));
                    }
                    return false;
                }
                return false;
            }
        }
    }

    /**
     * @param label
     *        The text to set the label to
     * @throws DomainException
     *         The label has to start with a lowercase character
     */
    public void setLabel(String label) throws DomainException {
        if (!isValidLabel(label)) {
            throw new DomainException("invalid party label");
        }
        super.label = label;
    }

    /**
     *
     * @return a textual description of this party label
     */
    @Override
    public String toString(){
        return this.getLabel();
    }
}
