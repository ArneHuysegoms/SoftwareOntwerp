package diagram.label;

import exceptions.DomainException;

public class PartyLabel extends Label {

    /**
     * @param label
     *        The label that has to be given to this party
     * @throws DomainException
     *        The label of this party has to be in the form instanceName:classname
     * @post  The new label of this party is equal to the given label
     *        | new.getLabel == label
     * @post  The new coordinate of this label is equal to the given label
     *        | new.getCoordinate == coordinate
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
        return label.equals("") || label.matches("[a-z]*:[A-Z][a-z]*\\w*");
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
