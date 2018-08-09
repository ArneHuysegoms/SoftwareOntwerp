package window.elements.textbox;

import diagram.party.Party;
import exception.UIException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import window.Subwindow;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;

import java.awt.geom.Point2D;

/**
 * a textbox for a class
 */
public class ClassTextBox extends TextBox {

    /**
     * creates a new ClassTextBox
     *
     * @param coordinate  the new coordinate for this textbox
     * @param description the new description for this textbox
     * @throws UIException if the coordinate is null
     */
    public ClassTextBox(Point2D coordinate, String description) throws UIException {
        super(coordinate, description);
    }

    @Override
    public DialogboxElement clone() {
        try {
            return new ClassTextBox(getCoordinate(), getDescription());
        } catch (UIException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return true if the contents are valid
     */
    @Override
    public boolean hasValidContents() {
        return super.hasValidContents() && Party.isValidClassString(getContents());
    }

    @Override
    public void update(DiagramSubwindow diagramSubwindow, Party party){
        String[] labels = party.getLabel().getLabel().split(":");
        if (labels.length == 2) {
            this.setContents(labels[1]);
        } else {
            this.setContents(labels[0]);
        }
    }
}
