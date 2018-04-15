package diagram.label;

import exceptions.DomainException;

/**
 * Label subtype for labels belonging to messages
 */
public class MessageLabel extends Label {

    /**
     * @param label
     * @throws DomainException
     *         The label has to start with a lowercase character
     *
     * @post The labeltext of the new label equals the label of the new messageLabel
     *       | new.getLabel == label;
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
}
