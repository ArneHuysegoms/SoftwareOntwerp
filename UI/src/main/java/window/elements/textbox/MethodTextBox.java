package window.elements.textbox;

import diagram.label.InvocationMessageLabel;
import diagram.message.ResultMessage;
import diagram.party.Party;
import exception.UIException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import window.Subwindow;
import window.elements.DialogboxElement;

import java.awt.geom.Point2D;
import java.util.stream.Collectors;

/**
 * textbox for a method name
 */
public class MethodTextBox extends TextBox {

    public static String DESCRIPTION = "message label";

    /**
     * creates a new methodtextbox with the given parameters
     *
     * @param coordinate  the coordinate
     * @param description the description
     * @throws UIException if the coordinate is null
     */
    public MethodTextBox(Point2D coordinate, String description) throws UIException {
        super(coordinate, DESCRIPTION);
    }

    /**
     * clones object
     * @return new object
     */
    @Override
    public DialogboxElement clone() {
        try {
            return new MethodTextBox(getCoordinate(), DESCRIPTION);
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
    /**
     * get static description
     * @return description
     */
    @Override
    public String getStaticDescription(){
        return DESCRIPTION;
    }

    /**
     * set static description
     * @param s
     */
    @Override
    public void setStaticDescription(String s){
        DESCRIPTION = s;

    }

    /**
     * update contents
     * @param iml
     */
    @Override
    public void update(InvocationMessageLabel iml){
        if(iml != null){
            this.setContents(iml.getLabel());
        }
    }
}
