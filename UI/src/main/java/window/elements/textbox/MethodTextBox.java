package window.elements.textbox;

import diagram.message.ResultMessage;
import diagram.party.Party;
import exception.UIException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import window.Subwindow;
import window.elements.DialogboxElement;

import java.awt.geom.Point2D;

/**
 * textbox for a method name
 */
public class MethodTextBox extends TextBox {

    /**
     * creates a new methodtextbox with the given parameters
     *
     * @param coordinate  the coordinate
     * @param description the description
     * @throws UIException if the coordinate is null
     */
    public MethodTextBox(Point2D coordinate, String description) throws UIException {
        super(coordinate, description);
    }

    @Override
    public DialogboxElement clone() {
        try {
            return new MethodTextBox(getCoordinate(), getDescription());
        } catch (UIException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * @return true if this textbox has valid contents
     */
    @Override
    public boolean hasValidContents() {
        return !this.getContents().isEmpty();
    }

    @Override
    public void update(ResultMessage rm){
        this.setContents(rm.getLabel().getLabel());
    }

}
