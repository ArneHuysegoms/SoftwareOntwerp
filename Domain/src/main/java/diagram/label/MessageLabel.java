package diagram.label;

import exceptions.DomainException;

import java.io.Serializable;

/**
 * Label subtype for labels belonging to messages
 */
public class MessageLabel extends Label implements Serializable {

    /**
     * @param label the new label for this messagelabel
     * @throws DomainException
     *         The label has to start with a lowercase character
     */
    public MessageLabel(String label) throws DomainException {
        this.setLabel(label);

    }
    /**
     * @param label
     *        The text this label should be set to
     * @return
     *        True if label starts with a lowercase character, or is completely empty
     */

    public boolean isValidLabel(String label){
        return label.equals("") || Character.isLowerCase(label.charAt(0));
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
        super.label = label;
    }

    /**
     *
     * @return a textual description of this message label
     */
    @Override
    public String toString(){
        return this.getLabel();
    }
}
