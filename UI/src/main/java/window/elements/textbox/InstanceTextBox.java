package window.elements.textbox;

import diagram.party.Party;
import exception.UIException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import window.Subwindow;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;

import java.awt.geom.Point2D;

/**
 * textbox for an instance
 */
public class InstanceTextBox extends TextBox {

    public static String DESCRIPTION = "Instance";

    /**
     * creates a new instance textbox with the given parameters
     *
     * @param coordinate  the new coordinate
     * @param description the new description
     * @throws UIException if the coordinate is null
     */
    public InstanceTextBox(Point2D coordinate, String description) throws UIException {
        super(coordinate, DESCRIPTION);
    }

    @Override
    public String getStaticDescription() {
        return DESCRIPTION;
    }

    @Override
    public void setStaticDescription(String s) {
        DESCRIPTION = s;
    }

    /**
     * clones object
     * @return new object
     */
    @Override
    public DialogboxElement clone() {
        try {
            return new InstanceTextBox(getCoordinate(), DESCRIPTION);
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
        return getContents().equals("") || Party.isValidInstanceString(this.getContents());
    }

    @Override
    public void update(DiagramSubwindow diagramSubwindow, Party party){
        String[] labels = party.getLabel().getLabel().split(":");
        if (labels.length == 2) {
            this.setContents(labels[0]);
        }
    }
}
