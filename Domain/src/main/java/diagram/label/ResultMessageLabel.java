package diagram.label;

import exceptions.DomainException;

/**
 * label class for resultmessagse
 */
public class ResultMessageLabel extends MessageLabel {

    /**
     * @param label the new label for this messagelabel
     * @throws DomainException The label has to start with a lowercase character
     */
    public ResultMessageLabel(String label) throws DomainException {
        super(label);
    }

    /**
     * @param label
     *        The text to use for this methodname
     * @return
     *        True
     */
    @Override
    public boolean isValidLabel(String label) {
        return true;
    }

    /**
     *
     * @return a textual description of this ResultMessageLabel
     */
    @Override
    public String toString(){
        return this.getLabel();
    }
}
