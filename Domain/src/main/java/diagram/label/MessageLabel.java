package diagram.label;

import exceptions.DomainException;

public class MessageLabel extends Label {

    private String label;

    public MessageLabel(){
        super();
    }

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
        this.label = label;
    }
}
